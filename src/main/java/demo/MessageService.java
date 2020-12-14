package demo;

import java.util.List;

public interface MessageService {

	public MessageBoundary create(MessageBoundary message);
	public List<MessageBoundary> getAll (int size, int page);

	public AuthorBoundary create (AuthorBoundary author);
	public List<AuthorBoundary> getAllAuthors (int size, int page);
	
	public void bind (String email, String id);
	public List<MessageBoundary> getMessagesByAuthor(String email);
}
