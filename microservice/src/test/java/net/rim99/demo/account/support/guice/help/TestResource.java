package net.rim99.demo.account.support.guice.help;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("test")
public class TestResource {

    @Inject
    Instance instace;

    @GET
    public String hello() {
        return instace != null ? "hello" : "error";
    }
}
