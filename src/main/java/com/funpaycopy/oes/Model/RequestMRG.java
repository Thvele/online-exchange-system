package com.funpaycopy.oes.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @JsonBackReference
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public RequestMRG() {
    }

    public RequestMRG(String requestMRGDesc, User user) {
        this.requestMRGDesc = requestMRGDesc;
        this.user = user;
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
}
