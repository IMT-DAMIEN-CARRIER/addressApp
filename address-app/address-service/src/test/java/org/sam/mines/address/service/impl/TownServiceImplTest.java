package org.sam.mines.address.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sam.mines.address.model.Address;
import org.sam.mines.address.model.Target;
import org.sam.mines.address.model.Town;
import org.sam.mines.address.persistence.TownRepository;
import org.sam.mines.address.service.TownService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TownServiceImplTest {

    @Mock
    private TownRepository townRepository;

    @InjectMocks
    private TownService townService = new TownServiceImpl(townRepository);

    @Test
    void shouldNotSaveWhenNameIsBlank() {
        Set<Address> addresses = new HashSet<Address>();
        
        Town townForAddress = Town.TownBuilder.aTown()
        .withName("Alès")
        .withPostCode(30100)
        .withAddresses(addresses)
        .withResidents(new HashSet<Target>())
        .build();

        Town town = Town.TownBuilder.aTown()
        .withPostCode(30100)
        .withAddresses(addresses)
        .withResidents(new HashSet<Target>())
        .build();

        // GIVEN
        Address address = Address.AddressBuilder.anAddress()
        .withId(new UUID(3,1))
        .withNumber(1)
        .withStreet("La meuh")
        .withTown(townForAddress)
        .withTargets(new HashSet<Target>())
        .build();

        addresses.add(address);

        // WHEN
        // THEN
        assertThrows(IllegalArgumentException.class, () -> townService.save(town));
    }

    @Test
    void shouldNotSaveWhenPostCodeIsBlank() {
        Set<Address> addresses = new HashSet<Address>();
        
        Town townForAddress = Town.TownBuilder.aTown()
        .withName("Alès")
        .withPostCode(30100)
        .withAddresses(addresses)
        .withResidents(new HashSet<Target>())
        .build();

        Town town = Town.TownBuilder.aTown()
        .withName("Alès")
        .withAddresses(addresses)
        .withResidents(new HashSet<Target>())
        .build();

        // GIVEN
        Address address = Address.AddressBuilder.anAddress()
        .withId(new UUID(3,1))
        .withNumber(1)
        .withStreet("La meuh")
        .withTown(townForAddress)
        .withTargets(new HashSet<Target>())
        .build();

        addresses.add(address);

        // WHEN
        // THEN
        assertThrows(IllegalArgumentException.class, () -> townService.save(town));
    }

    @Test
    void shouldNotSaveWhenAddressesIsEmpty() {
        Set<Address> addresses = new HashSet<Address>();
        
        Town townForAddress = Town.TownBuilder.aTown()
        .withName("Alès")
        .withPostCode(30100)
        .withAddresses(addresses)
        .withResidents(new HashSet<Target>())
        .build();

        Town town = Town.TownBuilder.aTown()
        .withName("Alès")
        .withPostCode(30100)
        .withResidents(new HashSet<Target>())
        .build();

        // GIVEN
        Address address = Address.AddressBuilder.anAddress()
        .withId(new UUID(3,1))
        .withNumber(1)
        .withStreet("La meuh")
        .withTown(townForAddress)
        .withTargets(new HashSet<Target>())
        .build();

        addresses.add(address);

        // WHEN
        // THEN
        assertThrows(NullPointerException.class, () -> townService.save(town));
    }

    @Test
    void shouldSaveAValidTown() {
        Set<Address> addresses = new HashSet<Address>();
        UUID generatedId = UUID.randomUUID();


        Town town = Town.TownBuilder.aTown()
        .withId(generatedId)
        .withName("Alès")
        .withPostCode(30100)
        .withAddresses(addresses)
        .withResidents(new HashSet<Target>())
        .build();

        // GIVEN

        Address address = Address.AddressBuilder.anAddress()
        .withId(generatedId)
        .withNumber(1)
        .withStreet("La meuh")
        .withTown(town)
        .withTargets(new HashSet<Target>())
        .build();

        addresses.add(address);

        when(townRepository.save(any(Town.class)))
                .thenReturn(town);

        // THEN
        assertEquals(generatedId, townService.save(town).getId());
    }

    @Test
    void shouldGetAll() {
        // GIVEN
        when(townRepository.findAll()).thenReturn(List.of(
                Town.TownBuilder.aTown().withName("town 1").withId(UUID.randomUUID()).build(),
                Town.TownBuilder.aTown().withName("town 2").withId(UUID.randomUUID()).build()
        ));

        // WHEN
        List<Town> all = townService.getAll();

        // THEN
        assertEquals(2, all.size());
    }

    @Test
    void shoudBeValid() {
        Set<Address> addresses = new HashSet<Address>();
        
        Town town = Town.TownBuilder.aTown()
        .withName("Alès")
        .withPostCode(30100)
        .withAddresses(addresses)
        .withResidents(new HashSet<Target>())
        .build();

        // GIVEN
        Address address = Address.AddressBuilder.anAddress()
        .withId(new UUID(3,1))
        .withNumber(1)
        .withStreet("La meuh")
        .withTown(town)
        .withTargets(new HashSet<Target>())
        .build();

        addresses.add(address);

        // WHEN
        // THEN
        assertTrue(this.townService.isTownValid(town));
    }
}