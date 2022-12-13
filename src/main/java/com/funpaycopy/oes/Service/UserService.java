package com.funpaycopy.oes.Service;

import com.funpaycopy.oes.Model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    boolean deleteUser(Long id);
    void deleteUser_(Long id);

    boolean deactivateUser(Long id);

    User updateUser(Long id, User user);
    void update(Long id, User user);
}
