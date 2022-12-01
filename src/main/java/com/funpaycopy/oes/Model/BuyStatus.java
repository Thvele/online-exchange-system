package com.funpaycopy.oes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "buyStatus")
public class BuyStatus {

    @Id
    private String buyStatus;

    @JsonIgnoreProperties(value = {"status"})
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<BuyList> buys;

    public BuyStatus() {
    }

    public BuyStatus(String buyStatus, Collection<BuyList> buys) {
        this.buyStatus = buyStatus;
        this.buys = buys;
    }

    public String getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(String buyStatus) {
        this.buyStatus = buyStatus;
    }

    public Collection<BuyList> getBuys() {
        return buys;
    }

    public void setBuys(Collection<BuyList> buys) {
        this.buys = buys;
    }
}
