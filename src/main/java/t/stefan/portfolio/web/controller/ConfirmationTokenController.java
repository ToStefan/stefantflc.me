package t.stefan.portfolio.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t.stefan.portfolio.service.impl.ConfirmationTokenServiceImpl;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/tokens")
public class ConfirmationTokenController {

    private ConfirmationTokenServiceImpl confirmTokenService;

    @GetMapping(value = "/confirm/{token}")
    public ResponseEntity<String> search(@PathVariable String token) {
        if(confirmTokenService.confirmUser(token) == true){
            return new ResponseEntity<>("Successfully confirmed", HttpStatus.OK);
        }
        return new ResponseEntity<>("Token not valid!", HttpStatus.OK);
    }
}
