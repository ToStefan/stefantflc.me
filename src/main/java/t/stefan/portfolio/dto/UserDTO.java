package t.stefan.portfolio.dto;

import java.io.Serializable;

import t.stefan.portfolio.entity.User;

public class UserDTO implements Serializable {

	private Long id;
	private String username;
	private String password;

	public UserDTO() {
	}

	public UserDTO(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public UserDTO(User user) {
		this(user.getId(), user.getUsername(), user.getPassword());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
