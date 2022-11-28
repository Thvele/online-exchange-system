package com.funpaycopy.oes.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "goodsList")
public class GoodsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGoods;

    @NotBlank(message = "Не указано наименование товара!")
    @Size(max = 100, message = "Наименование товара должно содержать не больше 100 символов!")
    private String goodsName;

    @NotBlank(message = "Не указано описание товара!")
    @Size(max = 500, message = "Описание товара должно содержать не больше 500 символов!")
    private String goodsDesc;

    @NotBlank(message = "Не указаны реквизиты товара!")
    @Size(max = 500, message = "Реквизиты товара должны содержать не больше 500 символов!")
    private String goodsDetails;

    @NotNull(message = "Не указана цена товара!")
    @Digits(integer = 6, fraction = 2)
    @DecimalMin(value = "0.00")
    private BigDecimal goodsCost;

    @JsonManagedReference
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private GoodsType type;

    @JsonManagedReference
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User seller;

    public GoodsList() {
    }

    public GoodsList(String goodsName, String goodsDesc, String goodsDetails, BigDecimal goodsCost, GoodsType type, User seller) {
        this.goodsName = goodsName;
        this.goodsDesc = goodsDesc;
        this.goodsDetails = goodsDetails;
        this.goodsCost = goodsCost;
        this.type = type;
        this.seller = seller;
    }

    public long getIdGoods() {
        return idGoods;
    }

    public void setIdGoods(long idGoods) {
        this.idGoods = idGoods;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsDetails() {
        return goodsDetails;
    }

    public void setGoodsDetails(String goodsDetails) {
        this.goodsDetails = goodsDetails;
    }

    public BigDecimal getGoodsCost() {
        return goodsCost;
    }

    public void setGoodsCost(BigDecimal goodsCost) {
        this.goodsCost = goodsCost;
    }

    public GoodsType getType() {
        return type;
    }

    public void setType(GoodsType type) {
        this.type = type;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
