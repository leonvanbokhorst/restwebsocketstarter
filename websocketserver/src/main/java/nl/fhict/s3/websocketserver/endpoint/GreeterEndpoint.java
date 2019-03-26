package nl.fhict.s3.websocketserver.endpoint;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import nl.fhict.s3.websocketshared.Greeting;

@ServerEndpoint(value = "/greeter/")
public class GreeterEndpoint {

    // All sessions
    private static final List<Session> sessions = new ArrayList<>();

    // Map each property to list of sessions that are subscribed to that property
    private static final Map<String, List<Session>> propertySessions = new HashMap<>();

    @OnOpen
    public void onConnect(Session session) {
        System.out.println("[WebSocket Connected] SessionID: " + session.getId());
        String message = "[New client with client side session ID]: " + session.getId();
        sessions.add(session);
        System.out.println("[#sessions]: " + sessions.size());
    }

    @OnMessage
    public void onText(String message, Session session) {
        System.out
            .println("[WebSocket Session ID] : " + session.getId() + " [Received] : " + message);
        handleMessageFromClient(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out
            .println("[WebSocket Session ID] : " + session.getId() + " [Socket Closed]: " + reason);
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

    // Handle incoming message from client
    private void handleMessageFromClient(String jsonMessage, Session session) {
        Gson gson = new Gson();
        Greeting message = null;
        try {
            message = gson.fromJson(jsonMessage, Greeting.class);
        } catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket ERROR: cannot parse Json message " + jsonMessage);
        }

//        // Operation defined in message
//        CommunicatorWebSocketMessageOperation operation;
//        operation = wbMessage.getOperation();
//
//        // Process message based on operation
//        String property = wbMessage.getProperty();
//        if (null != operation && null != property && !"".equals(property)) {
//            switch (operation) {
//                case REGISTERPROPERTY:
//                    // Register property if not registered yet
//                    if (propertySessions.get(property) == null) {
//                        propertySessions.put(property, new ArrayList<Session>());
//                    }
//                    break;
//                case UNREGISTERPROPERTY:
//                    // Do nothing as property may also have been registered by
//                    // another client
//                    break;
//                case SUBSCRIBETOPROPERTY:
//                    // Subsribe to property if the property has been registered
//                    if (propertySessions.get(property) != null) {
//                        propertySessions.get(property).add(session);
//                    }
//                    break;
//                case UNSUBSCRIBEFROMPROPERTY:
//                    // Unsubsribe from property if the property has been registered
//                    if (propertySessions.get(property) != null) {
//                        propertySessions.get(property).remove(session);
//                    }
//                    break;
//                case UPDATEPROPERTY:
//                    // Send the message to all clients that are subscribed to this property
//                    if (propertySessions.get(property) != null) {
//                        System.out.println("[WebSocket send ] " + jsonMessage + " to:");
//                        for (Session sess : propertySessions.get(property)) {
//                            // Use asynchronous communication
//                            System.out.println("\t\t >> Client associated with server side session ID: " + sess.getId());
//                            sess.getAsyncRemote().sendText(jsonMessage);
//                        }
//                        System.out.println("[WebSocket end sending message to subscribers]");
//                    }
//                    break;
//                default:
//                    System.out.println("[WebSocket ERROR: cannot process Json message " + jsonMessage);
//                    break;
//            }
    }
}
