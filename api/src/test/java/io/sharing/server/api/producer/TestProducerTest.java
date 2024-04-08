package io.sharing.server.api.producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestProducerTest {

    @Autowired
    private KafkaProducer testProducer;

    @Test
    void test() {
        testProducer.create("test", "topic");
    }
}