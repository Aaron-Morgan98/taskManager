package aaron.taskManager.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
//@EnableWebSecurity
public class SecurityConfig {


    //http security
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((auth) -> auth
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(withDefaults());
//
//
//        return http.build();
//    }

//    @Bean
//    public String generateAuthToken(UserDto userDto){
//        String token = Jwts.builder()
//                .setSubject(String.valueOf(userDto.getUser_id()))
//                .claim("email",userDto.getEmail())
//                .signWith(SignatureAlgorithm.HS512, "123") // TODO: change secret key
//                .compact();
//        return token;
//    }

    //password hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
