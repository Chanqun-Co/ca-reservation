package io.sharing.server.api.reservation.application.port.inp;

import io.sharing.server.api.reservation.adapter.inp.web.ReservationReq;
import io.sharing.server.core.product.ProductDto;
import io.sharing.server.core.reservation.application.port.inp.CreateReservation;
import io.sharing.server.core.reservation.application.port.inp.CreateReservationCommand;
import io.sharing.server.core.support.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@UseCase
@RequiredArgsConstructor
public class ReservationCreate {

    private final CreateReservation createReservation;

    private final RestTemplate restTemplate;

    public void create(ReservationReq req) {
        // 상품 정보 조회
        ProductDto product = getProductInfo(req.getProductId());

        createReservation.create(req.toCommand(product));

        // Kafka 결제요청 서비스

        // 상품 상태변경 서비스
    }

    /**
     * 상품 정보 조회
     * */
    private ProductDto getProductInfo(String prodId) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(prodId);
        productDto.setHostId("UUID345678");
        // RestTemplate
//        try {
//            productDto = restTemplate.getForObject("", ProductDto.class);
//        } catch (HttpClientErrorException e) {
//            throw e;
//        }
        return productDto;
    }

    /**
     * 결제 요청
     * */
    private void requestPayment() {
        // outbox patturn
    }

    /**
     * 상품 상태 변경
     * */
    private void changeProductStatus(String prodId) {
        // outbox patturn
    }

}
