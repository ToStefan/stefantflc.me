package t.stefan.portfolio.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
        String content = "";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_HTML);
        if (confirmTokenService.confirmUser(token) == true) {
            content = "<!DOCTYPE html><html><head><title>Verification Succeeded</title></head><body>" +
                    "<div style=\"text-align: center;padding-top: 10%;\">" +
                    "<h1>E-mail successfully verified</h1>" +
                    "<a href=\"http://www.stefantflc.me/#/log-in\">" +
                    "<h1>~ <i><b>Go back</b></i> ~</h1></a>" +
                    "</div></body></html>";
            return new ResponseEntity<>(content, responseHeaders, HttpStatus.OK);
        }
        content = "<!DOCTYPE html><html><head><title>Unknown token</title></head><body>" +
                "<div style=\"text-align: center;padding-top: 10%;\">" +
                "<h1>Tokon not valid</h1>" +
                "<a href=\"http://www.stefantflc.me/\">" +
                "<h1>~ <i><b>Go back</b></i> ~</h1></a>" +
                "</div></body></html>";
        return new ResponseEntity<>(content, responseHeaders, HttpStatus.NOT_FOUND);
    }
}
