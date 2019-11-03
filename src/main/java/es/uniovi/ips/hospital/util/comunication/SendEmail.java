package es.uniovi.ips.hospital.util.comunication;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import es.uniovi.ips.hospital.domain.Appointment;

import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Gestiona el envio de un email con notificacion al doctor asignado a la cita
 *
 * @author Ricardo Soto, uo265710
 */
public class SendEmail {

    private final String username = "emailhospitalips@gmail.com";
    private final String password = "Ips201920";
    private Appointment appointment;
    private String addresses;

    public SendEmail(Appointment appointment) {
        this.appointment = appointment;
        this.addresses = appointment.getDoctors().stream().map( n -> n.getEmail() ).collect( Collectors.joining( "," ) );
    }

    /**
     * Envio del email
     *
     * @throws AddressException
     * @throws MessagingException
     */
    public void execute() throws AddressException, MessagingException {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("emailhospitalips@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(addresses)
        );
        message.setSubject("Aviso de cita URGENTE");
        message.setText("Le informamos que la siguiente cita:\n"
                + appointment.guiToString() + "\n Ha sido marcada como urgente. \n\t Saludos, la administraci�n del hospital");

        Transport.send(message);
    }

}