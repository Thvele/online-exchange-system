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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
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
    public String home(Model model, HttpServletResponse response, Principal principal) {

        Iterable<GoodsList> goodsLists = goodsListRepository.findAllBySelledFalseOrderByIdGoodsDesc();

        try{
            User user = userRepository.findByLoginAndActive(principal.getName(), true);

            Cookie cookie = new Cookie("uID", String.valueOf(user.getIdUser()));
            Cookie cookie1 = new Cookie("uN", String.valueOf(user.getLogin()));
            Cookie cookie2 = new Cookie("uP", String.valueOf(user.getProfilePhoto()));
            Cookie cookie3 = new Cookie("uB", String.valueOf(user.getBalance()));
            cookie.setSecure(true);
            cookie1.setSecure(true);
            cookie2.setSecure(true);
            cookie3.setSecure(true);

            cookie.setPath("/");
            cookie1.setPath("/");
            cookie2.setPath("/");
            cookie3.setPath("/");

            response.addCookie(cookie);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
        } catch (Exception e) {}

        model.addAttribute("goodsList", goodsLists);
        return ("home");
    }

    @GetMapping("/search")
    public String homeSearch(Model model, @RequestParam("search") String search, @RequestParam("type") String type){

        Iterable<GoodsList> goodsLists = null;

        if(search.isEmpty()) {
            goodsLists = goodsListRepository.findAllBySelledFalseAndTypeTypeNameContainingOrderByIdGoodsDesc(type);
        } else if (type.isEmpty()) {
            goodsLists = goodsListRepository.findAllBySelledFalseAndGoodsNameContainingOrderByIdGoodsDesc(search);
        } else {
            goodsLists = goodsListRepository.findAllBySelledFalseAndGoodsNameContainingAndTypeTypeNameContainingOrderByIdGoodsDesc(search, type);
        }

        model.addAttribute("search", search);
        model.addAttribute("type", type);
        model.addAttribute("goodsList", goodsLists);

        return ("home");
    }

    @GetMapping("/item/{id}")
    public String goods(Model model, @PathVariable("id") Long id) {

        GoodsList goods = goodsListRepository.findById(id).orElseThrow();

        model.addAttribute("goods", goods);
        return ("item");
    }

    @GetMapping("/profile")
    public String profileOwn(Model model, Principal principal) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);
        List<GoodsList> goodsList = goodsListRepository.findAllBySellerIdUserAndSelled(user.getIdUser(), false);
        List<BuyList> selledList = buyListRepository.findAllByGoodsSellerIdUser(user.getIdUser());

        GetGraphData(model, user.getIdUser());

        model.addAttribute("user", user);
        model.addAttribute("goods", goodsList);
        model.addAttribute("selled", selledList);
        return ("profile");
    }

    @GetMapping("/profile/{id}")
    public String profile(Model model, @PathVariable("id") Long id) {

        User user = userRepository.findById(id).orElseThrow();
        List<GoodsList> goodsList = goodsListRepository.findAllBySellerIdUserAndSelled(id, false);
        List<BuyList> selledList = buyListRepository.findAllByGoodsSellerIdUser(id);

        GetGraphData(model, id);

        model.addAttribute("user", user);
        model.addAttribute("goods", goodsList);
        model.addAttribute("selled", selledList);
        return ("profile");
    }

    void GetGraphData(Model model, Long id) {

        int buyAcc = 0;
        int buyItem = 0;
        int buyOther = 0;

        int sellAcc = 0;
        int sellItem = 0;
        int sellOther = 0;

        for (BuyList buy : buyListRepository.findAllByBuyerIdUserAndGoodsTypeTypeName(id, "Аккаунт")) buyAcc++;
        for (BuyList buy : buyListRepository.findAllByBuyerIdUserAndGoodsTypeTypeName(id, "Предмет")) buyItem++;
        for (BuyList buy : buyListRepository.findAllByBuyerIdUserAndGoodsTypeTypeName(id, "Прочее")) buyOther++;

        model.addAttribute("buyAcc", buyAcc);
        model.addAttribute("buyItem", buyItem);
        model.addAttribute("buyOther", buyOther);

        for (BuyList buy : buyListRepository.findAllByGoodsSellerIdUserAndGoodsTypeTypeName(id, "Аккаунт")) sellAcc++;
        for (BuyList buy : buyListRepository.findAllByGoodsSellerIdUserAndGoodsTypeTypeName(id, "Предмет")) sellItem++;
        for (BuyList buy : buyListRepository.findAllByGoodsSellerIdUserAndGoodsTypeTypeName(id, "Прочее")) sellOther++;

        model.addAttribute("sellAcc", sellAcc);
        model.addAttribute("sellItem", sellItem);
        model.addAttribute("sellOther", sellOther);
    }

}
