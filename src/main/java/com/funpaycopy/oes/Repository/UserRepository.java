package com.funpaycopy.oes.Repository;

import com.funpaycopy.oes.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginAndActive(String login, Boolean active);
    User findByLogin(String login);
    User findByEmail(String email);
    List<User> findAllByLoginContainsAndLoginNot(String login, String user);
    List<User> findAllByLoginNot(String user);
}
