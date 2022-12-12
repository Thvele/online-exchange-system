package com.funpaycopy.oes.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "requestMRG")
public class RequestMRG {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idRequestMRG;

    @Size(max = 1500)
    private String requestMRGDesc;

    @JsonIgnoreProperties(value = {"requestMRGCollection"})
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    private Boolean closed;

    public RequestMRG() {
    }

    public RequestMRG(String requestMRGDesc, User user, Boolean closed) {
        this.requestMRGDesc = requestMRGDesc;
        this.user = user;
        this.closed = closed;
    }


    public long getIdRequestMRG() {
        return idRequestMRG;
    }

    public void setIdRequestMRG(long idRequestMRG) {
        this.idRequestMRG = idRequestMRG;
    }

    public String getRequestMRGDesc() {
        return requestMRGDesc;
    }

    public void setRequestMRGDesc(String requestMRGDesc) {
        this.requestMRGDesc = requestMRGDesc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
