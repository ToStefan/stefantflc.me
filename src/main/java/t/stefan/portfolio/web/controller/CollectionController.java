package t.stefan.portfolio.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t.stefan.portfolio.service.CollectionService;
import t.stefan.portfolio.service.impl.CollectionServiceImpl;
import t.stefan.portfolio.web.dto.CollectionDTO;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/collections")
public class CollectionController {

    private final CollectionServiceImpl collectionService;

    @GetMapping
    public ResponseEntity<List<CollectionDTO>> getAll(){
        List<CollectionDTO> collections = collectionService.findAll();
        return new ResponseEntity<>(collections, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CollectionDTO> getCollection(@PathVariable("id") Long id){
        CollectionDTO dto = collectionService.findById(id);
        return  new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CollectionDTO> create(@RequestBody CollectionDTO collectionDTO){
        CollectionDTO dto = collectionService.create(collectionDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        collectionService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
