package aaron.taskManager.database.controllers;

import aaron.taskManager.database.domain.dto.UserDto;
import aaron.taskManager.database.domain.entities.UserEntity;
import aaron.taskManager.database.mappers.Mapper;
import aaron.taskManager.database.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {
    private Mapper<UserEntity, UserDto> userMapper;
    private UserService userService;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // user create endpoint
    @PostMapping(path = "/users/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUserEntity = userService.save(userEntity);


        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    //GET endpoint for user id
    @GetMapping(path = "/users/{user_id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("user_id") Long user_id){
        Optional<UserEntity> foundUser = userService.findOne(user_id);

        // if id is found, return info, error if id not found
        return foundUser.map(userEntity -> {
            UserDto userDto = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    //login - send JWT auth token if email/password = ok
    //TODO: modification needed to accept user input (when front end is created)
    //get email and password which the user has inputted
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto user){
        String email = user.getEmail();
        String password = user.getPassword();

        String token = userService.login(email,password);

        if(token != null){
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
        }


}
