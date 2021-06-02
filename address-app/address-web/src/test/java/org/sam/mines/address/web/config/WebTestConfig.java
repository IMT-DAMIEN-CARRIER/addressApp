package org.sam.mines.address.web.config;

import org.sam.mines.address.model.PhoneNumber;
import org.sam.mines.address.persistence.AddressRepository;
import org.sam.mines.address.persistence.PhoneNumberRepository;
import org.sam.mines.address.persistence.TargetRepository;
import org.sam.mines.address.persistence.TownRepository;
import org.sam.mines.address.service.AddressService;
import org.sam.mines.address.service.TargetService;
import org.sam.mines.address.service.TownService;
import org.sam.mines.address.web.controller.TownController;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebTestConfig {
    @MockBean
    private TownService townService;

    @MockBean
    private TownRepository townRepository;

    @MockBean
    private TargetService targetService;

    @MockBean
    private TargetRepository targetRepository;

    @MockBean
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private PhoneNumberRepository phoneNumberRepository;

}
