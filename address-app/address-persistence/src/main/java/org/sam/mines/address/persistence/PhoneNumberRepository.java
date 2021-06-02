package org.sam.mines.address.persistence;

import org.sam.mines.address.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, UUID> {
    List<PhoneNumber> findPhoneNumberByPhoneNumber(String phoneNumber);
}
