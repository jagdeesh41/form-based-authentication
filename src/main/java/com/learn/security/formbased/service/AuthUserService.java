package com.learn.security.formbased.service;

import com.learn.security.formbased.dto.AuthUserDto;
import com.learn.security.formbased.entity.AuthUser;
import com.learn.security.formbased.repo.AuthUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthUserService implements UserDetailsService {

    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private PasswordEncoder customPasswordEncoder;

    public void save(AuthUserDto authUserDto)
    {
        AuthUser authUser = new AuthUser();
        BeanUtils.copyProperties(authUserDto,authUser);
        log.info("auth user {}",authUser);
        authUser.setPassword(customPasswordEncoder.encode(authUser.getPassword()));
        AuthUser savedAuthUser = authUserRepository.save(authUser);
        log.info("auth user {}",savedAuthUser);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return authUser;

    }
}
