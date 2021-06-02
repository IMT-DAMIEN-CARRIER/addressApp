package org.sam.mines.address.service.impl;

import org.sam.mines.address.service.TownService;
import org.sam.mines.address.model.Town;
import org.sam.mines.address.persistence.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public List<Town> getAll() {
        return townRepository.findAll();
    }

    @Override
    public Optional<Town> get(UUID id) {
        return townRepository.findById(id);
    }

    @Override
    public Town save(Town town) {
        isTownValid(town);

        return townRepository.save(town);
    }

    public boolean isTownValid(Town town) throws IllegalArgumentException
    {
        if (town.getName() == null || town.getName().isBlank()){
            throw new IllegalArgumentException("Name is required");
        }

        if (town.getPostCode() < 1000 || town.getPostCode() > 99999) {
            throw new IllegalArgumentException("PostCode is invalid");
        }

        if (town.getAddresses().isEmpty()) {
            throw new IllegalArgumentException("Adresses are needed");
        }

        return true;
    }

    @Override
    public void delete(UUID id) {
        townRepository.deleteById(id);
    }
}
