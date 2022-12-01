package com.funpaycopy.oes.Controller;

import com.funpaycopy.oes.Model.GoodsList;
import com.funpaycopy.oes.Repository.GoodsListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @Autowired
    GoodsListRepository goodsListRepository;

    @GetMapping("/")
    public String home(Model model) {

        Iterable<GoodsList> goodsLists = goodsListRepository.findAll();

        model.addAttribute("goodsList", goodsLists);

        return ("home");
    }

    @GetMapping("/item/{id}")
    public String goods(Model model, @PathVariable("id") Long id) {

        GoodsList goods = goodsListRepository.findById(id).orElseThrow();
        model.addAttribute("goods", goods);

        return ("item");
    }

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable("id") Long id) {

        return ("profile");
    }

}
