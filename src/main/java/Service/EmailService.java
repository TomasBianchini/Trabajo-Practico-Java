package Service;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import entities.Pasaje;

public class EmailService {

	private String password = "igvntsmwdfutypsh";
	private String mail_from = "vuelos316@gmail.com";
	private Session session;

	public EmailService() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com.");
		properties.put("mail.smtp.socketFactory.port", "587");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.user", "usuario");
		properties.put("mail.smtp.auth", "true");

		properties.put("mail.smtp.port", "587");
		session = Session.getDefaultInstance(properties);
	}

	public void sendEmail(String subject, Pasaje p, String to) throws Exception {
		try {
			String texto = "Informacion Personal:" + p.getUsuario().getNombre() + p.getUsuario().getApellido()
					+ "Detalles de vuelo:" + p.getVuelo().getAeropuertoDestino().getNombre() + p.getAsiento().getFila()
					+ p.getAsiento().getNumero() + p.getAsiento().getTipo();

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail_from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject(subject);

			MimeMultipart multipart = new MimeMultipart("related");

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(texto);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport t = session.getTransport("smtp");
			t.connect(mail_from, this.password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		} catch (MessagingException me) {
			throw me;
		}

	}

}
