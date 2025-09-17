package com.zazhi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zazhi.mapper.UserMapper;
import com.zazhi.pojo.LoginUserDetails;
import com.zazhi.pojo.RoleAndPermission;
import com.zazhi.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author lixh
 * @since 2025/9/9 11:56
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        RoleAndPermission rp = userMapper.getRolesAndPermissions(user.getId());
        return new LoginUserDetails(user, rp.getRoles(), rp.getPermissions());
    }
}
