package t.stefan.portfolio.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import t.stefan.portfolio.entity.Role;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private Boolean isEnabled;
    private String email;
    private String username;
    private String password;
    private List<Role> roles;
    private String information;

}