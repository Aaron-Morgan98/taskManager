package aaron.taskManager.database.controllers;

import aaron.taskManager.database.domain.dto.UserDto;
import aaron.taskManager.database.domain.entities.UserEntity;
import aaron.taskManager.database.mappers.Mapper;
import aaron.taskManager.database.services.UserService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private Mapper<UserEntity, UserDto> userMapper;
    private UserService userService;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // user create endpoint
    @PermitAll
    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUserEntity = userService.save(userEntity);


        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    //TODO GET endpoint for user
}
