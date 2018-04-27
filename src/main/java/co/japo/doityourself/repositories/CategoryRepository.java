package co.japo.doityourself.repositories;

import co.japo.doityourself.domain.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByCategoryNameIgnoreCase(String description);

}
