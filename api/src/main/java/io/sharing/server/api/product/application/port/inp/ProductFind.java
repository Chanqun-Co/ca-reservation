package io.sharing.server.api.product.application.port.inp;

import io.sharing.server.core.product.ProductDto;
import io.sharing.server.core.support.stereotype.UseCase;

@UseCase
public class ProductFind {

    /**
     * 상품 정보 조회
     * */
    public ProductDto getProductInfoAndLock(Long prodId) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(prodId);
        productDto.setHostId("UUID345678");
        return productDto;
    }
}
