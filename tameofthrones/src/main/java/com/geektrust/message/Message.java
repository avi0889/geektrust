package com.geektrust.message;


import com.geektrust.kingdom.Kingdom;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private String messageStr;
    private Kingdom sender;
    private Kingdom receiver;

    public Message(String messageStr, Kingdom sender, Kingdom receiver) {
        this.messageStr = messageStr;
        this.sender = sender;
        this.receiver = receiver;
    }

    public boolean isValid() {
        Map<Character, Integer> inputCountMap = getCountMap(messageStr.toLowerCase());
        for(Map.Entry<Character, Integer> entry : getCountMap(receiver.getEmblem().toLowerCase()).entrySet()) {
            Character character = entry.getKey();
            int count = entry.getValue();
            if(!inputCountMap.containsKey(character) || inputCountMap.get(character) < count)
                return false;
        }
        return true;
    }

    private Map<Character, Integer> getCountMap(String message) {
        Map<Character, Integer> messageCountMap = new HashMap<>();
        for(int i = 0; i < message.length(); i++) {
            int count = 0;
            if(messageCountMap.containsKey(message.charAt(i))) {
                count = messageCountMap.get(message.charAt(i));
            }
            messageCountMap.put(message.charAt(i), count + 1);
        }
        return messageCountMap;
    }

    public Kingdom getSender() {
        return this.sender;
    }

    public Kingdom getReceiver() {
        return this.receiver;
    }
}
