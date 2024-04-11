package io.sharing.server.api.product.application.port.inp;

import io.sharing.server.api.config.ProductFeignClientConfig;
import io.sharing.server.core.product.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ca-product", url = "https://localhost:8000/ca-product", configuration = ProductFeignClientConfig.class)
public interface ProductClient {

    @GetMapping("/product_lock/{productId}")
    ProductDto getProductInfoAndLock(@PathVariable("productId") Long productId);
}
