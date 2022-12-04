package com.app.byeolbyeolsseudam.controller.market;

import com.app.byeolbyeolsseudam.service.market.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/market/*", "/market"})
public class MarketController {

    private final MarketService marketService;
//    private final ReviewService reviewService;

    @GetMapping("/all")
    public String Market(Model model){
        model.addAttribute("products", marketService.showList());
        return "/app/market/market";
    }

    /* 카테고리 받아서 조회 */
    @GetMapping("/living")
    public String MarketLiving(Model model){
        model.addAttribute("products", marketService.showLivingList());
        return "/app/market/market";
    }

    @GetMapping("/kitchen")
    public String MarketKitchen(Model model){
        model.addAttribute("products", marketService.showKitchenList());
        return "/app/market/market";
    }

    @GetMapping("/bathroom")
    public String MarketBathroom(Model model){
        model.addAttribute("products", marketService.showBathroomList());
        return "/app/market/market";
    }

    @GetMapping("/food")
    public String MarketFood(Model model){
        model.addAttribute("products", marketService.showFoodList());
        return "/app/market/market";
    }

    @GetMapping("/hobby")
    public String MarketHobby(Model model){
        model.addAttribute("products", marketService.showHobbyList());
        return "/app/market/market";
    }

    @GetMapping("/office")
    public String MarketOffice(Model model){
        model.addAttribute("products", marketService.showOfficeList());
        return "/app/market/market";
    }

    @GetMapping("/pet")
    public String MarketPet(Model model){
        model.addAttribute("products", marketService.showPetList());
        return "/app/market/market";
    }

    // 마켓 상세보기 조회
    @GetMapping("/{productId}")
    public String detail(@PathVariable Long productId, Model model){
        model.addAttribute("product", marketService.showListDetail(productId));
//        model.addAttribute("reviews", reviewService.showReview(productId));
        return "/app/market/marketDetail";
    }

    @GetMapping("/basket")
    public String basket(){
        return "/app/market/marketBasket";
    }


    @GetMapping("/payment")
    public String payment(){
        return "/app/market/marketPayment";
    }

    @GetMapping("/paid")
    public String paid(){
        return "/app/market/marketPaid";
    }
}
