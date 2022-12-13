package com.funpaycopy.oes.Service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.funpaycopy.oes.Model.User;
import com.funpaycopy.oes.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {

        User user1 = new User();
        BeanUtils.copyProperties(user, user1);
        userRepository.save(user1);
        return userRepository.findByLogin(user1.getLogin());
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean deleteUser(Long id) {

        userRepository.deleteById(id);
        return true;
    }

    @Override
    public void deleteUser_(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    public boolean deactivateUser(Long id) {

        User user = userRepository.findById(id).orElseThrow();
        user.setActive(false);
        userRepository.save(user);
        return true;
    }

    @Override
    public User updateUser(Long id, User user) {

        User upd_user = userRepository.findById(id).orElseThrow();

        upd_user.setLogin(user.getLogin());
        upd_user.setPassword(user.getPassword());
        upd_user.setBalance(user.getBalance());
        upd_user.setEmail(user.getEmail());
        upd_user.setActive(user.getActive());
        upd_user.setRoles(user.getRoles());
        upd_user.setGoods(user.getGoods());
        upd_user.setBuys(user.getBuys());
        upd_user.setRequestTSCollection(user.getRequestTSCollection());
        upd_user.setRequestMRGCollection(user.getRequestMRGCollection());

        userRepository.save(upd_user);
        return userRepository.findByLogin(upd_user.getLogin());
    }

    @Override
    public void update(Long id, User user) {

        User upd_user = userRepository.findById(id).orElseThrow();

        upd_user.setLogin(user.getLogin());
        upd_user.setPassword(user.getPassword());
        upd_user.setBalance(user.getBalance());
        upd_user.setEmail(user.getEmail());
        upd_user.setActive(user.getActive());
        upd_user.setRoles(user.getRoles());
        upd_user.setGoods(user.getGoods());
        upd_user.setBuys(user.getBuys());
        upd_user.setRequestTSCollection(user.getRequestTSCollection());
        upd_user.setRequestMRGCollection(user.getRequestMRGCollection());

        userRepository.save(upd_user);
    }
}
