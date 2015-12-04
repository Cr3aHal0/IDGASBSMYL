package io.github.cr3ahal0.idgasbsmyl.entity;

import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;

/**
 * Created by Maxime on 04/12/2015.
 */
public class MessageRepository extends CouchDbRepositorySupport<Message> {

    public MessageRepository(CouchDbConnector db) {
        super(Message.class, db);
    }

}
