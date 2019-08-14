package com.geektrust.breakerofchains.processor;


import com.geektrust.exception.InvalidInputException;
import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.utils.Constants;
import com.geektrust.utils.Election;
import com.geektrust.factory.KingdomFactoryImpl;
import com.geektrust.utils.BaseInputProcessor;

import java.util.*;

public class BreakerOfChainsInputProcessor extends BaseInputProcessor {

    private Election election;
    private KingdomFactory kingdomFactory;

    public BreakerOfChainsInputProcessor(Election election, KingdomFactory kingdomFactory) {
        this.election = election;
        this.kingdomFactory = kingdomFactory;
    }

    @Override
    public void processInput(String input) throws NoSuchKingdomException, InvalidInputException {
        if(isQuestion(input)) {
            processQuestion(input);
        } else {
            electRuler(input);
        }
    }

    private void processQuestion(String input) {
        if(isRulerQuestion(input)) {
            if(election == null || election.winner() == null)
                System.out.println(Constants.noneFoundMessage);
            else
                System.out.println(election.winner().getName());
        } else if(isAlliesQuestion(input)) {
            if(election == null || election.winner() == null || election.winner().numberOfAllies() == 0)
                System.out.println(Constants.noneFoundMessage);
            else {
                StringBuilder stringBuilder = new StringBuilder();
                Iterator<String> iterator = election.winner().getAlliesNames().iterator();
                while (iterator.hasNext()) {
                    stringBuilder.append(iterator.next());
                    stringBuilder.append(" ");
                }
                System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
            }
        }
    }

    private void electRuler(String input) throws InvalidInputException, NoSuchKingdomException {
        //parse input and add competing kingdoms to election
        String[] houses = input.split(" ");
        if(houses.length <= 1)
            throw new InvalidInputException(Constants.breakerOfChainsInvalidInputMessage);
        for(String house : houses) {
            election.addContestant(kingdomFactory.getKingdom(house));
        }
        //trigger the elections
        election.elect();
    }
}
