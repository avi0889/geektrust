package com.geektrust.goldencrown.processor;


import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.kingdom.Kingdom;
import com.geektrust.message.Message;
import com.geektrust.utils.BaseInputProcessor;
import com.geektrust.exception.InvalidInputException;
import com.geektrust.utils.Constants;
import com.geektrust.utils.Election;

import java.util.*;

public class GoldenCrownInputProcessor extends BaseInputProcessor {

    private Election election;
    private KingdomFactory kingdomFactory;

    public GoldenCrownInputProcessor(Election election, KingdomFactory kingdomFactory) {
        this.election = election;
        this.kingdomFactory = kingdomFactory;
    }

    public void processInput(String input) throws InvalidInputException, NoSuchKingdomException {
        if(isQuestion(input)) {
            processQuestion(input);
        } else {
            processMessage(input);
        }
    }

    private void processMessage(String input) throws InvalidInputException, NoSuchKingdomException {
        String receiverHouse, messageStr;
        try {
            receiverHouse = input.split(",")[0];
            messageStr = input.split(",")[1];
        } catch (Exception e) {
            throw new InvalidInputException(Constants.goldenCrownInvalidInputMessage);
        }
        Kingdom sender = kingdomFactory.getKingdom(Constants.goldenCrownRulerKingdomName);
        Kingdom receiver = kingdomFactory.getKingdom(receiverHouse);
        Message message = new Message(messageStr, sender, receiver);
        if(message.isValid())
            sender.addAlly(receiver);
        election.elect();
    }

    private void processQuestion(String input) {
        if(isRulerQuestion(input)) {
            if(election.winner() != null)
                System.out.println(Constants.goldenCrownRulerName);
            else
                System.out.println(Constants.noneFoundMessage);
        } else if(isAlliesQuestion(input)) {
            Kingdom winner = election.winner();
            if(winner != null && winner.numberOfAllies() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                Iterator<String> iterator = winner.getAlliesNames().iterator();
                while(iterator.hasNext()) {
                    stringBuilder.append(iterator.next());
                    stringBuilder.append(", ");
                }
                System.out.println(stringBuilder.substring(0, stringBuilder.length() - 2));
            } else
                System.out.println(Constants.noneFoundMessage);
        }
    }
}
