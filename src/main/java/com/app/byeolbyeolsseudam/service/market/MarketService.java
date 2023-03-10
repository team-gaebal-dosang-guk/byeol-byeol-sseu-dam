package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarketService {

    // 상품 전체 조회
    public List<ProductDTO> selectProducts(Criteria criteria);

    // 상품 상세 조회
    public ProductDTO readProduct(Long productId);

}
