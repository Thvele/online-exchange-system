package com.funpaycopy.oes.Controller;

import com.funpaycopy.oes.Model.BuyList;
import com.funpaycopy.oes.Model.GoodsList;
import com.funpaycopy.oes.Model.User;
import com.funpaycopy.oes.Repository.BuyListRepository;
import com.funpaycopy.oes.Repository.GoodsListRepository;
import com.funpaycopy.oes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    GoodsListRepository goodsListRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BuyListRepository buyListRepository;

    @GetMapping("/")
    public String home(Model model) {

        Iterable<GoodsList> goodsLists = goodsListRepository.findAllBySelled(false);

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

        User user = userRepository.findById(id).orElseThrow();
        List<GoodsList> goodsList = goodsListRepository.findAllBySellerIdUserAndSelled(id, false);
        List<BuyList> selledList = buyListRepository.findAllByGoodsSellerIdUser(id);

        model.addAttribute("user", user);
        model.addAttribute("goods", goodsList);
        model.addAttribute("selled", selledList);
        return ("profile");
    }

}
