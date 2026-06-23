package com.example.climalert;

import com.example.climalert.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClimalertApplicationTests {

  @Autowired
  private EmailService emailService;

  @Test
  void probarQueElMailLlegaDeVerdad() {
    System.out.println("Iniciando prueba de envío real...");

    emailService.enviarAlerta(99.9, 99);

    System.out.println("Petición de envío terminada. Revisá tu mail");
  }

  @Test
  void contextLoads() {
  }

}
