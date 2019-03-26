package nl.fhict.s3.websocketclient.endpoint;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import nl.fhict.s3.websocketshared.Greeting;

@ClientEndpoint
public class GreeterClientEndpoint extends Observable {

    private static GreeterClientEndpoint instance = null;
    private static final String URI = "ws://localhost:8095/greeter/";
    private Session session;
    private String message;
    private Gson gson;
    private boolean isRunning = false;

    private GreeterClientEndpoint() {
        gson = new Gson();
    }

    public static GreeterClientEndpoint getInstance() {
        if (instance == null) {
            System.out.println("[WebSocket Client create singleton instance]");
            instance = new GreeterClientEndpoint();
        }
        return instance;
    }

    public void start() {
        System.out.println("[WebSocket Client start connection]");
        if (!isRunning) {
            startClient();
            isRunning = true;
        }
    }

    public void stop() {
        System.out.println("[WebSocket Client stop]");
        if (isRunning) {
            stopClient();
            isRunning = false;
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session) {
        System.out.println("[WebSocket Client open session] " + session.getRequestURI());
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session) {
        this.message = message;
        System.out.println("[WebSocket Client message received] " + message);
        processMessage(message);
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        System.out.println("[WebSocket Client connection error] " + cause.toString());
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.print("[WebSocket Client close session] " + session.getRequestURI());
        System.out.println(" for reason " + reason);
        session = null;
    }

    private void sendMessageToServer(Greeting message) {
        String jsonMessage = gson.toJson(message);
        session.getAsyncRemote().sendText(jsonMessage);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private void startClient() {
        System.out.println("[WebSocket Client start]");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(URI));

        } catch (IOException | URISyntaxException | DeploymentException ex) {
            ex.printStackTrace();
        }
    }

    private void stopClient() {
        System.out.println("[WebSocket Client stop]");
        try {
            session.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void processMessage(String jsonMessage) {

        // Parse incoming message
        Greeting wsMessage;
        try {
            wsMessage = gson.fromJson(jsonMessage, Greeting.class);
        } catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket Client ERROR: cannot parse Json message " + jsonMessage);
            return;
        }

        // Obtain content from message
        String content = wsMessage.getName();
        if (content == null || "".equals(content)) {
            System.out.println("[WebSocket Client ERROR: message without content]");
            return;
        }

        Greeting commMessage = new Greeting();
        commMessage.setName(content);

        this.setChanged();
        this.notifyObservers(commMessage);
    }
}