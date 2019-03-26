package nl.fhict.s3.websocketclient;

import java.util.Observable;
import java.util.Observer;
import nl.fhict.s3.websocketclient.endpoint.GreeterClientEndpoint;

public class ClientMe implements Observer {

    void go() {
        GreeterClientEndpoint greeterClientEndpoint = GreeterClientEndpoint.getInstance();
        greeterClientEndpoint.addObserver(this);
        greeterClientEndpoint.start();

        greeterClientEndpoint.setMessage("Whoohoo");

        greeterClientEndpoint.stop();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
