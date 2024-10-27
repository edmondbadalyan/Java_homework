package com.Edmond.Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ChatClient {

    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JTextField usernameField;
    private PrintWriter out;

    public ChatClient() {
        frame = new JFrame("Chat Client");
        chatArea = new JTextArea(20, 50);
        inputField = new JTextField(50);
        usernameField = new JTextField(15);
        JButton sendButton = new JButton("Send");

        chatArea.setEditable(false);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Настройка поля для ввода имени пользователя
        usernameField.setForeground(Color.LIGHT_GRAY);
        usernameField.setText("Введите имя");
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (usernameField.getText().equals("Введите имя")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setForeground(Color.LIGHT_GRAY);
                    usernameField.setText("Введите имя");
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(usernameField);
        inputPanel.add(inputField);
        inputPanel.add(sendButton);
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        connectToServer();
        new Thread(this::receiveMessages).start();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = inputField.getText();
        String username = usernameField.getText();
        if (!message.isEmpty() && !username.equals("Введите имя")) {
            out.println(username + ": " + message);  // Отправляем сообщение с именем пользователя
            inputField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Пожалуйста, введите имя и сообщение.");
        }
    }

    private void receiveMessages() {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = in.readLine()) != null) {
                chatArea.append(message + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatClient::new);
    }
}
