package com.cpms.cpms.services;

import com.cpms.cpms.dao.UserDAO;
import com.cpms.cpms.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;
    private UserDAO userDAOMock;

    @Before
    public void setUp() {
        userDAOMock = Mockito.mock(UserDAO.class);
        userService = new UserService();

        try {
            java.lang.reflect.Field daoField = UserService.class.getDeclaredField("userDAO");
            daoField.setAccessible(true);
            daoField.set(userService, userDAOMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock DAO", e);
        }
    }

    @Test
    public void testAddUser_validUser() {
        User user = new User();
        user.setUserName("validUser");

        userService.addUser(user);
        verify(userDAOMock).addUser(user);
    }

    @Test
    public void testAddUser_nullUserName() {
        User user = new User();
        user.setUserName(null);

        userService.addUser(user);
        verify(userDAOMock, never()).addUser(any());
    }

    @Test
    public void testAddUser_emptyUserName() {
        User user = new User();
        user.setUserName("");

        userService.addUser(user);
        verify(userDAOMock, never()).addUser(any());
    }

    @Test
    public void testGetUser() {
        User user = new User();
        when(userDAOMock.getUser(1)).thenReturn(user);

        User result = userService.getUser(1);
        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        userService.updateUser(user);
        verify(userDAOMock).updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(5);
        verify(userDAOMock).deleteUser(5);
    }

    @Test
    public void testGetAllUsers() {
        List<User> mockList = Arrays.asList(new User(), new User());
        when(userDAOMock.getAllUsers()).thenReturn(mockList);

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
    }
}
