package com.rgt.test.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgt.test.entity.User;
import com.rgt.test.repository.UserRepository;
@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     * @throws IOException 
     * @throws ClassNotFoundException If the user class is not found.
     */
    public List<User> getAllUsers() throws IOException, ClassNotFoundException {
        return userRepository.getAllUsers();
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The retrieved user.
     * @throws IOExceptionIf 
     * @throws ClassNotFoundException If the user class is not found.
     */
    public User getUserById(long id) throws IOException, ClassNotFoundException {
        return userRepository.getUserById(id);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete.
     * @return A success message if the user is deleted.
     * @throws IOException 
     * @throws ClassNotFoundException If the user class is not found.
     * @throws IllegalArgumentException If the user is not found.
     */
    public String deleteUser(long id) throws IOException, ClassNotFoundException {
        User existingUser = userRepository.getUserById(id);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.delete(id);
        return "User deleted successfully";
    }
}