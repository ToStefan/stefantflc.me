package t.stefan.portfolio.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import t.stefan.portfolio.service.impl.ClientDetailsServiceImpl;
import t.stefan.portfolio.web.dto.ClientDetailsDTO;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/client-details")
public class ClientDetailsController {

    private final ClientDetailsServiceImpl clientdetailsService;

    @PreAuthorize("hasRole('MASTER')")
    @GetMapping
    public ResponseEntity<List<ClientDetailsDTO>> findAllByGroupByIp() {
         List<ClientDetailsDTO> dtos = clientdetailsService.findAllGroupByIp();
         return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MASTER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDetailsDTO> findById(@PathVariable("id") Long id) {
        ClientDetailsDTO dto = clientdetailsService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MASTER')")
    @GetMapping(value = "/ip/{ip}")
    public ResponseEntity<List<ClientDetailsDTO>> findAllByIp(@PathVariable("ip") String ip) {
        List<ClientDetailsDTO> dtos = clientdetailsService.findAllByIp(ip);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody ClientDetailsDTO clientDetailsDTO) {
        clientdetailsService.create(clientDetailsDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        clientdetailsService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}