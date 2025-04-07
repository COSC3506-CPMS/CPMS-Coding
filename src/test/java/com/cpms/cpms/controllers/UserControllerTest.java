package com.cpms.cpms.controllers;

import com.cpms.cpms.services.UserService;
import com.cpms.cpms.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    private UserController userController; // Controller under test
    private UserService userServiceMock; // Mocked UserService

    @BeforeEach
    public void setUp() {
        userServiceMock = Mockito.mock(UserService.class); // Mock the UserService
        userController = new UserController(userServiceMock); // Inject the mock into the controller
    }

    @Test
    public void testAddUser() {
        // Arrange: Create dummy user data
        String name = "testUser";
        String password = "secure123";
        User.Role role = User.Role.ADMIN;
        String permissions = "READ,WRITE";

        // Act: Call the controller method
        userController.addUser(name, password, role, permissions);

        // Assert: Verify the service's addUser method was called with the correct data
        verify(userServiceMock, times(1)).addUser(any(User.class));
    }

    @Test
    public void testGetUserById() {
        // Arrange: Mock the service to return a user
        User mockUser = new User();
        mockUser.setUserName("testUser");
        when(userServiceMock.getUser(1)).thenReturn(mockUser);

        // Act: Call the controller method
        User result = userController.getUserById(1);

        // Assert: Verify the result and service call
        assertNotNull(result);
        assertEquals("testUser", result.getUserName());
        verify(userServiceMock, times(1)).getUser(1);
    }

    @Test
    public void testUpdateUser() {
        // Arrange: Create a dummy user object
        User user = new User();
        user.setUserID(1);
        user.setUserName("updatedUser");

        // Act: Call the controller method
        userController.updateUser(user);

        // Assert: Verify the service's updateUser method was called
        verify(userServiceMock, times(1)).updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        // Act: Call the controller method
        userController.deleteUser(5);

        // Assert: Verify the service's deleteUser method was called with the correct ID
        verify(userServiceMock, times(1)).deleteUser(5);
    }

    @Test
    public void testGetAllUsers() {
        // Arrange: Mock the service to return a list of users
        User user1 = new User();
        user1.setUserName("user1");
        User user2 = new User();
        user2.setUserName("user2");
        List<User> mockUsers = Arrays.asList(user1, user2);

        when(userServiceMock.getAllUsers()).thenReturn(mockUsers);

        // Act: Call the controller method
        List<User> result = userController.getAllUsers();

        // Assert: Verify the result and service call
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUserName());
        assertEquals("user2", result.get(1).getUserName());
        verify(userServiceMock, times(1)).getAllUsers();
    }
}