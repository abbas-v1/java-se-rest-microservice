package org.example;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.SeBootstrap;
import jakarta.ws.rs.core.Application;
import java.net.URI;
import java.util.Collections;
import java.util.Set;

@ApplicationPath("api")
public class Main extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Collections.singleton(HelloWorld.class);
    }

    public static void main(String[] args) throws InterruptedException {
        final SeBootstrap.Configuration config = SeBootstrap.Configuration.builder().port(8080).build();

        SeBootstrap.start(Main.class, config).thenAccept(instance -> {

            instance.stopOnShutdown(stopResult ->
                    System.out.printf("Stop result: %s [Native stop result: %s].%n", stopResult,
                            stopResult.unwrap(Object.class)));

            final URI uri = instance.configuration().baseUri();

            System.out.printf("Instance %s running at %s [Native handle: %s].%n", instance, uri,
                    instance.unwrap(Object.class));

            System.out.println("Send SIGKILL to shutdown.");
        });

        Thread.currentThread().join();
    }

}