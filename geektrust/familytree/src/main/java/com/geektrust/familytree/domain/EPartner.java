package com.geektrust.familytree.domain;


import com.geektrust.familytree.entity.Person;

public class EPartner {
    private Person person;
    private Person partner;

    public EPartner(Person person, Person partner) {
        this.person = person;
        this.partner = partner;
    }

    public Person getPerson() {
        return person;
    }

    public Person getPartner() {
        return partner;
    }
}
