package t.stefan.portfolio.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import t.stefan.portfolio.entity.RoleName;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO {

    private Long id;
    private RoleName name;

}