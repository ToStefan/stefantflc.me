package t.stefan.portfolio.service;

import t.stefan.portfolio.entity.ConfirmationToken;
import t.stefan.portfolio.entity.User;

public interface ConfirmationTokenService {

    ConfirmationToken generateToken(User user);
    Boolean confirmUser(String confirmationToken);
}
