package com.funpaycopy.oes.Repository;

import com.funpaycopy.oes.Model.RequestTS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestTSRepository extends JpaRepository<RequestTS, Long> {

    List<RequestTS> findAllByEmployeeIsNull();
    List<RequestTS> findAllByBuyBuyerLogin(String login);
    List<RequestTS> findAllByEmployeeIsNullAndRequestNameContains(String name);
}
