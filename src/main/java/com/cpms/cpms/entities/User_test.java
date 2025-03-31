package com.cpms.cpms.entities;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {

    @Test
    public void testUserSettersAndGetters() {
        User user = new User();

        int userID = 1;
        String userName = "john_doe";
        String password = "hashed_pw_123";
        String role = "Admin";
        String permissions = "READ,WRITE,DELETE";
      
        user.setUserID(userID);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(role);
        user.setPermissions(permissions);

        Assert.assertEquals(userID, user.getUserID());
        Assert.assertEquals(userName, user.getUserName());
        Assert.assertEquals(password, user.getPassword());
        Assert.assertEquals(role, user.getRole());
        Assert.assertEquals(permissions, user.getPermissions());
    }
}
