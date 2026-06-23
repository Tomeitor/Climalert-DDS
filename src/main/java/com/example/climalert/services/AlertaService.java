package com.example.climalert.services;

import com.example.climalert.models.RegistroClima;
import com.example.climalert.repositories.RegistroClimaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlertaService {

  private final RegistroClimaRepository repository;
  private final EmailService emailService;

  public AlertaService(RegistroClimaRepository repository, EmailService emailService) {
    this.repository = repository;
    this.emailService = emailService;
  }

  @Scheduled(fixedRate = 60000)
  public void procesarAlertas() {

    Optional<RegistroClima> ultimoRegistroOpt = repository.findTopByOrderByFechaHoraDesc();

    if (ultimoRegistroOpt.isPresent()) {
      RegistroClima ultimoRegistro = ultimoRegistroOpt.get();

      double temp = ultimoRegistro.getTemperatura();
      int hum = ultimoRegistro.getHumedad();

      if (temp > 35.0 && hum > 60) {
        System.out.println("¡ALERTA! Superados los umbrales (Temp: " + temp + " - Hum: " + hum + "). Disparando correos...");
        emailService.enviarAlerta(temp, hum);
      } else {
        System.out.println("Análisis de clima: Todo normal. (Temp: " + temp + " - Hum: " + hum + ")");
      }
    } else {
      System.out.println("Análisis de clima: Aún no hay datos para procesar.");
    }
  }
}