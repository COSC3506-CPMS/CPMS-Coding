package com.cpms.cpms.services;

import com.cpms.cpms.dao.UserDAO;
import com.cpms.cpms.entities.User;
import com.cpms.cpms.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService; // Service under test
    private UserDAO userDAOMock; // Mocked DAO dependency

    @BeforeEach
    public void setUp() {
        userDAOMock = Mockito.mock(UserDAO.class); // Mock the DAO
        userService = new UserService(userDAOMock); // Inject mock DAO into the service
    }

    @Test
    public void shouldAddUserWhenUserNameIsValid() {
        User user = new User();
        user.setUserName("validUser");

        userService.addUser(user);

        verify(userDAOMock, times(1)).addUser(user); // Verify DAO interaction
    }

    @Test
    public void shouldThrowExceptionWhenUserNameIsNull() {
        User user = new User();
        user.setUserName(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.addUser(user));
        assertEquals("User name cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenUserNameIsEmpty() {
        User user = new User();
        user.setUserName("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> userService.addUser(user));
        assertEquals("User name cannot be null or empty.", exception.getMessage());
    }

    @Test
    public void shouldGetUserById() {
        User user = new User();
        user.setUserName("test_user");

        when(userDAOMock.getUser(1)).thenReturn(user); // Mock DAO behavior

        User result = userService.getUser(1);

        assertNotNull(result);
        assertEquals("test_user", result.getUserName());
        verify(userDAOMock, times(1)).getUser(1); // Verify DAO interaction
    }

    @Test
    public void shouldUpdateUser() {
        User user = new User();
        user.setUserID(1);
        user.setUserName("updated_user");

        userService.updateUser(user);

        verify(userDAOMock, times(1)).updateUser(user); // Verify DAO interaction
    }

    @Test
    public void shouldDeleteUserById() {
        userService.deleteUser(5);

        verify(userDAOMock, times(1)).deleteUser(5); // Verify DAO interaction
    }

    @Test
    public void shouldGetAllUsers() {
        User user1 = new User();
        user1.setUserName("user1");
        User user2 = new User();
        user2.setUserName("user2");

        List<User> mockList = Arrays.asList(user1, user2);
        when(userDAOMock.getAllUsers()).thenReturn(mockList); // Mock DAO behavior

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUserName());
        assertEquals("user2", result.get(1).getUserName());
        verify(userDAOMock, times(1)).getAllUsers(); // Verify DAO interaction
    }
}