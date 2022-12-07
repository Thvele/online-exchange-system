package com.funpaycopy.oes.Repository;

import com.funpaycopy.oes.Model.RequestStatus;
import com.funpaycopy.oes.Model.RequestTS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestTsStatusRepository extends JpaRepository<RequestStatus, String> {
}
