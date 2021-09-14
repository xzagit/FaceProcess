package cn.xuziao.faceprocess.tools;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


/**
 * @author 86188
 */

public class SendIdentifyingCode {

    public SendIdentifyingCode(String address, String content) {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(prop);
        session.setDebug(true);
        Transport ts = null;
        try {
            ts = session.getTransport();
            ts.connect("smtp.qq.com", "greenorangestack@foxmail.com", "jppdelxzytmugfhd");
            Message message = createSimpleMail(session, address, content);
            ts.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ts != null;
                ts.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public MimeMessage createSimpleMail(Session session, String address, String content) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("greenorangestack@foxmail.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(address));
        message.setSubject("FaceProcessor验证消息");
        message.setContent(content, "text/html;charset=UTF-8");
        return message;
    }

}

