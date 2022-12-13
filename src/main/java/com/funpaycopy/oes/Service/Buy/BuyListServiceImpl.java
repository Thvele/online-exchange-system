package com.funpaycopy.oes.Service.Buy;

import com.funpaycopy.oes.Model.BuyList;
import com.funpaycopy.oes.Repository.BuyListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyListServiceImpl implements BuyListService{

    private BuyListRepository buyListRepository;

    public BuyListServiceImpl(BuyListRepository buyListRepository) { this.buyListRepository = buyListRepository; }


    @Override
    public List<BuyList> getAllBuy() {

        return buyListRepository.findAll();
    }

    @Override
    public BuyList saveBuy(BuyList buy) {

        BuyList buy_ = new BuyList();
        BeanUtils.copyProperties(buy, buy_);
        buyListRepository.save(buy_);
        return buyListRepository.findById(buy_.getIdBuy()).orElseThrow();
    }

    @Override
    public BuyList getBuyByID(Long id) {

        return buyListRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean deleteBuy(Long id) {

        buyListRepository.deleteById(id);
        return true;
    }

    @Override
    public BuyList updateBuy(Long id, BuyList buy) {

        BuyList buy_ = buyListRepository.findById(id).orElseThrow();

        buy_.setBuyer(buy.getBuyer());
        buy_.setGoods(buy.getGoods());
        buy_.setStatus(buy.getStatus());
        buy_.setRequestTS(buy.getRequestTS());

        buyListRepository.save(buy_);
        return buyListRepository.findById(buy_.getIdBuy()).orElseThrow();
    }


}
