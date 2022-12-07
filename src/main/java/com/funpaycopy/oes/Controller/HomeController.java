package com.funpaycopy.oes.Controller;

import com.funpaycopy.oes.Model.*;
import com.funpaycopy.oes.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
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
    @Autowired
    RequestTsStatusRepository requestTsStatusRepository;
    @Autowired
    RequestTSRepository requestTSRepository;
    @Autowired
    RequestMRGRepository requestMRGRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

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

        if(user.getBalance().compareTo(goodsList.getGoodsCost()) >= 0){

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
    public String thanku(Model model, Principal principal, BigDecimal sum, String method) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);

        if(method.equals("in")) {

            user.setBalance(user.getBalance().add(sum));
            userRepository.save(user);

            model.addAttribute("icon", "published_with_changes");
            model.addAttribute("msg", "Спасибо, оплата прошла успешно!");
        } else {

            if(user.getBalance().compareTo(sum) >= 0){

                user.setBalance(user.getBalance().subtract(sum));
                userRepository.save(user);

                model.addAttribute("icon", "published_with_changes");
                model.addAttribute("msg", "Средства придут к вам на счёт в течении 24 часов");
            } else {

                model.addAttribute("icon", "unpublished");
                model.addAttribute("msg", "У вас не хватает средств!");
            }
        }

        return ("thanku");
    }

    @GetMapping("/rights")
    public String rightsView(Model model, Principal principal) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);

        model.addAttribute("user", user);

        if(requestMRGRepository.findByUserLogin(principal.getName()) != null){

            model.addAttribute("requestSend", true);
        }

        return ("rights");
    }

    @PostMapping("/rights")
    public String getSellerRights(Principal principal, Model model) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);

        if(user.getBalance().compareTo(BigDecimal.valueOf(150)) >= 0){

            user.setBalance(user.getBalance().subtract(BigDecimal.valueOf(150)));

            Object[] roles = user.getRoles().toArray();

            user.getRoles().clear();

            for(Object role : roles) {

                user.getRoles().add(Role.valueOf(role.toString()));
            }
            user.getRoles().add(Role.SELLER);

            userRepository.save(user);
            return("redirect:/logout");
        } else {

            model.addAttribute("icon", "unpublished");
            model.addAttribute("msg", "У вас не хватает средств!");

            return ("thanku");
        }
    }

    @PostMapping("/modrights")
    public String getModRights(Principal principal, @RequestParam String desc) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);

        RequestMRG requestMRG = new RequestMRG();

        requestMRG.setUser(user);
        requestMRG.setRequestMRGDesc(desc);

        requestMRGRepository.save(requestMRG);
        return ("redirect:/");
    }

    @GetMapping("/support")
    public String supportView(Model model, Principal principal) {

        model.addAttribute("buys", buyListRepository.findAllByBuyerLoginAndRequestTSNull(principal.getName()));
        return ("support");
    }

    @PostMapping("/support")
    public String reqTS(Principal principal, @RequestParam String name, @RequestParam String desc, @RequestParam Long buyId) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);
        BuyList buyList = buyListRepository.findById(buyId).orElseThrow();

        RequestTS requestTS = new RequestTS();

        requestTS.setBuy(buyList);
        requestTS.setRequestStatus(requestTsStatusRepository.findById("На рассмотрении").orElseThrow());
        requestTS.setRequestName(name);
        requestTS.setRequestDesc(desc);

        requestTSRepository.save(requestTS);

        return ("redirect:/");
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
    public String restore(String dbName){
        try {

            String executeCmd = "cmd.exe /c mysql -uroot " + dbName + " < " + System.getProperty("user.dir") + "\\backup\\backup.sql";
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                return ("БД успешно восстановлена из файла: " + System.getProperty("user.dir") + "\\backup\\backup.sql");
            } else {
                return ("Ошибка при восстановлении БД из файла: " + System.getProperty("user.dir") + "\\backup\\backup.sql");
            }
        } catch(IOException | InterruptedException | HeadlessException e){

            return ("Ошибка при восстановлении БД из файла: " + System.getProperty("user.dir") + "\\backup\\backup.sql" + " | " + e.getMessage());
        }
    }

    @GetMapping("/profileEDT")
    public String editView(Model model, Principal principal) {

        model.addAttribute("user", userRepository.findByLoginAndActive(principal.getName(), true));
        return ("profileEDT");
    }

    @PostMapping("/profileEDT")
    public String edit(Model model, Principal principal, @RequestParam String email, @RequestParam String pass, @RequestParam(required = false) String newPass, @RequestParam(required = false) String reNewPass) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);

        if(passwordEncoder.matches(pass, user.getPassword())) {

            user.setEmail(email);

            if(!newPass.isEmpty() && !reNewPass.isEmpty()){

                if(newPass.equals(reNewPass)) {

                    user.setPassword(passwordEncoder.encode(newPass));
                } else {

                    model.addAttribute("exception", "Пароли не совпадают");
                    model.addAttribute("user", user);
                    return ("profileEDT");
                }
            }

            userRepository.save(user);
            return ("redirect:/");
        } else {

            model.addAttribute("exception", "Неверный пароль");
            model.addAttribute("user", user);
            return ("profileEDT");
        }
    }

    @PostMapping("/linkPhoto")
    public String linkPhoto(Principal principal, @RequestParam String photo) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);

        user.setProfilePhoto(photo);

        userRepository.save(user);
        return ("redirect:/");
    }

    @PostMapping("/filePhoto")
    public String filePhoto(Principal principal, @RequestParam MultipartFile photo) {

        User user = userRepository.findByLoginAndActive(principal.getName(), true);

        try{

            String folderPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\usericons\\";
            File temp = new File(folderPath + user.getLogin() + photo.getOriginalFilename());
            photo.transferTo(temp);

            user.setProfilePhoto("/usericons/" + user.getLogin() + photo.getOriginalFilename());

            userRepository.save(user);
        } catch (Exception e) {

            return ("profileEDT");
        }


        return ("redirect:/");
    }
}
