package dependency_injection;

import java.util.ArrayList;
import java.util.List;

public class MessageStore {
	private final List<String> messages = new ArrayList<>();

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getAllMessages() {
        return new ArrayList<>(messages);
    }
}
