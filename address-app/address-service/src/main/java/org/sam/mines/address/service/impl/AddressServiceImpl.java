package org.sam.mines.address.service.impl;

import org.sam.mines.address.model.Address;
import org.sam.mines.address.model.Town;
import org.sam.mines.address.persistence.AddressRepository;
import org.sam.mines.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AddressServiceImpl implements AddressService
{
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
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
    public Address save(Address address) {
        if (address.getNumber() == 0) {
            throw new IllegalArgumentException("Number is required");
        } else if (address.getStreet().isEmpty()) {
            throw new IllegalArgumentException("No addresses referenced for ");
        } else if (!isTownValid(address.getTown())) {

        } else if () {

        }

        return this.addressRepository.save(address);
    }

    private boolean isTownValid(Town town) throws IllegalArgumentException {
        if (town.getPostCode() == 0) {
            throw new IllegalArgumentException("Post code is required for each");
        }

        if (town.getName().isEmpty()) {
            throw new IllegalArgumentException("Town name must be setted");
        }
        return true;
    }

    @Override
    public void delete(UUID id) {
        this.addressRepository.deleteById(id);
    }
}
