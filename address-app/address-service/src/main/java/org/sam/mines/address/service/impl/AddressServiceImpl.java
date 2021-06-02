package org.sam.mines.address.service.impl;

import org.sam.mines.address.model.Address;
import org.sam.mines.address.persistence.AddressRepository;
import org.sam.mines.address.service.AddressService;
import org.sam.mines.address.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AddressServiceImpl implements AddressService
{
    private final AddressRepository addressRepository;
    private final TownService townService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, TownService townService) {
        this.addressRepository = addressRepository;
        this.townService = townService;
    }

    @Override
    public List<Address> getAll() {
        return this.addressRepository.findAll();
    }

    @Override
    public Optional<Address> get(UUID id) {
        return this.addressRepository.findById(id);
    }

    @Override
    public Address save(Address address) throws IllegalArgumentException {
        this.townService.isTownValid(address.getTown());

        if (address.getNumber() == 0) {
            throw new IllegalArgumentException("Number is required");
        } else if (address.getStreet().isEmpty()) {
            throw new IllegalArgumentException("No addresses referenced for");
        } else if (address.getTargets().isEmpty()) {
            throw new IllegalArgumentException("Targets are required");
        }

        return this.addressRepository.save(address);
    }

    @Override
    public void delete(UUID id) {
        this.addressRepository.deleteById(id);
    }
}
