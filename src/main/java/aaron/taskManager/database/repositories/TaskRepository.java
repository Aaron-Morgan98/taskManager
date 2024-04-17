package aaron.taskManager.database.repositories;

import aaron.taskManager.database.domain.entities.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
}
