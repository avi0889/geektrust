package com.geektrust.familytree.initialize;


import com.geektrust.familytree.domain.EDescendant;
import com.geektrust.familytree.domain.EPartner;
import com.geektrust.familytree.entity.Person;
import com.geektrust.familytree.repository.RelativesRepository;

import java.util.HashMap;
import java.util.Map;

public class Initializer {

    private RelativesRepository<EPartner, String> partnersRepository;
    private RelativesRepository<EDescendant, String> descendantsRepository;

    public Initializer(RelativesRepository<EPartner, String> partnersRepository,
                       RelativesRepository<EDescendant, String> descendantsRepository) {
        this.partnersRepository = partnersRepository;
        this.descendantsRepository = descendantsRepository;
    }

    public Map<String, Person> init() {
        Map<String, Person> personNameMap = new HashMap<>();

        Person kingShan = new Person("Shan", "male", null);
        Person queenAnga = new Person("Anga", "female", null);
        partnersRepository.save(new EPartner(kingShan, queenAnga));

        Person chit = new Person("Chit", "male", queenAnga);
        Person amba = new Person("Amba", "female", null);
        partnersRepository.save(new EPartner(chit, amba));
        Person ish = new Person("Ish", "male", queenAnga);
        Person vich = new Person("Vich", "male", queenAnga);
        Person lika = new Person("Lika", "female", null);
        partnersRepository.save(new EPartner(vich, lika));
        Person aras = new Person("Aras", "male", queenAnga);
        Person chitra = new Person("Chitra", "female", null);
        partnersRepository.save(new EPartner(aras, chitra));
        Person satya = new Person("Satya", "female", queenAnga);
        Person vyan = new Person("Vyan", "male", null);
        partnersRepository.save(new EPartner(satya, vyan));
        descendantsRepository.save(new EDescendant(queenAnga));
        descendantsRepository.findOne(queenAnga.getName()).addChild(chit);
        descendantsRepository.findOne(queenAnga.getName()).addChild(ish);
        descendantsRepository.findOne(queenAnga.getName()).addChild(vich);
        descendantsRepository.findOne(queenAnga.getName()).addChild(aras);
        descendantsRepository.findOne(queenAnga.getName()).addChild(satya);

        Person dritha = new Person("Dritha", "female", amba);
        Person jaya = new Person("Jaya", "male", null);
        partnersRepository.save(new EPartner(dritha, jaya));
        Person tritha = new Person("Tritha", "female", amba);
        Person vritha = new Person("Vritha", "male", amba);
        descendantsRepository.save(new EDescendant(amba));
        descendantsRepository.findOne(amba.getName()).addChild(dritha);
        descendantsRepository.findOne(amba.getName()).addChild(tritha);
        descendantsRepository.findOne(amba.getName()).addChild(vritha);

        Person vila = new Person("Vila", "female", lika);
        Person chika = new Person("Chika", "female", lika);
        descendantsRepository.save(new EDescendant(lika));
        descendantsRepository.findOne(lika.getName()).addChild(vila);
        descendantsRepository.findOne(lika.getName()).addChild(chika);

        Person arit = new Person("Arit", "male", null);
        Person jnki = new Person("Jnki", "female", chitra);
        partnersRepository.save(new EPartner(arit, jnki));
        Person ahit = new Person("Ahit", "male", chitra);
        descendantsRepository.save(new EDescendant(chitra));
        descendantsRepository.findOne(chitra.getName()).addChild(jnki);
        descendantsRepository.findOne(chitra.getName()).addChild(ahit);

        Person satvy = new Person("Satvy", "female", null);
        Person asva = new Person("Asva", "male", satya);
        partnersRepository.save(new EPartner(satvy, asva));
        Person krpi = new Person("Krpi", "female", null);
        Person vyas = new Person("Vyas", "male", satya);
        partnersRepository.save(new EPartner(krpi, vyas));
        Person atya = new Person("Atya", "female", satya);
        descendantsRepository.save(new EDescendant(satya));
        descendantsRepository.findOne(satya.getName()).addChild(asva);
        descendantsRepository.findOne(satya.getName()).addChild(vyas);
        descendantsRepository.findOne(satya.getName()).addChild(atya);

        Person yodhan = new Person("Yodhan", "male", dritha);
        descendantsRepository.save(new EDescendant(dritha));
        descendantsRepository.findOne(dritha.getName()).addChild(yodhan);

        Person laki = new Person("Laki", "male", jnki);
        Person lavnya = new Person("Lavnya", "female", jnki);
        descendantsRepository.save(new EDescendant(jnki));
        descendantsRepository.findOne(jnki.getName()).addChild(laki);
        descendantsRepository.findOne(jnki.getName()).addChild(lavnya);

        Person vasa = new Person("Vasa", "male", satvy);
        descendantsRepository.save(new EDescendant(satvy));
        descendantsRepository.findOne(satvy.getName()).addChild(vasa);

        Person kriya = new Person("Kriya", "male", krpi);
        Person krithi = new Person("Krithi", "female", krpi);
        descendantsRepository.save(new EDescendant(krpi));
        descendantsRepository.findOne(krpi.getName()).addChild(kriya);
        descendantsRepository.findOne(krpi.getName()).addChild(krithi);

        //add all the people in a map and return
        personNameMap.put("Shan", kingShan);
        personNameMap.put("Anga", queenAnga);
        personNameMap.put("Chit", chit);
        personNameMap.put("Amba", amba);
        personNameMap.put("Ish", ish);
        personNameMap.put("Vich", vich);
        personNameMap.put("Lika", lika);
        personNameMap.put("Aras", aras);
        personNameMap.put("Chitra", chitra);
        personNameMap.put("Satya", satya);
        personNameMap.put("Vyan", vyan);
        personNameMap.put("Dritha", dritha);
        personNameMap.put("Jaya", jaya);
        personNameMap.put("Tritha", tritha);
        personNameMap.put("Vritha", vritha);
        personNameMap.put("Vila", vila);
        personNameMap.put("Chika", chika);
        personNameMap.put("Arit", arit);
        personNameMap.put("Jnki", jnki);
        personNameMap.put("Ahit", ahit);
        personNameMap.put("Satvy", satvy);
        personNameMap.put("Asva", asva);
        personNameMap.put("Krpi", krpi);
        personNameMap.put("Vyas", vyas);
        personNameMap.put("Atya", atya);
        personNameMap.put("Yodhan", yodhan);
        personNameMap.put("Laki", laki);
        personNameMap.put("Lavnya", lavnya);
        personNameMap.put("Vasa", vasa);
        personNameMap.put("Kriya", kriya);
        personNameMap.put("Krithi", krithi);

        return personNameMap;
    }
}
