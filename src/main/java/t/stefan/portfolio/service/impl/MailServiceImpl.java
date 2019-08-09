package t.stefan.portfolio.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import t.stefan.portfolio.entity.ConfirmationToken;
import t.stefan.portfolio.service.MailService;
import t.stefan.portfolio.util.Constants;
import t.stefan.portfolio.web.dto.MailDTO;
import t.stefan.portfolio.web.dto.UserDTO;
import t.stefan.portfolio.web.mapper.UserMapper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final ConfirmationTokenServiceImpl confirmTokenService;
    private final UserMapper userMapper;

    @Override
    public void userConfirmation(UserDTO user) {

        ConfirmationToken token = confirmTokenService.generateToken(userMapper.toEntity(user));
        String confirmLink = Constants.basePath + "/api/tokens/confirm/" + token.getConfirmationToken();

        String body = "<h3 style=\"color:green;\">Account confirmation!</h3>" +
                "<p>Click " +
                "<a href=" + confirmLink + " target=\"_blank\">this</a>" +
                " to confirm account.</p>";

        String subject = "stefantflc.me - Account Confirmation";
        MailDTO mail = new MailDTO(user.getEmail(), body, subject);
        sendEmail(mail);
    }

    @Override
    public void sendEmail(MailDTO mail) {

        Message msg = new MimeMessage(getMailProps());
        try {
            msg.setFrom(new InternetAddress(Constants.appMail, false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getRecipient()));
            msg.setSubject(mail.getSubject());
            msg.setContent(mail.getContent(), "text/html");

            Transport.send(msg);

        } catch (MessagingException e) {
            log.error("MessagingException -> There is an error while sending mail...");
        }
    }

    private Session getMailProps(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constants.appMail, Constants.appMailPass);
            }
        });
        return session;
    }
}
