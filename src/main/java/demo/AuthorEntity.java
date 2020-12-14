package demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
public class AuthorEntity {
	private String id;
	private String email;
	private String name;
	private List<MessageEntity> messages;

	public AuthorEntity() {
		this.messages = new ArrayList<>();
	}

	public List<MessageEntity> getMessages() {
		return messages;
	}
	
	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}
	
	public void addMessage(MessageEntity message) {
		this.messages.add(message);
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AuthorEntity{" +
				"id='" + id + '\'' +
				", email='" + email + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
