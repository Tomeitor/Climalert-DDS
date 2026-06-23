package com.example.climalert.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void enviarAlerta(double temperatura, int humedad) {
    SimpleMailMessage mensaje = new SimpleMailMessage();

    mensaje.setTo("admin@clima.com", "emergencias@clima.com", "meteorologia@clima.com");
    mensaje.setSubject("ALERTA METEOROLÓGICA: Condiciones extremas");

    mensaje.setText("Se han detectado condiciones climáticas peligrosas en CABA:\n\n" +
        "Temperatura actual: " + temperatura + "°C\n" +
        "Humedad actual: " + humedad + "%\n\n" +
        "Por favor, iniciar protocolos correspondientes.");

    try {
      mailSender.send(mensaje);
      System.out.println("Correos de alerta enviados exitosamente a las entidades.");
    } catch (Exception e) {
      System.err.println("Error al enviar el correo: " + e.getMessage());
    }
  }
}