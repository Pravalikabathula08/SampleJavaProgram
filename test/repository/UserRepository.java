package com.rgt.test.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rgt.test.entity.User;

@Repository
public class UserRepository {

    /**
     * The directory where user data files are stored.
     */
    private final String dataDir = "C:/Users/PravalikaBathula/Downloads/SwaggerProject/UserManagement/data/";

    /**
     * Saves a user to a data file.
     *
     * @param user The user to be saved.
     * @return The saved user.
     * @throws IOException If an I/O error occurs.
     */
    public User save(User user) throws IOException {
        File file = new File(dataDir + user.getId() + ".ser");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(user);
        }
        return user;
    }

    /**
     * Updates an existing user in the data file.
     *
     * @param user The user to be updated.
     * @return The updated user.
     * @throws IOException If an I/O error occurs.
     */
    public User updateUser(User user) throws IOException {
        String filename = dataDir + user.getId() + ".ser";
        File file = new File(filename);
        if (file.exists()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(user);
            }
            return user;
        } else {
            throw new IllegalArgumentException("User not found with ID: " + user.getId());
        }
    }

    /**
     * Retrieves a list of all users.
     *
     * @return The list of all users.
     * @throws IOException 
     * @throws ClassNotFoundException If the user class is not found.
     */
    public List<User> getAllUsers() throws IOException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        File dir = new File(dataDir);
        for (File file : dir.listFiles()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                users.add((User) in.readObject());
            }
        }
        return users;
    }

    /**
     * Deletes a user data file by ID.
     *
     * @param id The ID of the user to be deleted.
     * @throws IOException If an I/O error occurs.
     */
    public void delete(long id) throws IOException {
        File file = new File(dataDir + id + ".ser");
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Retrieves the data directory path.
     *
     * @return The data directory path.
     */
    public String getDataDirectory() {
        return dataDir;
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user.
     * @return The user with the specified ID, or null if not found.
     * @throws IOException 
     * @throws ClassNotFoundException If the user class is not found.
     */
    public User getUserById(long id) throws IOException, ClassNotFoundException {
        String filename = dataDir + id + ".ser";
        File file = new File(filename);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (User) ois.readObject();
            }
        }
        return null;
    }
}