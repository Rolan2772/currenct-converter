package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.entities.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> findByName(String name);

    UserEntity createUser(String name);
}
