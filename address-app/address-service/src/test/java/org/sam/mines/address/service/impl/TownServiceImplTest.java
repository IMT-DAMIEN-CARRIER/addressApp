package org.sam.mines.address.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sam.mines.address.model.Town;
import org.sam.mines.address.persistence.TownRepository;
import org.sam.mines.address.service.TownService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        // GIVEN
        Town town = Town.TownBuilder.aTown().build();

        // WHEN
        // THEN
        assertThrows(IllegalArgumentException.class, () -> townService.save(town));
    }

    @Test
    void shouldSaveAValidTown() {
        // GIVEN
        Town town = Town.TownBuilder.aTown().withName("a town").build();

        // WHEN
        UUID generatedId = UUID.randomUUID();
        when(townRepository.save(any(Town.class)))
                .thenReturn(Town.TownBuilder.aTown().withId(generatedId).build());

        // THEN
        Town persisted = townService.save(town);
        assertEquals(persisted.getId(), generatedId);
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
}