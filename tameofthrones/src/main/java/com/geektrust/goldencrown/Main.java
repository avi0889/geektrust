package com.geektrust.goldencrown;

import com.geektrust.exception.KingdomException;
import com.geektrust.exception.NoSuchKingdomException;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.factory.KingdomFactoryImpl;
import com.geektrust.goldencrown.election.GoldenCrownElection;
import com.geektrust.goldencrown.processor.GoldenCrownInputProcessor;
import com.geektrust.utils.Constants;
import com.geektrust.utils.Election;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchKingdomException {
        if(args.length < 1) {
            throw new FileNotFoundException("please pass input file path as the first argument to main class");
        }
        BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));

        //prepare a factory for this run
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        //prepare an election for this run
        Election goldenCrownElection = new GoldenCrownElection();
        //add King Shah to the election
        goldenCrownElection.addContestant(kingdomFactory.getKingdom(Constants.goldenCrownRulerKingdomName));

        //initialize the processor with the election and factory
        GoldenCrownInputProcessor inputParser = new GoldenCrownInputProcessor(goldenCrownElection, kingdomFactory);

        String str;
        while((str = br.readLine()) != null) {
            try {
                inputParser.processInput(str);
            } catch (KingdomException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
