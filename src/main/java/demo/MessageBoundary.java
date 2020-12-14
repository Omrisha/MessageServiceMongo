package demo;

// {"message":"hello", "id":"xyz"}
public class MessageBoundary {
	private String message;
	private String id;
	
	public MessageBoundary() {
	}

	public MessageBoundary(MessageEntity entity) {
		this.id = entity.getId();
		this.message = entity.getMessage();
	}
	
	public MessageBoundary(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public MessageEntity toEntity() {
		MessageEntity entity = new MessageEntity();

		entity.setId(this.getId());
		entity.setMessage(this.getMessage());

		return entity;
	}

	@Override
	public String toString() {
		return "MessageBoundary{" +
				"message='" + message + '\'' +
				", id='" + id + '\'' +
				'}';
	}
}
