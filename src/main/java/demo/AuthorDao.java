package demo;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorDao extends PagingAndSortingRepository<AuthorEntity, String>{
	public Optional<AuthorEntity> findOneByEmail (@Param("email") String email);// MATCH ....
}
