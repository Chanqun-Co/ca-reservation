package io.sharing.server.api.product.application.port.inp;

import io.sharing.server.core.product.ProductDto;
import io.sharing.server.core.support.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ProductFind {

    private final ProductClient productClient;
    /**
     * 상품 정보 조회
     * */
    public ProductDto getProductInfoAndLock(Long prodId) {
        ProductDto productDto = new ProductDto();

        try {
            productDto = productClient.getProductInfoAndLock(prodId);
        } catch (Exception e) {
            e.printStackTrace();
            productDto.setProductId(prodId);
            productDto.setHostId("UUID345678");
        }

        return productDto;
    }
}
