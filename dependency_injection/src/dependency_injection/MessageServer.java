package dependency_injection;

import java.util.List;
public class MessageServer implements Runnable {
    private final MessageStore messageStore;

    public MessageServer(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    @Override
    public void run() {
        System.out.println("Сервер сообщений запущен.");
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            while (true) {
                String input = scanner.nextLine();
                if (input.startsWith("ADD")) {
                    String message = input.substring(4);
                    messageStore.addMessage(message);
                    System.out.println("Сообщение добавлено.");
                } else if (input.equals("GET")) {
                    List<String> messages = messageStore.getAllMessages();
                    System.out.println("Сообщения:");
                    for (String msg : messages) {
                        System.out.println("- " + msg);
                    }
                } else if (input.equals("EXIT")) {
                    System.out.println("Сервер завершает работу.");
                    break;
                } else {
                    System.out.println("Неизвестная команда.");
                }
            }
        }
    }
}

