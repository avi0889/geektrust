package com.geektrust.familytree.relations;


import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.entity.Person;
import com.geektrust.familytree.repository.RelativesRepository;

import java.util.ArrayList;
import java.util.List;

public class InLawRelation implements Relation {

    private boolean isMale;
    private RelativesRepository<EPartner, String> partnersRepository;
    private Relation siblingRelation;

    public InLawRelation(boolean isMale, RelativesRepository<EPartner, String> partnersRepository,
                         Relation siblingRelation) {
        this.isMale = isMale;
        this.partnersRepository = partnersRepository;
        this.siblingRelation = siblingRelation;
    }

    @Override
    public List<Person> getRelatives(Person person) {
        List<Person> inLaws = new ArrayList<>();
        if(partnersRepository.findOne(person.getName()) != null) {
            EPartner ePartner = partnersRepository.findOne(person.getName());
            if(ePartner != null) {
                Person partner = ePartner.getPartner();
                if (partner != null) {
                    inLaws.addAll(siblingRelation.getRelatives(partner));
                }
            }
        }
        List<Person> siblings = siblingRelation.getRelatives(person);
        for(Person sibling : siblings) {
            EPartner ePartner = partnersRepository.findOne(sibling.getName());
            if(ePartner != null) {
                Person siblingPartner = ePartner.getPartner();
                if (siblingPartner != null && siblingPartner.isMale() == isMale) {
                    inLaws.add(siblingPartner);
                }
            }
        }
        return inLaws;
    }
}
