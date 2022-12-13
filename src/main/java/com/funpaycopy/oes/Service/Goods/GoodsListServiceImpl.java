package com.funpaycopy.oes.Service.Goods;

import com.funpaycopy.oes.Model.GoodsList;
import com.funpaycopy.oes.Repository.GoodsListRepository;
import com.funpaycopy.oes.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsListServiceImpl implements GoodsListService{

    private final GoodsListRepository goodsListRepository;
    private final UserRepository userRepository;

    public GoodsListServiceImpl(GoodsListRepository goodsListRepository, UserRepository userRepository) { this.goodsListRepository = goodsListRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<GoodsList> getAllGoods() {

        return goodsListRepository.findAll();
    }

    @Override
    public GoodsList saveGoods(GoodsList goodsList, Long sID) {

        GoodsList goods_ = new GoodsList();
        BeanUtils.copyProperties(goodsList, goods_);
        goods_.setSeller(userRepository.findById(sID).orElseThrow());
        goodsListRepository.save(goods_);
        return goodsListRepository.findById(goods_.getIdGoods()).orElseThrow();
    }

    @Override
    public GoodsList getGoodsByID(Long id) {

        return goodsListRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean deleteGoods(Long id) {

        goodsListRepository.deleteById(id);
        return true;
    }

    @Override
    public GoodsList updateGoods(Long id, GoodsList goods) {

        GoodsList goods_ = goodsListRepository.findById(id).orElseThrow();

        goods_.setGoodsName(goods.getGoodsName());
        goods_.setGoodsDesc(goods.getGoodsDesc());
        goods_.setGoodsDetails(goods.getGoodsDetails());
        goods_.setGoodsCost(goods.getGoodsCost());
        goods_.setType(goods.getType());
        goods_.setSeller(goods.getSeller());

        goodsListRepository.save(goods_);
        return goodsListRepository.findById(goods_.getIdGoods()).orElseThrow();
    }


}
