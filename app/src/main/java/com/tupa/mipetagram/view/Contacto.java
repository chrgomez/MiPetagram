package com.tupa.mipetagram.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.tupa.mipetagram.R;
import com.tupa.mipetagram.SendMail;

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


public class Contacto extends AppCompatActivity {

    private Session session;
    private TextInputEditText txtNombre;
    private TextInputEditText txtCorreo;
    private TextInputEditText txtMensaje;
    private Button btnEnviarComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtNombre = (TextInputEditText) findViewById(R.id.txtNombre);
        txtCorreo = (TextInputEditText) findViewById(R.id.txtCorreo);
        txtMensaje = (TextInputEditText) findViewById(R.id.txtMensaje);
        btnEnviarComentario = (Button) findViewById(R.id.btnEnviarComentario);
        btnEnviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
                limpiarCampos();
            }
        });
    }

    private void limpiarCampos() {
        txtCorreo.setText(null);
        txtMensaje.setText(null);
        txtNombre.setText(null);
    }

    private void sendEmail() {
        String correo = txtCorreo.getText().toString().trim();
        String asunto = txtNombre.getText().toString().trim();
        String mensaje = txtMensaje.getText().toString().trim();
        SendMail sm = new SendMail(this, correo, asunto, mensaje);
        sm.execute();
    }

}
