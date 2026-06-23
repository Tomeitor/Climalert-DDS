package com.example.climalert.services;

import com.example.climalert.dto.WeatherResponseDTO;
import com.example.climalert.models.RegistroClima;
import com.example.climalert.repositories.RegistroClimaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class ClimaService {

  @Value("${weather.api.url}")
  private String apiUrl;

  @Value("${weather.api.key}")
  private String apiKey;

  @Value("${weather.api.location}")
  private String location;

  private final RegistroClimaRepository repository;
  private final RestTemplate restTemplate;

  public ClimaService(RegistroClimaRepository repository) {
    this.repository = repository;
    this.restTemplate = new RestTemplate();
  }

  @Scheduled(fixedRate = 300000)
  public void obtenerYGuardarClima() {
    try {
      String urlFinal = apiUrl + "?key=" + apiKey + "&q=" + location;

      WeatherResponseDTO response = restTemplate.getForObject(urlFinal, WeatherResponseDTO.class);

      if (response != null && response.current() != null) {
        double temp = response.current().temperaturaC();
        int hum = response.current().humidity();

        RegistroClima nuevoRegistro = new RegistroClima(temp, hum, LocalDateTime.now());
        repository.save(nuevoRegistro);

        System.out.println("Clima guardado exitosamente: " + temp + "°C, Humedad: " + hum + "%");
      }

    } catch (Exception e) {
      System.err.println("Error al consultar la API del clima: " + e.getMessage());
    }
  }
}