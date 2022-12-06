package com.funpaycopy.oes.Controller;

import com.funpaycopy.oes.Model.BuyList;
import com.funpaycopy.oes.Model.GoodsList;
import com.funpaycopy.oes.Model.User;
import com.funpaycopy.oes.Repository.BuyListRepository;
import com.funpaycopy.oes.Repository.BuyStatusRepository;
import com.funpaycopy.oes.Repository.GoodsListRepository;
import com.funpaycopy.oes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
    @Autowired
    BuyStatusRepository buyStatusRepository;

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

    @GetMapping("/buy")
    public String buyList(Principal principal, HttpServletResponse response, Model model) {

        Iterable<BuyList> buyList = buyListRepository.findAllByBuyerLogin(principal.getName());

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

        model.addAttribute("buyList", buyList);
        return ("buy");
    }

    @PostMapping("/buy/{id}")
    public String buy(Principal principal, @PathVariable("id") Long id) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);
        GoodsList goodsList = goodsListRepository.findByIdGoodsAndSelledFalse(id);
        User seller = userRepository.findByLoginAndActive(goodsList.getSeller().getLogin(), true);

        if(user.getBalance().compareTo(goodsList.getGoodsCost()) > 0){

            user.setBalance(user.getBalance().subtract(goodsList.getGoodsCost()));
            seller.setBalance(seller.getBalance().add(goodsList.getGoodsCost()));
            goodsList.setSelled(true);

            BuyList buyList = new BuyList();
            buyList.setBuyer(user);
            buyList.setGoods(goodsList);
            buyList.setStatus(buyStatusRepository.findById("Получен").orElseThrow());
            buyList.setBuyDate();

            buyListRepository.save(buyList);
            userRepository.save(user);
            userRepository.save(seller);

            return ("redirect:/buy");
        }

        return ("redirect:/item/" + id);
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

    @GetMapping("/searchBuy")
    public String buySearch(Model model, @RequestParam("search") String search, @RequestParam("type") String type, Principal principal) {

        Iterable<BuyList> buyList = null;

        if(search.isEmpty()) {
            buyList = buyListRepository.findAllByGoodsTypeTypeNameContainingAndBuyerLoginOrderByIdBuy(type, principal.getName());
        } else if (type.isEmpty()) {
            buyList = buyListRepository.findAllByGoodsGoodsNameContainingAndBuyerLoginOrderByIdBuyDesc(search, principal.getName());
        } else {
            buyList = buyListRepository.findAllByGoodsGoodsNameContainingAndGoodsTypeTypeNameContainingAndBuyerLoginOrderByIdBuy(search, type, principal.getName());
        }

        model.addAttribute("search", search);
        model.addAttribute("type", type);
        model.addAttribute("buyList", buyList);

        return ("buy");
    }

    @GetMapping("/item/{id}")
    public String goods(Model model, @PathVariable("id") Long id, Principal principal) {

        GoodsList goods = goodsListRepository.findById(id).orElseThrow();
        User user = userRepository.findByLoginAndActive(principal.getName(), true);

        model.addAttribute("goods", goods);
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("login", user.getLogin());
        return ("item");
    }

    @GetMapping("/buy/item/{id}")
    public String byu(Model model, @PathVariable("id") Long id, Principal principal){

        BuyList buyList = buyListRepository.findByGoodsIdGoodsAndBuyerLogin(id, principal.getName());

        try {
            if(buyList.getBuyer().getLogin().equals(principal.getName())){

                model.addAttribute("buy", buyList);
                return ("item");
            }
        } catch (Exception e) {}

        return ("redirect:/");
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

    @GetMapping("/thanku")
    public String thankuView() {

        return ("thanku");
    }

    @PostMapping("/thanku")
    public String thanku(Principal principal, BigDecimal sum) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);

        user.setBalance(user.getBalance().add(sum));
        userRepository.save(user);
        return ("redirect:/thanku");
    }

    @GetMapping("/rights")
    public String rightsView(Model model, Principal principal) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);

        model.addAttribute("user", user);
        return ("rights");
    }

    @GetMapping("/support")
    public String supportView() {

        return ("support");
    }

    @GetMapping("/backup")
    @ResponseBody
    public String backup(String dbName) {
        try {
            String folderPath = System.getProperty("user.dir") + "\\backup\\";
            File temp = new File(folderPath);
            temp.mkdir();

            String savePath = folderPath + "backup.sql";

            String executeCmd = "mysqldump --column-statistics=0 -uroot " + dbName + " -r " + savePath;

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {

                return ("Резервное копирование базы данных прошло успешно");
            } else {

                return ("Не удалось сохранить резервную копию базы данных");
            }

        } catch (IOException | InterruptedException ex) {

            return ("Не удалось сохранить резервную копию базы данных: " + ex.getMessage());
        }
    }

    @GetMapping("/restore")
    @ResponseBody
    public String restore(String dbName, String filepath){
        try {

            String executeCmd = "cmd.exe /c mysql -uroot " + dbName + " < " + filepath;
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                return ("БД успешно восстановлена из файла: " + filepath);
            } else {
                return ("Ошибка при восстановлении БД из файла: " + filepath);
            }
        } catch(IOException | InterruptedException | HeadlessException e){

            return ("Ошибка при восстановлении БД из файла: " + filepath + " | " + e.getMessage());
        }
    }
}
