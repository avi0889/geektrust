package com.geektrust.breakerofchains.election;


import com.geektrust.breakerofchains.ballot.Ballot;
import com.geektrust.factory.KingdomFactory;
import com.geektrust.factory.KingdomFactoryImpl;
import com.geektrust.kingdom.Kingdom;
import com.geektrust.message.Message;
import com.geektrust.utils.Constants;
import com.geektrust.utils.Election;

import java.util.*;

public class BreakerOfChainsElection implements Election {

    private Set<Kingdom> contestants;
    private Kingdom winner;
    private KingdomFactory kingdomFactory;

    public BreakerOfChainsElection(KingdomFactory kingdomFactory) {
        this.kingdomFactory = kingdomFactory;
        contestants = new HashSet<>();
    }

    @Override
    public void elect() {
        elect(contestants, 1);
    }

    @Override
    public Kingdom winner() {
        return winner;
    }

    @Override
    public void addContestant(Kingdom contestant) {
        contestants.add(contestant);
    }

    private void elect(Set<Kingdom> competingKingdoms, int level) {
        //prepare the ballot for this round
        Ballot ballot = prepareBallot(competingKingdoms);

        //reset allies of the competing kingdoms
        Iterator<Kingdom> iterator = competingKingdoms.iterator();
        while(iterator.hasNext())
            iterator.next().resetAllies();

        //set to ensure a kingdom does not give more than one allegiance
        Set<Kingdom> alliedKingdoms = new HashSet<>();

        //map to keep count of votes
        Map<Kingdom, Integer> voteCount = new HashMap<>();
        //initialize the vote count to zero for all contestants
        iterator = competingKingdoms.iterator();
        while(iterator.hasNext())
            voteCount.put(iterator.next(), 0);

        //pick 6 messages and begin vote count
        for(Message message : ballot.pickNRandomMessages(kingdomFactory.totalNumberOfKingdoms())) {
            if(!competingKingdoms.contains(message.getReceiver()) && !alliedKingdoms.contains(message.getReceiver())) {
                if(message.isValid()) {
                    Kingdom contestant = message.getSender();
                    //increment the vote count for this sender by one
                    int count = voteCount.get(contestant);
                    voteCount.put(contestant, count + 1);

                    //add this receiver as an ally of this sender
                    contestant.addAlly(message.getReceiver());

                    //add this receiver to allied ones' set so that it can't ally again
                    alliedKingdoms.add(message.getReceiver());
                }
            }
        }

        //print the output of this round
        System.out.println("Results after round " + level + " ballot count");
        for(Map.Entry<Kingdom, Integer> entry : voteCount.entrySet())
            System.out.println("Allies for " + entry.getKey().getName() + " : " + entry.getValue());

        //find if it is a tie
        if(isTie(voteCount)) {
            elect(fetchTiedWinners(voteCount), level + 1);
        } else {
            setWinner(voteCount);
        }
    }

    private boolean isTie(Map<Kingdom, Integer> voteCounts) {
        int firstPositionCount = 0;
        int secondPositionCount = 0;
        for(int i : voteCounts.values()) {
            if(i > firstPositionCount) {
                secondPositionCount = firstPositionCount;
                firstPositionCount = i;
            } else if(i > secondPositionCount) {
                secondPositionCount = i;
            }
        }
        return firstPositionCount == secondPositionCount;
    }

    private Set<Kingdom> fetchTiedWinners(Map<Kingdom, Integer> voteCounts) {
        //find the maximum count
        int firstPositionCount = -1;
        for (Map.Entry<Kingdom, Integer> entry : voteCounts.entrySet()) {
            if (entry.getValue() > firstPositionCount)
                firstPositionCount = entry.getValue();
        }
        //find all the kingdoms with same count
        Set<Kingdom> tiedWinners = new HashSet<>();
        for (Map.Entry<Kingdom, Integer> entry : voteCounts.entrySet()) {
            if (entry.getValue() == firstPositionCount)
                tiedWinners.add(entry.getKey());
        }
        return tiedWinners;
    }

    private void setWinner(Map<Kingdom, Integer> voteCounts) {
        int firstPositionCount = 0;
        Kingdom firstPositionKingdom = null;
        for (Map.Entry<Kingdom, Integer> entry : voteCounts.entrySet()) {
            if (entry.getValue() > firstPositionCount) {
                firstPositionCount = entry.getValue();
                firstPositionKingdom = entry.getKey();
            }
        }
        winner = firstPositionKingdom;
    }

    private Ballot prepareBallot(Set<Kingdom> contestants) {
        Ballot ballot = new Ballot();
        for(Kingdom kingdom : contestants) {
            //add this contestant's message, to all the other kingdoms, in the ballot
            ballot.addMessages(
                    Constants.messagesList.get((new Random()).nextInt(Constants.messagesList.size())),
                    kingdom, kingdomFactory.getAllKingdoms());
        }
        return ballot;
    }
}
