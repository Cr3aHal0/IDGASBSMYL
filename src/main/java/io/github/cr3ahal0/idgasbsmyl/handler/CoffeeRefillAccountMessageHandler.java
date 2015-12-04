package io.github.cr3ahal0.idgasbsmyl.handler;

import javax.jms.JMSException;
import javax.jms.MapMessage;

/**
 * Created by Maxime on 04/12/2015.
 */
public class CoffeeRefillAccountMessageHandler extends MessageHandler {
    @Override
    public boolean isValid(MapMessage message) throws JMSException {

        //check criterias
        if (message.getString("vendingMachineId") != null &&
                message.getString("vendingMachineType") != null &&
                message.getString("userType") != null &&
                message.getString("userId") != null &&
                message.getString("refillType") != null &&
                message.itemExists("amount") &&
                message.itemExists("previousBalance") &&
                message.itemExists("currentBalance") &&
                message.getString("isoDate") != null) {
            return true;
        }
        return false;
    }
}
