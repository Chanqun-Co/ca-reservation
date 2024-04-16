package io.sharing.server.api.reservation.application.port.inp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.sharing.server.api.product.application.port.inp.ProductFind;
import io.sharing.server.api.reservation.adapter.inp.web.ReservationReq;
import io.sharing.server.core.outbox.application.service.OutboxService;
import io.sharing.server.core.product.Product;
import io.sharing.server.core.reservation.application.port.inp.CreateReservation;
import io.sharing.server.core.support.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class ReservationCreate {

    private final CreateReservation createReservation;

    private final OutboxService outboxService;

    private final ProductFind productFind;

    @Transactional
    public void create(ReservationReq req) {
        Product productDto = productFind.getProductInfoAndLock(req.getProductId());

        createReservation.create(req.toCommand(productDto));

        requestPayment(productDto);

        changeProductStatus(productDto);
    }


    /**
     * 결제 요청
     * */
    private void requestPayment(Product product) {
        String message = convertToJSONString(product);

        outboxService.requestPayment(message);
    }

    /**
     * 상품 상태 변경
     * */
    private void changeProductStatus(Product product) {
        String message = convertToJSONString(product);

        outboxService.changeProductStatus(message);
    }

    private String convertToJSONString(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
