package aaron.taskManager.database.controllers;

import aaron.taskManager.database.domain.dto.TaskDto;
import aaron.taskManager.database.domain.entities.TaskEntity;
import aaron.taskManager.database.mappers.Mapper;
import aaron.taskManager.database.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    private Mapper<TaskEntity, TaskDto> taskMapper;
    private TaskService taskService;

    public TaskController(Mapper<TaskEntity, TaskDto> taskMapper, TaskService taskService) {
        this.taskMapper = taskMapper;
        this.taskService = taskService;
    }

    @PostMapping(path = "/tasks/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task){
        TaskEntity taskEntity = taskMapper.mapFrom(task);
        TaskEntity savedTaskEntity = taskService.save(taskEntity);

        return new ResponseEntity<>(taskMapper.mapTo(savedTaskEntity), HttpStatus.CREATED);
    }


    // list all endpoint
    @GetMapping(path = "/tasks")
    public List<TaskDto> listTasks(){
        List<TaskEntity> tasks = taskService.findAll();
        return tasks.stream().map(taskMapper::mapTo).collect(Collectors.toList());
    }

    //PATCH endpoint for updates (maybe include a PUT as well?)
    //TODO: TEST
    @PatchMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDto> updateTasks(
            @PathVariable("id") Long id,
            @RequestBody TaskDto taskDto){

        // validation to check if id exists
        if(!taskService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TaskEntity taskEntity = taskMapper.mapFrom(taskDto);
        TaskEntity updatedTask = taskService.update(id, taskEntity);

        return new ResponseEntity<>(taskMapper.mapTo(updatedTask), HttpStatus.OK);

    }

    //DELETE endpoint
    @DeleteMapping(path="/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable("id") Long id){
        taskService.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
