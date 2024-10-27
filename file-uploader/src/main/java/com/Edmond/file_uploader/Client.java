package com.Edmond.file_uploader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    private JFrame frame;
    private JButton selectButton;
    private JButton sendButton;
    private JLabel imageLabel;
    private File selectedFile;

    public Client() {
        frame = new JFrame("File Uploader");
        selectButton = new JButton("Select File");
        sendButton = new JButton("Send File");
        imageLabel = new JLabel();

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image and Video Files", "jpg", "jpeg", "png", "gif", "mp4"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    
                    if (isImageFile(selectedFile)) {
                        imageLabel.setIcon(new ImageIcon(selectedFile.getPath()));
                    } else {
                        imageLabel.setIcon(null); // Сбрасываем иконку для видео
                    }
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    sendFile(selectedFile);
                }
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(selectButton, BorderLayout.NORTH);
        frame.add(imageLabel, BorderLayout.CENTER);
        frame.add(sendButton, BorderLayout.SOUTH);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void sendFile(File file) {
        try (Socket socket = new Socket("localhost", 12345);
             FileInputStream fis = new FileInputStream(file);
             OutputStream os = socket.getOutputStream()) {

            
            os.write((file.getName() + "\n").getBytes()); 
            
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
            JOptionPane.showMessageDialog(frame, "File sent successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error sending file.");
        }
    }

    private boolean isImageFile(File file) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "gif"};
        String fileName = file.getName();
        for (String ext : imageExtensions) {
            if (fileName.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new Client();
    }
}

