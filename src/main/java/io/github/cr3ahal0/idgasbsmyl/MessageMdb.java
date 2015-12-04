package io.github.cr3ahal0.idgasbsmyl;

import io.github.cr3ahal0.idgasbsmyl.entity.MessageRepository;
import io.github.cr3ahal0.idgasbsmyl.handler.MessageHandler;
import io.github.cr3ahal0.idgasbsmyl.handler.MessageHandlerFactory;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.*;
import java.net.MalformedURLException;

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

    CouchDbConnector db;

    MessageRepository repo;

    public MessageMdb () throws MalformedURLException {
        HttpClient httpClient = new StdHttpClient.Builder()
                .url("http://localhost:5984")
                .build();

        CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
        db = new StdCouchDbConnector("mydatabase", dbInstance);

        db.createDatabaseIfNotExists();

        repo = new MessageRepository(db);
    }

    public void onMessage(Message message) {

        if (message instanceof MapMessage) {
            MapMessage map = (MapMessage)message;
            try {
                //Check whenever the message is correct and has a valid type
                MessageHandler handler = MessageHandlerFactory.get(map);
                if (handler.isValid(map)) {

                    //TODO Persist it with Ektorp
                    repo.add(new io.github.cr3ahal0.idgasbsmyl.entity.Message(map));
                }
            } catch (JMSException e) {
                System.out.println("Error while getting message text");
            }
        }
    }

}