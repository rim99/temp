package net.rim99.demo.account.support.service.resource;

import org.junit.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HelloTest extends ResourceTest {

    @Test
    public void should_get_hello() {
        String response =
                get("/hello")
                        .then()
                        .statusCode(200)
                        .extract()
                        .asString();
        assertThat(response, is("hello"));
    }
}
