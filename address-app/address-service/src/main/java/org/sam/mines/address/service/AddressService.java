package org.sam.mines.address.service;

import org.sam.mines.address.model.Address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressService
{
    List<Address> getAll();
    Optional<Address> get(UUID id);
    Address save(Address address);
    void delete(UUID id);
}
