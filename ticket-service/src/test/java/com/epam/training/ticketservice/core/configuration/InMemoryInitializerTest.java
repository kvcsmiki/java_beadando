package com.epam.training.ticketservice.core.configuration;

import com.epam.training.ticketservice.core.user.User;
import com.epam.training.ticketservice.core.user.UserRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class InMemoryInitializerTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final InMemoryInitializer underTest = new InMemoryInitializer(userRepository);

    @Test
    void testInit(){
        User admin = new User("admin", "admin", User.Role.ADMIN);
        underTest.init();
        verify(userRepository).save(admin);
    }

}