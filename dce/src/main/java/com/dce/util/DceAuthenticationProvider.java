package com.dce.util;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component("dceAuthenticationProvoder")
public class DceAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName().trim();
		String password = authentication.getCredentials().toString().trim();

		SimpleGrantedAuthority grantedAuths = new SimpleGrantedAuthority("ROLE_ADMIN");
		Collection<SimpleGrantedAuthority> auth = new ArrayList<>();

		auth.add(grantedAuths);

		Authentication auth1 = new UsernamePasswordAuthenticationToken(userName, password, auth);
		return auth1;

	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
