package com.funpaycopy.oes.Repository;

import com.funpaycopy.oes.Model.GoodsList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsListRepository extends JpaRepository<GoodsList, Long> {

    List<GoodsList> findAllBySellerIdUserAndSelled(Long id, Boolean bool);
    List<GoodsList> findAllBySelledFalseOrderByIdGoodsDesc();
    List<GoodsList> findAllBySelledFalseAndGoodsNameContainingOrderByIdGoodsDesc(String name);
    List<GoodsList> findAllBySelledFalseAndTypeTypeNameContainingOrderByIdGoodsDesc(String type);
    List<GoodsList> findAllBySelledFalseAndGoodsNameContainingAndTypeTypeNameContainingOrderByIdGoodsDesc(String name, String type);
    GoodsList findByIdGoodsAndSelledFalse(Long id);
}
