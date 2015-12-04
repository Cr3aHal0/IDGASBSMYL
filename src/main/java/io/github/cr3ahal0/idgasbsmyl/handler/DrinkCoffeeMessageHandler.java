package io.github.cr3ahal0.idgasbsmyl.handler;

import javax.jms.MapMessage;

/**
 * Created by Maxime on 04/12/2015.
 */
public class DrinkCoffeeMessageHandler extends MessageHandler {

    @Override
    public boolean isValid(MapMessage message) {

        //check criterias

        return false;
    }
}
