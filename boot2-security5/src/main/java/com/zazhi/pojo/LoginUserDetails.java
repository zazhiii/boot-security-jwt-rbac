package com.zazhi.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author lixh
 * @since 2025/9/9 12:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDetails implements UserDetails {

    private User user;

    private Set<String> roles;

    private Set<String> permissions;

    /**
     * 缓存权限信息，避免每次调用都重新创建；并且不序列化
     *
     */
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 避免每次调用都重新创建
        if (authorities != null) {
            return authorities;
        }

        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach(
                role -> grantedAuthorities.add(new SimpleGrantedAuthority(role))
        );
        permissions.forEach(
                permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission))
        );
        this.authorities = grantedAuthorities;
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return true;
    }
}
