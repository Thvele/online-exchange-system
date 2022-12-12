package com.funpaycopy.oes.Repository;

import com.funpaycopy.oes.Model.BuyList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyListRepository extends JpaRepository<BuyList, Long> {

    List<BuyList> findAllByBuyerLoginOrderByBuyDateDesc(String name);
    List<BuyList> findAllByGoodsSellerIdUser(Long id);
    List<BuyList> findAllByGoodsSellerIdUserAndGoodsTypeTypeName(Long id, String name);
    List<BuyList> findAllByBuyerIdUserAndGoodsTypeTypeName(Long id, String name);
    BuyList findByGoodsIdGoodsAndBuyerLogin(Long id, String name);
    List<BuyList> findAllByGoodsGoodsNameContainingAndBuyerLoginOrderByIdBuyDesc(String name, String login);
    List<BuyList> findAllByGoodsTypeTypeNameContainingAndBuyerLoginOrderByIdBuy(String type, String login);
    List<BuyList> findAllByGoodsGoodsNameContainingAndGoodsTypeTypeNameContainingAndBuyerLoginOrderByIdBuy(String name, String type, String login);
    List<BuyList> findAllByBuyerLoginAndRequestTSNull(String login);
}
