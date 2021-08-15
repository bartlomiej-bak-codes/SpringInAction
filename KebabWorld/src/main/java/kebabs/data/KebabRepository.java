package kebabs.data;

import kebabs.Kebab;
import org.springframework.data.repository.CrudRepository;

public interface KebabRepository extends CrudRepository<Kebab, Long> {
}
