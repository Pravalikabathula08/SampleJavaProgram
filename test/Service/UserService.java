package com.rgt.test.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rgt.test.entity.User;
import com.rgt.test.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static long nextId = 1;

    /**
     * Creates a new user.
     *
     * @param user The user to create.
     * @return The created user.
     * @throws IOException 
     */
    public User createUser(User user) throws IOException {
//      user.setId(System.currentTimeMillis());
        user.setId(nextId++);
        return userRepository.save(user);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The retrieved user.
     * @throws IOException 
     * @throws ClassNotFoundException If the user class is not found.
     */
    public User getUser(long id) throws IOException, ClassNotFoundException {
        return userRepository.getUserById(id);
    }

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
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete.
     * @throws IOException If an I/O error occurs.
     */
    public void deleteUser(long id) throws IOException {
        userRepository.delete(id);
    }

    /**
     * Updates a user.
     *
     * @param id   The ID of the user to update.
     * @param user The updated user.
     * @return The updated user.
     * @throws IOException
     * @throws ClassNotFoundException If the user class is not found.
     */
    public User updateUser(long id, User user) throws IOException, ClassNotFoundException {
        return userRepository.updateUser(user);
    }

}