package io.github.cr3ahal0.idgasbsmyl.handler;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maxime on 04/12/2015.
 */
public class MessageHandlerFactory {

    private static Map<String, MessageHandler> handlers;

    static {
        handlers = new HashMap<String, MessageHandler>();
        handlers.put("identification", new LoginMessageHandler());
        handlers.put("drink", new DrinkCoffeeMessageHandler());
        handlers.put("addRefill", new CoffeeRefillAccountMessageHandler());
    }

    private MessageHandlerFactory() {}

    public static MessageHandler get(MapMessage map) throws JMSException {

        String type = map.getString("actionType");

        if (type == null) {
            return null;
        }

        MessageHandler handler = handlers.get(type);
        return handler;
    }

}
