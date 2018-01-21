package com.zooplus.sdc.converter.services;

import com.zooplus.sdc.converter.entities.UserEntity;
import com.zooplus.sdc.converter.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willAnswer;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    public static final String USER_NAME = "Dane";

    @Mock
    private UserRepository userRepository;

    @Spy
    @InjectMocks
    private DefaultUserService defaultUserService;

    @Before
    public void before() {
        willAnswer(invocationOnMock -> {
            UserEntity userEntity = (UserEntity) invocationOnMock.getArguments()[0];
            userEntity.setId(1L);
            return userEntity;
        })
                .given(userRepository)
                .save(Matchers.<UserEntity>any());
    }

    @Test
    public void findUser() {
        given(userRepository.findByName(USER_NAME))
                .willReturn(Optional.of(UserEntity.builder()
                        .id(1L)
                        .name(USER_NAME)
                        .build()));

        Optional<UserEntity> userEntity = defaultUserService.findByName(USER_NAME);

        assertTrue(userEntity.isPresent());
        assertEquals(USER_NAME, userEntity.get().getName());
    }

    @Test
    public void createUser() {
        UserEntity userEntity = defaultUserService.createUser(USER_NAME);

        assertNotNull(userEntity.getId());
        assertEquals(USER_NAME, userEntity.getName());
    }
}
