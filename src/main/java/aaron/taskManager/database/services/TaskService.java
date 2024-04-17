package aaron.taskManager.database.services;

import aaron.taskManager.database.domain.entities.TaskEntity;

import java.util.List;

public interface TaskService {
    TaskEntity save(TaskEntity taskEntity);

    List<TaskEntity> findAll();

    boolean isExists(Long id);

    TaskEntity update(Long id, TaskEntity taskEntity);

    void delete(Long id);
}
