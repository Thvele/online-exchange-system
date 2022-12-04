package com.funpaycopy.oes.Repository;

import com.funpaycopy.oes.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginAndActive(String login, Boolean active);
}
