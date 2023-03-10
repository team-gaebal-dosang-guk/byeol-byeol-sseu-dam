package com.app.byeolbyeolsseudam.controller.market;

import com.app.byeolbyeolsseudam.domain.Payment;
import com.app.byeolbyeolsseudam.domain.basket.BasketDTO;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.service.market.BasketService;
import com.app.byeolbyeolsseudam.service.market.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/ordering/*", "/ordering"})
public class OrderController {
    private final OrderService orderService;
    private final BasketService basketService;

    // 주문 상세 조회
    @GetMapping("/{productId}")
    public ProductDTO getProductList(@PathVariable Long productId){
        return orderService.getOrderDetailList(productId);
    }

    @PostMapping("/all/{productId}")
    public List<ProductDTO> getProduct(@PathVariable Long productId){
        return orderService.getOrderDetail(productId);
    }

    @PostMapping("/basket")
    public List<BasketDTO> getBasket(@RequestBody String paymentFlag){
        List<BasketDTO> baskets = basketService.buyBasket(paymentFlag);
        return baskets;
    }

    @PostMapping("/{memberId}")
    public MemberDTO getMemberList(@PathVariable Long memberId){
        return orderService.getOrderMember(memberId);
    }

    // 주문하기
    @PostMapping("/payment")
    public Long saveOrderList(Payment payment){
        return orderService.order(payment);
    }

    // 주문 완료 조회
    @PostMapping("/success/{memberId}")
    public OrderDTO getOrderList(@PathVariable Long memberId){
        return orderService.getOrderList(memberId);
    }

    @GetMapping("/payment/pay")
    public void test(@Valid @RequestBody List<BasketDTO> list){
        log.info(("---------------" + list));
    }

}
