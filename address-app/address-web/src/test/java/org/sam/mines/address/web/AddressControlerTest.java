package org.sam.mines.address.web;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.sam.mines.address.model.PhoneNumber;
import org.sam.mines.address.model.Address;
import org.sam.mines.address.model.Target;
import org.sam.mines.address.model.Town;
import org.sam.mines.address.service.AddressService;
import org.sam.mines.address.web.config.WebTestConfig;
import org.sam.mines.address.web.controller.AddressController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(AddressController.class)
@Import(WebTestConfig.class)
public class AddressControlerTest {

    private MockMvc mockMvc;

    @Autowired
    public AddressService addressService;

    @Autowired
    public AddressControlerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void shouldGetAnAddress() throws Exception {
        // Given
        Address address = this.generateAddress();

        // When
        when(addressService.get(eq(address.getId()))).thenReturn(Optional.of(
            address
        ));

        //Then
        mockMvc.perform(MockMvcRequestBuilders.get("/address/%s".formatted(address.getId().toString()))
            .accept(MediaType.ALL))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.street").value("Osef"));
    }

    @Test
    void shouldSaveAnAdress() throws Exception
    {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        // Given
        Address address = this.generateAddress();
        String json = ow.writeValueAsString(address);

        // When
        when(addressService.save(address)).thenReturn(address);

        // Then
        this.mockMvc.perform(MockMvcRequestBuilders.post("/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    private Address generateAddress()
    {
        UUID uuid = UUID.randomUUID();
        UUID uuidTown = UUID.randomUUID();
        UUID uuidTarget = UUID.randomUUID();
        UUID uuidAddress = UUID.randomUUID();

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("0677777777");

        HashSet<Address> addresses = new HashSet<Address>();
        Address address = Address.AddressBuilder.anAddress()
                .withId(uuid)
                .withStreet("Osef").build();


        HashSet<Target> residents = new HashSet<Target>();
        Target target = new Target();
        target.setId(uuidTarget);
        target.setName("Damien");
        target.setPhoneNumber(phoneNumber);
        target.setFirstname("Toto");
        target.setAddresses(addresses);
        residents.add(target);

        Town town = Town.TownBuilder.aTown()
                .withId(uuidTown)
                .withName("Alès")
                .withPostCode(30100)
                .withResidents(residents)
                .withAddresses(new HashSet<Address>()).build();

        address.setTown(town);
        address.setTargets(new HashSet<Target>());
        addresses.add(address);

        return address;
    }
}
