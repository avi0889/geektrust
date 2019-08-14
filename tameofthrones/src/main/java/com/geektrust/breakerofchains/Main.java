package com.geektrust.breakerofchains;



import com.geektrust.breakerofchains.election.BreakerOfChainsElection;
import com.geektrust.breakerofchains.processor.BreakerOfChainsInputProcessor;
import com.geektrust.exception.KingdomException;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.factory.KingdomFactoryImpl;
import com.geektrust.utils.Election;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            throw new FileNotFoundException("please pass input file path as the first argument to main class");
        }
        BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));

        //prepare a factory
        KingdomFactory kingdomFactory = new KingdomFactoryImpl();
        //prepare an election
        Election breakerOfChainsElection = new BreakerOfChainsElection(kingdomFactory);

        //initialize the processor with this election and factory
        BreakerOfChainsInputProcessor inputProcessor = new BreakerOfChainsInputProcessor(
                breakerOfChainsElection, kingdomFactory);

        String str;
        while((str = br.readLine()) != null) {
            try {
                inputProcessor.processInput(str);
            } catch (KingdomException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
