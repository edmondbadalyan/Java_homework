package dependency_injection;

public class Main {

	public static void main(String[] args) {
		MessageStore messageStore = new MessageStore();
        MessageServer server = new MessageServer(messageStore);

        Thread serverThread = new Thread(server);
        serverThread.start();

	}

}