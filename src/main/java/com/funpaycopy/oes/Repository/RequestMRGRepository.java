package com.funpaycopy.oes.Repository;

import com.funpaycopy.oes.Model.RequestMRG;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestMRGRepository extends JpaRepository<RequestMRG, Long> {

    RequestMRG findByUserLogin(String login);
}
