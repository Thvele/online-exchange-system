package com.funpaycopy.oes.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "buyList")
public class BuyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBuy;

    @NotNull
    @OneToOne
    @JoinColumn(name = "goods")
    private GoodsList goods;

    @JsonBackReference
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User buyer;

    @JsonBackReference
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private BuyStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "buy", fetch = FetchType.EAGER)
    private Collection<RequestTS> requestTS;

    public BuyList() {
    }

    public BuyList(GoodsList goods, User buyer, BuyStatus status, Collection<RequestTS> requestTS) {
        this.goods = goods;
        this.buyer = buyer;
        this.status = status;
        this.requestTS = requestTS;
    }

    public long getIdBuy() {
        return idBuy;
    }

    public void setIdBuy(long idBuy) {
        this.idBuy = idBuy;
    }

    public GoodsList getGoods() {
        return goods;
    }

    public void setGoods(GoodsList goods) {
        this.goods = goods;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public BuyStatus getStatus() {
        return status;
    }

    public void setStatus(BuyStatus status) {
        this.status = status;
    }

    public Collection<RequestTS> getRequestTS() {
        return requestTS;
    }

    public void setRequestTS(Collection<RequestTS> requestTS) {
        this.requestTS = requestTS;
    }
}
