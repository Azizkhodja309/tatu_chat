package org.example.mychat.config.security;

import org.example.mychat.model.Member;
import org.example.mychat.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository repository;

    public CustomUserDetailsService(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member user = repository.getByNickname(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new AuthUserDetails(user.getId(), new User(user.getNickname(), user.getPassword(), Collections.emptyList()));
    }
}
