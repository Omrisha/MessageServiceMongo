package demo;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceDb implements MessageService {

	private MesssageDao messageDao;
	private AuthorDao authorDao;
	private Log log = LogFactory.getLog(MessageServiceDb.class);

	@Autowired
	public MessageServiceDb(MesssageDao messageDao, AuthorDao authorDao) {
		this.messageDao = messageDao;
		this.authorDao = authorDao;
	}

	@Override
	public MessageBoundary create(MessageBoundary message) {
		MessageEntity entity = message.toEntity();
		entity = this.messageDao.save(entity);
		return new MessageBoundary(entity);
	}

	@Override
	public List<MessageBoundary> getAll(int size, int page) {
		return this.messageDao
			.findAll(PageRequest.of(page, size, Direction.DESC, "message", "id"))
			.getContent()
			.stream()
			.map(MessageBoundary::new)
			.collect(Collectors.toList());
	}

	@Override
	public AuthorBoundary create(AuthorBoundary author) {
		AuthorEntity entity = author.toEntity();
		this.log.trace("storing entity: " + entity);
		entity = this.authorDao.save(entity);
		this.log.debug("stored entity: " + entity);
		return new AuthorBoundary(entity);
	}

	@Override
	public List<AuthorBoundary> getAllAuthors(int size, int page) {
		return this.authorDao
			.findAll(PageRequest.of(page, size, Direction.ASC, "name", "email", "id"))
			.stream()
			.map(AuthorBoundary::new)
			.collect(Collectors.toList());
	}
	
	@Override
	public void bind(String email, String id) {
		AuthorEntity author = this.authorDao.findOneByEmail(email)
			.orElseThrow(()->new RuntimeException("could not find author by email: " + email));
		
		MessageEntity message = this.messageDao.findById(id)
			.orElseThrow(()->new RuntimeException("could not find message: " + id));
		
		author.addMessage(message);
		
		this.authorDao.save(author);
		
	}
	
	@Override
	public List<MessageBoundary> getMessagesByAuthor(String email) {
		AuthorEntity author = this.authorDao.findOneByEmail(email)
				.orElseThrow(()->new RuntimeException("could not find author by email: " + email));
					
		return author
				.getMessages()
				.stream()
				.map(MessageBoundary::new)
				.collect(Collectors.toList());
	}
}








