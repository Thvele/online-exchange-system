package com.funpaycopy.oes.Controller;

import com.funpaycopy.oes.Model.GoodsList;
import com.funpaycopy.oes.Service.Goods.GoodsListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class GoodsListController {

    private final GoodsListService goodsListService;

    public GoodsListController(GoodsListService goodsListService) { this.goodsListService = goodsListService; }

    @GetMapping("/goods")
    public List<GoodsList> getAllGoods() { return goodsListService.getAllGoods(); }

    @PostMapping("/goods")
    public GoodsList saveGoods(@RequestBody GoodsList goodsList, @RequestParam("sID") Long sID){

        return goodsListService.saveGoods(goodsList, sID);
    }

    @GetMapping("/goods/{id}")
    public ResponseEntity<GoodsList> getGoodsByID(@PathVariable("id") Long id) {

        GoodsList goodsList = goodsListService.getGoodsByID(id);
        return ResponseEntity.ok(goodsList);
    }

    @DeleteMapping("/goods/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteGoods(@PathVariable("id") Long id){

        boolean deleted = goodsListService.deleteGoods(id);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", deleted);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/goods/{id}")
    public ResponseEntity<GoodsList> updateGoods(@PathVariable("id") Long id,
                                                 @RequestBody GoodsList goods) {

        goods = goodsListService.updateGoods(id, goods);
        return ResponseEntity.ok(goods);
    }
}