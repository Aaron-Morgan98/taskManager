package aaron.taskManager.database.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private long user_id;
    private String username;
    private String password;
    private String email;
    private String team;


    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
    public long getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
