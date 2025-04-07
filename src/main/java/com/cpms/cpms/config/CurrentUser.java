<<<<<<< HEAD
package com.cpms.cpms.config;

import com.cpms.cpms.entities.User;

public class CurrentUser {
    private static ThreadLocal<User> loggedInUser = new ThreadLocal<>(); // Ensures thread safety

    public static User getLoggedInUser() {
        return loggedInUser.get(); // Safely retrieves the user for the current thread
    }

    public static void setLoggedInUser(User user) {
        loggedInUser.set(user); // Sets the user globally for the current thread
    }

    public static void clear() {
        loggedInUser.remove(); // Clears the user when no longer needed
    }
}
=======

package com.cpms.cpms.config;
import com.cpms.cpms.entities.User;
public class CurrentUser {
   private static ThreadLocal<User> loggedInUser = new ThreadLocal<>(); // Ensures thread safety
   public static User getLoggedInUser() {
       return loggedInUser.get(); // Safely retrieves the user for the current thread
   }
   public static void setLoggedInUser(User user) {
       loggedInUser.set(user); // Sets the user globally for the current thread
   }
   public static void clear() {
       loggedInUser.remove(); // Clears the user when no longer needed
   }
}

>>>>>>> komal-finalWork
