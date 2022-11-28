package com.funpaycopy.oes.Service.Request;

import com.funpaycopy.oes.Model.RequestMRG;
import com.funpaycopy.oes.Model.RequestTS;
import com.funpaycopy.oes.Repository.RequestMRGRepository;
import com.funpaycopy.oes.Repository.RequestTSRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService{

    private RequestTSRepository requestTSRepository;
    private RequestMRGRepository requestMRGRepository;

    public RequestServiceImpl(RequestTSRepository requestTSRepository, RequestMRGRepository requestMRGRepository) { this.requestTSRepository = requestTSRepository; this.requestMRGRepository = requestMRGRepository; }


    @Override
    public List<RequestTS> getAllTS() {

        return requestTSRepository.findAll();
    }

    @Override
    public List<RequestMRG> getAllMRG() {

        return requestMRGRepository.findAll();
    }

    @Override
    public RequestTS saveTS(RequestTS requestTS) {

        RequestTS requestTS_ = new RequestTS();
        BeanUtils.copyProperties(requestTS, requestTS_);
        requestTSRepository.save(requestTS_);
        return requestTS_;
    }

    @Override
    public RequestMRG saveMRG(RequestMRG requestMRG) {

        RequestMRG requestMRG_ = new RequestMRG();
        BeanUtils.copyProperties(requestMRG, requestMRG_);
        requestMRGRepository.save(requestMRG_);
        return requestMRG_;
    }

    @Override
    public RequestTS getTSByID(Long id) {

        return requestTSRepository.findById(id).orElseThrow();
    }

    @Override
    public RequestMRG getMRGByID(Long id) {

        return requestMRGRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean deleteTS(Long id) {

        requestTSRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteMRG(Long id) {

        requestMRGRepository.deleteById(id);
        return true;
    }

    @Override
    public RequestTS updateTS(Long id, RequestTS requestTS) {

        RequestTS requestTS_ = requestTSRepository.findById(id).orElseThrow();

        requestTS_.setRequestName(requestTS.getRequestName());
        requestTS_.setRequestDesc(requestTS.getRequestDesc());
        requestTS_.setRequestStatus(requestTS.getRequestStatus());
        requestTS_.setEmployee(requestTS.getEmployee());
        requestTS_.setBuy(requestTS.getBuy());

        requestTSRepository.save(requestTS_);
        return requestTS_;
    }

    @Override
    public RequestMRG updateMRG(Long id, RequestMRG requestMRG) {

        RequestMRG requestMRG_ = requestMRGRepository.findById(id).orElseThrow();

        requestMRG_.setRequestMRGDesc(requestMRG.getRequestMRGDesc());
        requestMRG_.setUser(requestMRG.getUser());

        requestMRGRepository.save(requestMRG_);
        return requestMRG_;
    }
}
