package com.geektrust.familytree.repository;


import com.geektrust.familytree.domain.EPartner;

import java.util.List;
import java.util.Map;

public class PartnerRepository implements RelativesRepository<EPartner, String> {

    private Map<String, EPartner> repository;

    public PartnerRepository(Map<String, EPartner> repository) {
        this.repository = repository;
    }

    @Override
    public EPartner save(EPartner ePartner) {
        repository.put(ePartner.getPerson().getName(), ePartner);
        repository.put(ePartner.getPartner().getName(), new EPartner(ePartner.getPartner(), ePartner.getPerson()));
        return repository.get(ePartner.getPerson().getName());
    }

    @Override
    public EPartner findOne(String s) {
        return repository.get(s);
    }

    @Override
    public List<EPartner> findAll(EPartner ePartner) {
        throw new UnsupportedOperationException();
    }
}
