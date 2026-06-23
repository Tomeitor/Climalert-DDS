package com.example.climalert.services;

import com.example.climalert.models.RegistroClima;
import com.example.climalert.repositories.RegistroClimaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertaServiceTest {

  @Mock
  private RegistroClimaRepository repository;

  @Mock
  private EmailService emailService;

  @InjectMocks
  private AlertaService alertaService;

  @Test
  void procesarAlertas_DebeLlamarAlEmailService_CuandoHayClimaCritico() {

    RegistroClima climaCritico = new RegistroClima(38.0, 65, LocalDateTime.now());
    when(repository.findTopByOrderByFechaHoraDesc()).thenReturn(Optional.of(climaCritico));

    alertaService.procesarAlertas();

    verify(emailService, times(1)).enviarAlerta(38.0, 65);
  }

  @Test
  void procesarAlertas_NoDebeMandarMail_CuandoElClimaEsNormal() {

    RegistroClima climaNormal = new RegistroClima(25.0, 50, LocalDateTime.now());
    when(repository.findTopByOrderByFechaHoraDesc()).thenReturn(Optional.of(climaNormal));

    alertaService.procesarAlertas();

    verify(emailService, never()).enviarAlerta(anyDouble(), anyInt());
  }
}