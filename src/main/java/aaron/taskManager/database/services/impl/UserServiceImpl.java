package aaron.taskManager.database.services.impl;

import aaron.taskManager.database.domain.dto.UserDto;
import aaron.taskManager.database.domain.entities.UserEntity;
import aaron.taskManager.database.repositories.UserRepository;
import aaron.taskManager.database.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Optional<UserEntity> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public String login(String email, String password) {
        UserDto userDto = userRepository.findByEmail(email);

        //if user not found, throw exception
        if(userDto == null || password == null){
            return null;
        }

        //verify passwords match
        if(passwordEncoder.matches(password, userDto.getPassword())){
            return generateAuthToken(userDto);
        } else{
            //incorrect password
            return null;
        }
    }


    public String generateAuthToken(UserDto userDto){
        String token = Jwts.builder()
                .setSubject(String.valueOf(userDto.getUser_id()))
                .claim("email",userDto.getEmail())
                .signWith(SignatureAlgorithm.HS512, "123") // TODO: change secret key
                .compact();
        return token;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
