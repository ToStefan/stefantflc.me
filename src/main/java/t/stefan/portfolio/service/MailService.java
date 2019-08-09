package t.stefan.portfolio.service;


import t.stefan.portfolio.web.dto.MailDTO;
import t.stefan.portfolio.web.dto.UserDTO;

public interface MailService {

    void userConfirmation(UserDTO user);
    void sendEmail(MailDTO mail);
}
