package com.Edmond.Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ChatServer implements Runnable {

    private static final int PORT = 12345;
    private static final List<ClientHandler> clients = new ArrayList<>();
    private static final List<String> messageHistory = new ArrayList<>();
    private ServerSocket serverSocket;
    private boolean running;

    public ChatServer() {
        // Default constructor
    }

    public static void main(String[] args) {
        System.out.println("Chat server started...");
        ChatServer server = new ChatServer();
        new Thread(server).start(); // Start the server in a new thread
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(PORT);
            running = true;

            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop(); // Clean up when server stops
        }
    }

    public static void broadcast(String message) {
        messageHistory.add(message); // Save the message in history
        if (messageHistory.size() > 10) {
            messageHistory.remove(0); // Remove old messages
        }
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void stop() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    broadcast(message);
                }
            } catch (SocketException e) {
                System.out.println("Client disconnected: " + socket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendMessage(String message) {
            if (out != null) {
                out.println(message);
            }
        }
    }
}
