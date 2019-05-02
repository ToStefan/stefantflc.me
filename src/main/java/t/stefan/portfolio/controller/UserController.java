package t.stefan.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import t.stefan.portfolio.dto.UserDTO;
import t.stefan.portfolio.entity.User;
import t.stefan.portfolio.service.UserService;

@RestController
@RequestMapping(value = "api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping()
	public ResponseEntity<List<UserDTO>> getUsers() {

		List<UserDTO> usersDTO = new ArrayList<UserDTO>();
		List<User> users = userService.findAll();

		for (User user : users) {
			usersDTO.add(new UserDTO(user));
		}
		return new ResponseEntity<List<UserDTO>>(usersDTO, HttpStatus.OK);
	}

}
