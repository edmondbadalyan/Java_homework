package com.Edmond.Chat;

import org.junit.jupiter.api.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChatServerTest {
    private ChatServer server;

    @BeforeEach
    public void setUp() {
        server = new ChatServer();
        new Thread(server).start();
    }

    @AfterEach
    public void tearDown() {
        server.stop();
    }

    @Test
    public void testClientConnection() throws Exception {
        try (Socket clientSocket = new Socket("localhost", 12345)) {
            assertTrue(clientSocket.isConnected(), "Client should connect to server");
        }
    }
}
