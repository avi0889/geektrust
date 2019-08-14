package com.geektrust.familytree.domain;


import com.geektrust.familytree.entity.Person;

import java.util.*;

public class EDescendant {
    private Person person;
    private Set<Person> descendants;

    public EDescendant(Person person) {
        this.person = person;
        descendants = new HashSet<>();
    }

    public Person getPerson() {
        return person;
    }

    public Person addChild(Person p) {
        descendants.add(p);
        return person;
    }

    public Person addChildren(List<Person> children) {
        if(children != null) {
            Iterator<Person> iterator = children.iterator();
            while(iterator.hasNext()) {
                descendants.add(iterator.next());
            }
        }
        return person;
    }

    public List<Person> getChildren() {
        return Collections.unmodifiableList(new ArrayList<>(descendants));
    }
}
