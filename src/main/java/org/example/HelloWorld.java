package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("hello")
public class HelloWorld {

    @GET
    public String sayHello() {
        return "Hello, World!";
    }
}
