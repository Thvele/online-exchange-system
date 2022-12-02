package com.funpaycopy.oes.Repository;

import com.funpaycopy.oes.Model.GoodsList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsListRepository extends JpaRepository<GoodsList, Long> {

    List<GoodsList> findAllBySellerIdUserAndSelled(Long id, Boolean bool);

    List<GoodsList> findAllBySelled(Boolean bool);
}
