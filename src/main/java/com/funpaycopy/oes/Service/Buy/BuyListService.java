package com.funpaycopy.oes.Service.Buy;

import com.funpaycopy.oes.Model.BuyList;

import java.util.List;

public interface BuyListService {
    List<BuyList> getAllBuy();

    BuyList saveBuy(BuyList buy);

    BuyList getBuyByID(Long id);

    boolean deleteBuy(Long id);

    BuyList updateBuy(Long id, BuyList buy);
}
