package com.example.climalert.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherResponseDTO(CurrentData current) {

  public record CurrentData(
      @JsonProperty("temp_c") double temperaturaC,
      int humidity
  ) {}

}