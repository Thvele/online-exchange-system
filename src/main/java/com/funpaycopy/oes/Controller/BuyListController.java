package com.funpaycopy.oes.Controller;

import com.funpaycopy.oes.Model.BuyList;
import com.funpaycopy.oes.Service.Buy.BuyListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class BuyListController {

    private final BuyListService buyListService;

    public BuyListController(BuyListService buyListService) { this.buyListService = buyListService; }

    @GetMapping("/buy")
    public List<BuyList> getAllBuy() { return buyListService.getAllBuy(); }

    @PostMapping("/buy")
    public BuyList saveBuy(@RequestBody BuyList buy){

        return buyListService.saveBuy(buy);
    }

    @GetMapping("/buy/{id}")
    public ResponseEntity<BuyList> getBuyByID(@PathVariable("id") Long id) {

        BuyList buy = buyListService.getBuyByID(id);
        return ResponseEntity.ok(buy);
    }

    @DeleteMapping("/buy/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBuy(@PathVariable("id") Long id) {

        boolean deleted = buyListService.deleteBuy(id);
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", deleted);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/buy/{id}")
    public ResponseEntity<BuyList> updateBuy(@PathVariable("id") Long id,
                                             @RequestBody BuyList buy) {

        buy = buyListService.updateBuy(id, buy);
        return ResponseEntity.ok(buy);
    }
}
