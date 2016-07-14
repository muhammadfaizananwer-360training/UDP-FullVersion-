package com.softech.ls360.dashboard.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.softech.ls360.dashboard.model.LmsUserPrincipal;
import com.softech.ls360.dashboard.service.LmsUserDetailsService;
import com.softech.ls360.dashboard.service.MessageService;

@Service
public class LmsUserDetailsServiceImpl implements LmsUserDetailsService {

	@Inject
	private MessageService messageService;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		
		UserDetails userDetails = null;
		
		Set<String> userRoles = getUserRoles();
		if (!CollectionUtils.isEmpty(userRoles)) {
			List<GrantedAuthority> authorities = getGrantedAuthorityList(userRoles);
			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;
			String password = "a123456789";
			userDetails = new LmsUserPrincipal(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		}
		
		return userDetails;
	}
	
	private Set<String> getUserRoles() {
		
		String administratorRole = "ROLE_ADMINISTRATOR";
		String managerRole = "ROLE_ADMINISTRATOR";
		
		Set<String> userRoles = new HashSet<>();
		userRoles.add(administratorRole);
		userRoles.add(managerRole);
		return userRoles;
	}
	
	private List<GrantedAuthority> getGrantedAuthorityList(Set<String> authorityRole) {
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(String role :  authorityRole) {
			GrantedAuthority authority = getGrantedAuthority(role);
			authorities.add(authority);
		}
		
		return authorities;
	}
	
	private GrantedAuthority getGrantedAuthority(String role) {
		GrantedAuthority authority = new SimpleGrantedAuthority(role);
		return authority;
	}


}
