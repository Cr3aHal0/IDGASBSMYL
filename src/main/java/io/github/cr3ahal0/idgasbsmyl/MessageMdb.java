package io.github.cr3ahal0.idgasbsmyl;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by E130110Z on 04/12/15.
 */

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "reconnectAttempts", propertyValue = "-1"),
        @ActivationConfigProperty(propertyName = "setupAttempts", propertyValue = "-1"),
        /*@ActivationConfigProperty(propertyName = "user", propertyValue = "admin"),
        @ActivationConfigProperty(propertyName = "password", propertyValue = "root"),*/
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationName",
                propertyValue = "jms/queue.mine"),
        @ActivationConfigProperty(propertyName = "destination",
                propertyValue = "jms/queue.mine") })
public class MessageMdb implements MessageListener {

    public void onMessage(Message message) {

        if (message instanceof TextMessage) {
            TextMessage txt = (TextMessage)message;
            try {
                System.out.println(txt.getText());
            } catch (JMSException e) {
                System.out.println("Error while getting message text");
            }
        }
    }

}