package org.sam.mines.address.persistence.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sam.mines.address.model.PhoneNumber;
import org.sam.mines.address.persistence.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners({SqlScriptsTestExecutionListener.class, TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(classes = {PersistenceTestConfig.class})
@Sql(scripts = {"/data/clear.sql", "/data/phoneNumber.sql"})
public class PhoneNumberRepositoryTest {
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Test
    public void shouldFindAll() {
        List<PhoneNumber> allTarget = phoneNumberRepository.findAll();

        assertEquals(2, allTarget.size());
    }

    @Test
    public void shoudFindAllByNumber() {
        assertEquals(1, phoneNumberRepository.findPhoneNumberByPhoneNumber("0677777777").size());
    }

    @Test
    public void shouldNotFindAllNumbers() {
        assertNotEquals(2, phoneNumberRepository.findPhoneNumberByPhoneNumber("0677777777").size());
    }
}
