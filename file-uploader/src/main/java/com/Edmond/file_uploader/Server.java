package com.Edmond.file_uploader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server {

    private JFrame frame;
    private JList<String> fileList;
    private DefaultListModel<String> listModel;
    private static final String IMAGE_DIR = "images/";

    public Server() {
        frame = new JFrame("File Server");
        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        frame.add(new JScrollPane(fileList), BorderLayout.CENTER);

        
        fileList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedFile = fileList.getSelectedValue();
                    if (selectedFile != null) {
                        openFile(selectedFile);
                    }
                }
            }
        });

        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(this::startServer).start();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (InputStream is = clientSocket.getInputStream()) {
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String fileName = reader.readLine(); 
            String filePath = IMAGE_DIR + fileName; 
            Files.createDirectories(Paths.get(IMAGE_DIR));

            try (OutputStream os = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                
                // Добавляем файл в список и логируем
                listModel.addElement(filePath);
                System.out.println("File received and saved as: " + filePath);
                JOptionPane.showMessageDialog(frame, "File received: " + filePath);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openFile(String filePath) {
        File file = new File(filePath);
        String fileExtension = getFileExtension(file);

        try {
            if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg") ||
                fileExtension.equalsIgnoreCase("png") || fileExtension.equalsIgnoreCase("gif") || 
                fileExtension.equalsIgnoreCase("mp4")) {
               
                if (fileExtension.equalsIgnoreCase("mp4")) {
                   
                    Desktop.getDesktop().open(file);
                } else {
                    openImage(filePath);
                }
            } else {
                
                Desktop.getDesktop().open(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Unable to open file: " + e.getMessage());
        }
    }

    private void openImage(String filePath) {
        JFrame imageFrame = new JFrame("Image Preview");
        imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        ImageIcon imageIcon = new ImageIcon(filePath);
        JLabel label = new JLabel(imageIcon);
        imageFrame.add(new JScrollPane(label), BorderLayout.CENTER);

        imageFrame.setSize(500, 500);
        imageFrame.setLocationRelativeTo(frame);
        imageFrame.setVisible(true);
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');
        return lastIndexOfDot == -1 ? "" : fileName.substring(lastIndexOfDot + 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Server::new);
    }
}



