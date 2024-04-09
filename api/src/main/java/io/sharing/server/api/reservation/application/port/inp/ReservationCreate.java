package io.sharing.server.api.reservation.application.port.inp;

import com.google.gson.Gson;
import io.sharing.server.api.reservation.adapter.inp.web.ReservationReq;
import io.sharing.server.core.outbox.application.port.outp.OutboxRepository;
import io.sharing.server.core.outbox.application.service.OutboxService;
import io.sharing.server.core.outbox.domain.Outbox;
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

    private final OutboxService outboxService;

    public void create(ReservationReq req) {
        // 상품 정보 조회
        ProductDto product = getProductInfo(req.getProductId());

        createReservation.create(req.toCommand(product));

        // Kafka 결제요청 서비스
        requestPayment(product);

        // 상품 상태변경 서비스
        changeProductStatus(product);
    }

    /**
     * 상품 정보 조회
     * */
    private ProductDto getProductInfo(Long prodId) {
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
    private void requestPayment(ProductDto product) {
        String message = convertToJSONString(product);
        Outbox outbox = new Outbox(message, "payment");

        outboxService.save(outbox);
    }

    /**
     * 상품 상태 변경
     * */
    private void changeProductStatus(ProductDto product) {
        String message = convertToJSONString(product);
        Outbox outbox = new Outbox(message, "product");

        outboxService.save(outbox);
    }

    private String convertToJSONString(Object obj) {
        Gson gson = new Gson();

        return gson.toJson(obj);
    }

}
