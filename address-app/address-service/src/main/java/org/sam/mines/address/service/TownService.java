package org.sam.mines.address.service;

import org.sam.mines.address.model.Town;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TownService {

    List<Town> getAll();

    Optional<Town> get(UUID id);

    Town save(Town town);

    void delete(UUID id);
}
