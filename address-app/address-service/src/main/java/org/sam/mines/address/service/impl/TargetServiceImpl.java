package org.sam.mines.address.service.impl;

import org.sam.mines.address.model.Target;
import org.sam.mines.address.persistence.TargetRepository;
import org.sam.mines.address.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TargetServiceImpl implements TargetService {
    private final TargetRepository targetRepository;

    @Autowired
    public TargetServiceImpl(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public List<Target> getAll() {
        return this.targetRepository.findAll();
    }

    @Override
    public Optional<Target> get(UUID id) {
        return this.targetRepository.findById(id);
    }

    @Override
    public Target save(Target target) throws IllegalArgumentException {
        if (target.getFirstname().isEmpty() || target.getFirstname().isBlank()) {
            throw new IllegalArgumentException("Firstname is required");
        }

        if (target.getName().isEmpty() || target.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }

        if (target.getAddresses().isEmpty()) {
            throw new IllegalArgumentException("Address is required");
        }

        if (target.getPhoneNumber().getPhoneNumber().isEmpty() || target.getPhoneNumber().getPhoneNumber().isBlank()) {
            throw new IllegalArgumentException("Phone number is required");
        }

        return this.targetRepository.save(target);
    }

    @Override
    public void delete(UUID id) {
        this.targetRepository.deleteById(id);
    }
}
