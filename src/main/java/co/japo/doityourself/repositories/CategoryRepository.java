package co.japo.doityourself.repositories;

import co.japo.doityourself.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByCategoryNameIgnoreCase(String description);

}
