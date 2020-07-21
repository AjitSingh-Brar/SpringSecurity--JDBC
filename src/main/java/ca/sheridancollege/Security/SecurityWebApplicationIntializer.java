package ca.sheridancollege.Security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationIntializer extends AbstractSecurityWebApplicationInitializer {

	
	public SecurityWebApplicationIntializer()
	{
		super(SecurityConfig.class);
	}
}
