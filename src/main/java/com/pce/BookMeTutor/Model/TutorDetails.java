package com.pce.BookMeTutor.Model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pce.BookMeTutor.Model.Dao.Tutor;

public class TutorDetails implements UserDetails {

	private static final long serialVersionUID = -1694632384858836272L;

	private String userName;
	private String password;
	private boolean enabled;

	public TutorDetails(Tutor user) {
		this.userName = user.getEmail();
		this.password = user.getPassword();
		this.enabled = user.isVerified();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return new ArrayList<GrantedAuthority>();
	}

	@Override
	public String getPassword() {

		return this.password;
	}

	@Override
	public String getUsername() {

		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
