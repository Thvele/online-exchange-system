package com.funpaycopy.oes.Service.Request;

import com.funpaycopy.oes.Model.RequestMRG;
import com.funpaycopy.oes.Model.RequestTS;

import java.util.List;

public interface RequestService {
    List<RequestTS> getAllTS();

    List<RequestMRG> getAllMRG();

    RequestTS saveTS(RequestTS requestTS);

    RequestMRG saveMRG(RequestMRG requestMRG);

    RequestTS getTSByID(Long id);

    RequestMRG getMRGByID(Long id);

    boolean deleteTS(Long id);

    boolean deleteMRG(Long id);

    RequestTS updateTS(Long id, RequestTS requestTS);

    RequestMRG updateMRG(Long id, RequestMRG requestMRG);
}
