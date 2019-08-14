package com.geektrust.familytree.relations;


import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.entity.Person;
import com.geektrust.familytree.repository.RelativesRepository;

import java.util.ArrayList;
import java.util.List;

public class PiblingRelation implements Relation {

    private boolean isParentMale;
    private boolean isMale;
    private Relation siblingRelation;
    private RelativesRepository<EPartner, String> partnersRepository;

    public PiblingRelation(boolean isParentMale, boolean isMale, Relation siblingRelation,
                           RelativesRepository<EPartner, String> partnersRepository) {
        this.isParentMale = isParentMale;
        this.isMale = isMale;
        this.siblingRelation = siblingRelation;
        this.partnersRepository = partnersRepository;
    }

    @Override
    public List<Person> getRelatives(Person person) {
        List<Person> relatives = new ArrayList<>();
        Person parent = null;
        if(isParentMale) {
            EPartner ePartner = partnersRepository.findOne(person.getMother().getName());
            if(ePartner != null)
                parent = ePartner.getPartner();
        }
        else
            parent = person.getMother();
        if(parent != null) {
            List<Person> siblings = siblingRelation.getRelatives(parent);
            for(Person sibling : siblings) {
                if(sibling != null && sibling.isMale() == isMale) {
                    relatives.add(sibling);
                }
            }
        }
        return relatives;
    }
}
