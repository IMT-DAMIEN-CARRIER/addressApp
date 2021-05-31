package org.sam.mines.address.web.controller;

import org.sam.mines.address.service.TownService;
import org.sam.mines.address.api.controller.TownApi;
import org.sam.mines.address.api.model.Town;
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
public class TownController implements TownApi {
    private TownService townService;

    @Autowired
    public TownController(TownService townService) {
        this.townService = townService;
    }

    @Override
    public ResponseEntity<Town> create(Town town) {
        org.sam.mines.address.model.Town saved = townService.save(this.map(town));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(map(saved));
    }

    @Override
    public ResponseEntity<String> delete(String id) {
        townService.delete(UUID.fromString(id));

        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<Town> get(String id) {
        return townService.get(UUID.fromString(id))
                .map(this::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Town>> list() {
        return ResponseEntity.ok(townService.getAll().stream().map(this::map).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Town> update(Town town) {
        org.sam.mines.address.model.Town modelTown = this.map(town);

        if (townService.get(modelTown.getId()).isPresent()){
            return ResponseEntity.ok(this.map(townService.save(modelTown)));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    private org.sam.mines.address.model.Town map(Town town) {
        return org.sam.mines.address.model.Town.TownBuilder.aTown()
                .withId(town.getId() == null ? null : UUID.fromString(town.getId()))
                .withName(town.getName())
                .withPostCode(Integer.parseInt(town.getPostCode()))
                .build();
    }

    private Town map(org.sam.mines.address.model.Town town) {

        Town apiTown = new Town();
        apiTown.setId(town.getId().toString());
        apiTown.setName(town.getName());
        apiTown.setPostCode(String.valueOf(town.getPostCode()));

        return apiTown;
    }
}
