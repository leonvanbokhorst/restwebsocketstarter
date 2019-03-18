package nl.fhict.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        SimpleRestClient client = new SimpleRestClient();
        Greeting greeting = client.getGreeting("leon");

        if (greeting != null) {
            log.info(greeting.getName());
        }
    }
}

