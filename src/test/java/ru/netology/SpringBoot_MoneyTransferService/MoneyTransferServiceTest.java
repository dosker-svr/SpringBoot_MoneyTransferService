package ru.netology.SpringBoot_MoneyTransferService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class MoneyTransferServiceTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static GenericContainer<?> moneyTransferService = new GenericContainer<>("money_transfer");

    @Test
    public void integrationAppTest() throws Exception {
        //"{"cardFromNumber":"4276844029881001","cardToNumber":"4276844029881002","cardFromCVV":"001","cardFromValidTill":"11/25","amount":{"currency":"RUR","value":50000}}"
        final String REQUEST_BODY =
                "{\"cardFromNumber\":\"4276844029881001\",\"cardToNumber\":\"4276844029881002\",\"cardFromCVV\":\"001\",\"cardFromValidTill\":\"11/25\",\"amount\":{\"currency\":\"RUR\",\"value\":50000}}";
        final String URI = "http://localhost:" + "8080" + "/transfer";
        RequestEntity<?> requestEntity = RequestEntity.post(URI).contentType(MediaType.APPLICATION_JSON).body(REQUEST_BODY);
        ResponseEntity<?> responseEntity = restTemplate.exchange(requestEntity, String.class);
        if (!responseEntity.getBody().equals("0000")) {
            throw new Exception("Not correctly response");
        }
        System.out.println("Тело ответа: " + responseEntity.getBody());

    }
}
