package com.tupa.mipetagram;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail extends AsyncTask<Void,Void,Void> {

    private Context context;
    private Session session;
    private String correo;
    private String nombre;
    private String mensaje;
    private ProgressDialog progressDialog;

    public SendMail(Context context, String correo, String nombre, String mensaje){
        this.context = context;
        this.correo = correo;
        this.nombre = nombre;
        this.mensaje = mensaje;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,"Enviando mensaje","Por favor espere...",false,false);
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Toast.makeText(context,"Mensaje enviado",Toast.LENGTH_LONG).show();
    }
    @Override
    protected Void doInBackground(Void... params) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.user", ConfigMail.EMAIL);
        props.put("mail.password", ConfigMail.PASSWORD);

        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConfigMail.EMAIL, ConfigMail.PASSWORD);
            }
        });
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(ConfigMail.EMAIL, "Android"));
            InternetAddress[] internetAddresses = {
                    new InternetAddress(correo),
            };
            mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);
            mimeMessage.setSubject("*"+nombre+"*");
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setText(mensaje);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            mimeMessage.setContent(multipart);
            Transport transport = session.getTransport("smtp");
            transport.connect(ConfigMail.EMAIL, ConfigMail.PASSWORD);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        }
        catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
