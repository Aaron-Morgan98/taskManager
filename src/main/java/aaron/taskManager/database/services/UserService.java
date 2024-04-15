package aaron.taskManager.database.services;

import aaron.taskManager.database.domain.entities.UserEntity;

import java.util.Optional;


public interface UserService {


   UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findOne(Long user_id);


    String login(String email, String password);

    void delete(Long Id);
}
