package net.rim99.demo.account.resouces;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Hello extends ResourceTest {

    @Test
    void should_get_hello() {
        String response =
                get("/hello")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();
        assertThat(response, is("hello"));
    }
}
