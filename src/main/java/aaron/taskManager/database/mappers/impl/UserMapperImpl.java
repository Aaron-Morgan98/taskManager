package aaron.taskManager.database.mappers.impl;

import aaron.taskManager.database.domain.dto.TaskDto;
import aaron.taskManager.database.domain.dto.UserDto;
import aaron.taskManager.database.domain.entities.TaskEntity;
import aaron.taskManager.database.domain.entities.UserEntity;
import aaron.taskManager.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public  class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    private ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    @Override
    public TaskDto mapTo(TaskEntity taskEntity) {
        return null;
    }

    @Override
    public TaskEntity mapFrom(TaskDto taskDto) {
        return null;
    }
}
