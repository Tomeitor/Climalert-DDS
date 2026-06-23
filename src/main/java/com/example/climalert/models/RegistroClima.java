package com.example.climalert.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros_clima")
@Getter
@Setter
@NoArgsConstructor
public class RegistroClima {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double temperatura;

  private int humedad;

  private LocalDateTime fechaHora;

  public RegistroClima(double temperatura, int humedad, LocalDateTime fechaHora) {
    this.temperatura = temperatura;
    this.humedad = humedad;
    this.fechaHora = fechaHora;
  }
}