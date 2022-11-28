package com.funpaycopy.oes.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @JsonBackReference
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private RequestStatus requestStatus;

    @JsonBackReference
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User employee;

    @JsonBackReference
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
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
