package com.example.diary.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.diary.entity.User;

public class UserDetailsImpl implements UserDetails {
	private final User user;
	private final Collection<GrantedAuthority> authorities;
	
	public UserDetailsImpl(User user, Collection<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}
	
	public int getId() {
		return user.getUserId();
	}
	
	@Override
	public String getPassword() {
		return user.getUserPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUserName();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() { //アカウントが期限切れでなければtrueを返す。
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() { //ユーザーがロックされていなければtrueを返す。
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() { //ユーザーのパスワードが期限切れでなければtrueを返す。
		return true;
	}
	
	@Override
	public boolean isEnabled() { //ユーザーが有効であればtrueを返す。
		return true;
	}
	
}
