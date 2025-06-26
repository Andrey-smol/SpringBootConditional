package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootConditionalApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    private static final GenericContainer<?> myProdApp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);
    private static final GenericContainer<?> myDevApp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
        myProdApp.start();
        myDevApp.start();
    }

    @Test
    void contextLoadsProd() {
        String expected = "Current profile is production";

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:"
                + myProdApp.getMappedPort(8081)
                + "/profile", String.class);
        String result = forEntity.getBody();

        Assertions.assertEquals(expected, result);
    }

    @Test
    void contextLoadsDev() {
        String expected = "Current profile is dev";

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:"
                + myDevApp.getMappedPort(8080)
                + "/profile", String.class);
        String result = forEntity.getBody();

        Assertions.assertEquals(expected, result);
    }

}
