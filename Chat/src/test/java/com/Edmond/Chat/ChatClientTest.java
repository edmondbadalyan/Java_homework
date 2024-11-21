package com.Edmond.Chat;

import org.junit.jupiter.api.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import static org.junit.jupiter.api.Assertions.*;

public class ChatClientTest {

    private static Thread serverThread;
    private ChatClient client;
    private ByteArrayOutputStream messageOutput;

    @BeforeAll
    public static void startServer() {
        // Запускаем сервер в отдельном потоке
        serverThread = new Thread(() -> {
            ChatServer server = new ChatServer();
            server.run();
        });
        serverThread.start();
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Инициализация клиента
        messageOutput = new ByteArrayOutputStream();
        client = new ChatClient();
        client.frame.setVisible(false);
    }

    @AfterEach
    public void tearDown() {
        client.frame.dispose();
        client = null;
    }

    @AfterAll
    public static void stopServer() {
        serverThread.interrupt();
    }

    @Test
    public void testClientConnection() throws Exception {
        Socket clientSocket = new Socket("localhost", 12345);
        assertTrue(clientSocket.isConnected(), "Клиент должен подключиться к серверу");
        clientSocket.close();
    }

    @Test
    public void testSendMessage() throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            client.usernameField.setText("TestUser");
            client.inputField.setText("Hello");
        });

        assertEquals("TestUser", client.usernameField.getText(), "Имя пользователя должно быть установлено");
        assertEquals("Hello", client.inputField.getText(), "Сообщение должно быть установлено");

        client.out = new PrintWriter(messageOutput, true);

        SwingUtilities.invokeAndWait(client::sendMessage);

        String output = messageOutput.toString().trim();
        assertEquals("TestUser: Hello", output, "Сообщение должно быть отправлено на сервер");
    }



    @Test
    public void testReceiveMessage() throws Exception {
        
        BufferedReader in = new BufferedReader(new StringReader("Server: Welcome to the chat\n"));

        
        String message;
        while ((message = in.readLine()) != null) {
            client.chatArea.append(message + "\n");
        }

        
        SwingUtilities.invokeAndWait(() -> {
            assertTrue(client.chatArea.getText().contains("Server: Welcome to the chat"), 
                       "Входящее сообщение должно быть отображено в чате");
        });
    }

    @Test
    public void testEmptyMessageValidation() {
        client.inputField.setText("");
        client.usernameField.setText("TestUser");

        client.out = new PrintWriter(messageOutput, true);
       
        client.sendMessage();

        assertTrue(messageOutput.toString().isEmpty(), "Пустое сообщение не должно быть отправлено");
    }
}
