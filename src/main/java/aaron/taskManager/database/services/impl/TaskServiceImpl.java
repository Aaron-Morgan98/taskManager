package aaron.taskManager.database.services.impl;

import aaron.taskManager.database.domain.entities.TaskEntity;
import aaron.taskManager.database.repositories.TaskRepository;
import aaron.taskManager.database.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskEntity save(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskEntity> findAll() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExists(Long id) {
        return taskRepository.existsById(id);
    }

    @Override
    public TaskEntity update(Long id, TaskEntity taskEntity) {
        taskEntity.setTask_id(id);

        //retrieve task based on id
        return taskRepository.findById(id).map(existingTask -> {
            //update logic
            Optional.ofNullable(taskEntity.getTask_name()).ifPresent(existingTask::setTask_name);
            Optional.ofNullable(taskEntity.getTask_created()).ifPresent(existingTask::setTask_created);
            Optional.ofNullable(taskEntity.getTask_desc()).ifPresent(existingTask::setTask_desc);
            Optional.ofNullable(taskEntity.getTask_due()).ifPresent(existingTask::setTask_due);
            Optional.ofNullable(taskEntity.getTeam()).ifPresent(existingTask::setTask_name);
            Optional.ofNullable(taskEntity.getAssigned_to()).ifPresent(existingTask::setAssigned_to);

            return taskRepository.save(existingTask);
        }).orElseThrow(() -> new RuntimeException("Task does not exist."));
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
