package io.sharing.server.api.product.application.port.inp;

import io.sharing.server.core.product.Product;
import io.sharing.server.core.support.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ProductFind {

    private final ProductClient productClient;
    /**
     * 상품 정보 조회
     * */
    public Product getProductInfoAndLock(Long prodId) {
        Product product;

        try {
            product = productClient.getProductInfoAndLock(prodId);
        } catch (Exception e) {
            e.printStackTrace();
            product = new Product(prodId, "UUID345678");
        }

        return product;
    }
}
