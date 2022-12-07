package com.funpaycopy.oes.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "requestTS")
public class RequestTS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRequestTS;

    @Size(max = 100)
    private String requestName;

    @Size(max = 1500)
    private String requestDesc;

    @JsonIgnoreProperties(value = {"requests"})
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private RequestStatus requestStatus;

    @JsonIgnoreProperties(value = {"requestTSCollection"})
    @ManyToOne(cascade = CascadeType.MERGE)
    private User employee;

    @JsonIgnoreProperties(value = {"requestTS"})
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private BuyList buy;

    public RequestTS() {
    }

    public RequestTS(String requestName, String requestDesc, RequestStatus requestStatus, User employee, BuyList buy) {
        this.requestName = requestName;
        this.requestDesc = requestDesc;
        this.requestStatus = requestStatus;
        this.employee = employee;
        this.buy = buy;
    }

    public long getIdRequestTS() {
        return idRequestTS;
    }

    public void setIdRequestTS(long idRequestTS) {
        this.idRequestTS = idRequestTS;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public BuyList getBuy() {
        return buy;
    }

    public void setBuy(BuyList buy) {
        this.buy = buy;
    }
}
