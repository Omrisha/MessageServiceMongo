package demo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageBoundaryController {
	private MessageService messageService;
	private Log log = LogFactory.getLog(MessageBoundaryController.class);

	@Autowired
	public MessageBoundaryController(MessageService messageService) {
		this.messageService = messageService;
	}

	// C[RUD]
	@RequestMapping(path = "/messages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public MessageBoundary create(@RequestBody MessageBoundary message) {
		return this.messageService.create(message);
	}
	
	@RequestMapping(path = "/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MessageBoundary[] getAll (
			@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page) {
		return this.messageService.getAll(size, page)
			.toArray(new MessageBoundary[0]);
	}
	
	@RequestMapping(path="/messages/authors", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public AuthorBoundary create (@RequestBody AuthorBoundary author) {
		this.log.debug("creating data: "  + author);
		return this.messageService.create(author);
	}
	

	@RequestMapping(path = "/messages/{id}/{email}", method = RequestMethod.PUT)
	public void bind (
			@PathVariable("email") String email, 
			@PathVariable("id") String id) {
		this.messageService.bind(email, id);
	}
	
	@RequestMapping(path = "/messages/byAuthor/{email}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public MessageBoundary[] getMessagesByAuthor (@PathVariable("email") String email){
		return this.messageService
			.getMessagesByAuthor(email)
			.toArray(new MessageBoundary[0]);
	}
}
