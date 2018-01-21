package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.entities.UserEntity;
import com.zooplus.sdc.converter.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findByName(String name) {
        log.info("Searching for user: {}", name);
        return userRepository.findByName(name);
    }

    @Override
    public UserEntity createUser(String name) {
        log.info("Creating new user: {}", name);
        return userRepository.save(UserEntity.builder()
                .name(name)
                .build());
    }
}
