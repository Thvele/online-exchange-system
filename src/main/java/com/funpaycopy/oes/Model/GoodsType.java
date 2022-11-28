package com.funpaycopy.oes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "goodsType")
public class GoodsType {

    @Id
    private String typeName;

    @JsonIgnore
    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<GoodsList> goods;

    public GoodsType() {
    }

    public GoodsType(String typeName, Collection<GoodsList> goods) {
        this.typeName = typeName;
        this.goods = goods;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Collection<GoodsList> getGoods() {
        return goods;
    }

    public void setGoods(Collection<GoodsList> goods) {
        this.goods = goods;
    }
}
