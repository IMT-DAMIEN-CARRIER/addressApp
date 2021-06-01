package org.sam.mines.address.service;

import org.sam.mines.address.model.Target;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TargetService {
    List<Target> getAll();

    Optional<Target> get(UUID id);

    Target save(Target target);

    void delete(UUID id);
}
