package com.funpaycopy.oes.Service.Goods;

import com.funpaycopy.oes.Model.GoodsList;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GoodsListService {
    List<GoodsList> getAllGoods();

    GoodsList saveGoods(GoodsList goodsList,Long sID);

    GoodsList getGoodsByID(Long id);

    boolean deleteGoods(Long id);

    GoodsList updateGoods(Long id, GoodsList goods);
}
