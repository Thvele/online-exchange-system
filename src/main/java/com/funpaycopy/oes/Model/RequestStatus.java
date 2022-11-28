package com.funpaycopy.oes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "requestStatus")
public class RequestStatus {

    @Id
    private String requestStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "requestStatus", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<RequestStatus> requests;

    public RequestStatus() {
    }

    public RequestStatus(String requestStatus, Collection<RequestStatus> requests) {
        this.requestStatus = requestStatus;
        this.requests = requests;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Collection<RequestStatus> getRequests() {
        return requests;
    }

    public void setRequests(Collection<RequestStatus> requests) {
        this.requests = requests;
    }
}
