package com.geektrust.breakerofchains.ballot;


import com.geektrust.kingdom.Kingdom;
import com.geektrust.message.Message;

import java.util.*;

public class Ballot {

    private List<Message> messages = new ArrayList<>();

    public void addMessages(String message, Kingdom sender, List<Kingdom> receivers) {
        for(Kingdom receiver : receivers) {
            if(!receiver.equals(sender))
                messages.add(new Message(message, sender, receiver));
        }
    }

    public List<Message> pickNRandomMessages(int n) {
        Collections.shuffle(messages);
        List<Message> nMessages = new ArrayList<>();
        for(int i = 0; i < n; i++)
            nMessages.add(messages.get(i));
        return nMessages;
    }
}
