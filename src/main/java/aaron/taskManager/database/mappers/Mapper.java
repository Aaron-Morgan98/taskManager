package aaron.taskManager.database.mappers;

import aaron.taskManager.database.domain.dto.UserDto;
import aaron.taskManager.database.domain.entities.UserEntity;

public interface Mapper <A,B>{

//    B mapTo(A a);
//
//    A mapFrom(B b);

    UserDto mapTo(UserEntity userEntity);

    UserEntity mapFrom(UserDto userDto);
}
