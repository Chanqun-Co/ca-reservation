package io.sharing.server.api.product.application.port.inp;

import io.sharing.server.api.config.ProductFeignClientConfig;
import io.sharing.server.core.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ca-product", url = "${product.client.url}", configuration = ProductFeignClientConfig.class)
public interface ProductClient {

    @GetMapping("/product-lock/{productId}")
    Product getProductInfoAndLock(@PathVariable("productId") Long productId);
}
