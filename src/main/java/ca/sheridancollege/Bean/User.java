package ca.sheridancollege.Bean;

import java.io.*;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

	private long userId;
	private String userName;
	private String encryptedPassword;
}
