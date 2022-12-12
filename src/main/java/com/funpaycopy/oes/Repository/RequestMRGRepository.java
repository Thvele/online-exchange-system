package com.funpaycopy.oes.Repository;

import com.funpaycopy.oes.Model.RequestMRG;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestMRGRepository extends JpaRepository<RequestMRG, Long> {

    RequestMRG findByUserLogin(String login);
    List<RequestMRG> findAllByClosedFalse();
    List<RequestMRG> findAllByUserLogin(String login);
    List<RequestMRG> findAllByUserLoginContainsAndClosedFalse(String login);
}
