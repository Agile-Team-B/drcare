package ie.ait.bteam.drcare.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	public static String encode(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}
