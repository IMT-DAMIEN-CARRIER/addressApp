package org.sam.mines.address.web.controller;

import org.sam.mines.address.api.controller.AddressApi;
import org.sam.mines.address.api.model.Address;
import org.sam.mines.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class AddressController implements AddressApi {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Override
    public ResponseEntity<Address> createAddress(Address address) {
        org.sam.mines.address.model.Address saved = addressService.save(this.map(address));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(map(saved));
    }

    @Override
    public ResponseEntity<String> deleteAddress(String id) {
        addressService.delete(UUID.fromString(id));

        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<Address> getAddressId(String id) {
        return addressService.get(UUID.fromString(id))
                .map(this::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Address>> listAddress() {
        return ResponseEntity.ok(
                addressService.getAll().stream().map(this::map).collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<Address> updateAddress(Address address) {
        return AddressApi.super.updateAddress(address);
    }

    private org.sam.mines.address.model.Address map(Address address) {
        return org.sam.mines.address.model.Address.AddressBuilder.anAddress()
                .withId(address.getId() == null ? null : UUID.fromString(address.getId()))
                .withNumber(address.getNumber())
                .withStreet(address.getStreet())
                .withTown(address.getTown())
                .withTargets(address.getTargets())
                .build();
    }

    private Address map(org.sam.mines.address.model.Address address) {
        Address addressApi = new Address();
        addressApi.setId(address.getId().toString());
        addressApi.setNumber(address.getNumber());
        addressApi.setStreet(address.getStreet());
        addressApi.setTown(address.getTown());
        addressApi.setTargets(address.getTargets());
    }
}
