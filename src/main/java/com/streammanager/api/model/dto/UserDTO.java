package com.streammanager.api.model.dto;

import com.streammanager.api.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
	@Id
	private String id;
	
	@NonNull
	@Indexed(unique = true)
	private String username;
	
	@NonNull
	@Indexed(unique = true)
	private String email;
	
	@NonNull
	private String password;
	
	private String avatar;
	
	private List<Role> roles = List.of(Role.USER);
}
