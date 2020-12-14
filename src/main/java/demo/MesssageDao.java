package demo;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MesssageDao extends PagingAndSortingRepository<MessageEntity, String>{
//JpaRepository<MessageEntity, Long>{

}
