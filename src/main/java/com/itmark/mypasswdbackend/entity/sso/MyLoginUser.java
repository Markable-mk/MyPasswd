package com.itmark.mypasswdbackend.entity.sso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyLoginUser implements UserDetails {

    private MarkUser user;

    private List<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (null!=permissions){
            Set<SimpleGrantedAuthority> collect = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            return collect;
        }
        return null;
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
    public boolean isAccountNonExpired() {
        // 先默认用户是正常的
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 先默认用户是正常的
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 先默认用户是正常的
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 先默认用户是正常的
        return true;
    }
}
