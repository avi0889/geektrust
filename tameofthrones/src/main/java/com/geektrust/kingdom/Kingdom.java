package com.geektrust.kingdom;

import java.util.*;

public class Kingdom {

    private final String emblem;
    private final String name;

    private Set<Kingdom> allies;

    public Kingdom(String emblem, String name) {
        this.emblem = emblem;
        this.name = name;
        allies = new HashSet<>();
    }

    public String getEmblem() {
        return emblem;
    }

    public String getName() {
        return name;
    }

    public void addAlly(Kingdom kingdom) {
        allies.add(kingdom);
    }

    public void resetAllies() {
        this.allies = new HashSet<>();
    }

    public int numberOfAllies() {
        return allies.size();
    }

    public List<String> getAlliesNames() {
        List<String> names = new ArrayList<>();
        Iterator<Kingdom> iterator = allies.iterator();
        while(iterator.hasNext()) {
            names.add(iterator.next().getName());
        }
        return Collections.unmodifiableList(names);
    }

    public boolean equals(Object object) {
        if(object instanceof Kingdom) {
            Kingdom kingdom = (Kingdom) object;
            if (kingdom.getName().equals(this.name))
                return true;
        }
        return false;
    }

    public int hashCode() {
        return 31 * this.name.hashCode();
    }
}
