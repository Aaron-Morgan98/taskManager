package aaron.taskManager.database.mappers.impl;

import aaron.taskManager.database.domain.dto.TaskDto;
import aaron.taskManager.database.domain.dto.UserDto;
import aaron.taskManager.database.domain.entities.TaskEntity;
import aaron.taskManager.database.domain.entities.UserEntity;
import aaron.taskManager.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public  class TaskMapperImpl implements Mapper<TaskEntity, TaskDto>{

    private ModelMapper modelMapper;

    public TaskMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return null;
    }

    @Override
    public TaskDto mapTo(TaskEntity taskEntity) {
        return modelMapper.map(taskEntity, TaskDto.class);
    }

    @Override
    public TaskEntity mapFrom(TaskDto taskDto) {
        return modelMapper.map(taskDto, TaskEntity.class);
    }
}
