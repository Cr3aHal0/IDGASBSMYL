package io.github.cr3ahal0.idgasbsmyl.handler;

import javax.jms.MapMessage;

/**
 * Created by Maxime on 04/12/2015.
 */
public abstract class MessageHandler {

    public abstract boolean isValid(MapMessage message);

}
