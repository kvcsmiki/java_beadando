package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.user.User;
import com.epam.training.ticketservice.core.user.UserDto;
import com.epam.training.ticketservice.core.user.UserService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserCommandTest {

    private final UserService userService = mock(UserService.class);
    private final UserCommand underTest = new UserCommand(userService);

    @Test
    void testRegister(){
        String expected = "User successfully registered";
        String actual = underTest.register("user","pass");
        assertEquals(expected,actual);
        verify(userService).registerUser("user","pass");
    }

    @Test
    void testSignInShouldWork(){
        UserDto userDto = new UserDto("Test", User.Role.USER);
        when(userService.login("Test", "pass")).thenReturn(Optional.of(userDto));
        String expected = "User Test logged in";

        String actual = underTest.login("Test","pass");

        assertEquals(expected,actual);
        verify(userService).login("Test","pass");
    }
    @Test
    void testLoginShouldNotWork(){
        String expected = "Login failed due to incorrect credentials";
        String actual = underTest.login("asd","asd");

        assertEquals(expected,actual);
    }
    @Test
    void testAdminSignInShouldWork(){
        UserDto userDto = new UserDto("admin", User.Role.ADMIN);
        when(userService.adminLogin("admin", "admin")).thenReturn(Optional.of(userDto));
        String expected = "Admin admin logged in";

        String actual = underTest.adminLogin("admin","admin");

        assertEquals(expected,actual);
        verify(userService).adminLogin("admin","admin");
    }
    @Test
    void testAdminLoginShouldNotWork(){
        String expected = "Login failed due to incorrect credentials";
        String actual = underTest.adminLogin("asd","asd");

        assertEquals(expected,actual);
    }
    @Test
    void testSignOutShouldWork(){
        UserDto userDto = new UserDto("Test", User.Role.USER);
        when(userService.logout()).thenReturn(Optional.of(userDto));
        String expected = "User Test successfully logged out";

        String actual = underTest.logout();

        assertEquals(expected,actual);
        verify(userService).logout();
    }
    @Test
    void testSignOutShouldNotWork(){
        String expected = "Nobody was logged in";
        String actual = underTest.logout();

        assertEquals(expected,actual);
    }
    @Test
    void testDescribeShouldWorkUser(){
        UserDto userDto = new UserDto("Test", User.Role.USER);
        when(userService.describe()).thenReturn(Optional.of(userDto));
        String expected = "Signed in with account: Test";

        String actual = underTest.describe();

        assertEquals(expected,actual);
        verify(userService).describe();
    }
    @Test
    void testDescribeShouldWorkAdmin(){
        UserDto userDto = new UserDto("admin", User.Role.ADMIN);
        when(userService.describe()).thenReturn(Optional.of(userDto));
        String expected = "Signed in with privileged account: admin";

        String actual = underTest.describe();

        assertEquals(expected,actual);
        verify(userService).describe();
    }
    @Test
    void testDescribeShouldNotWork(){
        when(userService.describe()).thenReturn(Optional.empty());
        String expected = "You are not signed in";
        String actual = underTest.describe();

        assertEquals(expected,actual);
        verify(userService).describe();
    }

}