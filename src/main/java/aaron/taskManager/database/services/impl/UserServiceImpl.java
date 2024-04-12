package aaron.taskManager.database.services.impl;

import aaron.taskManager.database.domain.entities.UserEntity;
import aaron.taskManager.database.repositories.UserRepository;
import aaron.taskManager.database.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public UserEntity save(UserEntity userEntity){
        String hashedPassword = BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt());
        userEntity.setPassword(hashedPassword);
        return userRepository.save(userEntity);
    }
}
