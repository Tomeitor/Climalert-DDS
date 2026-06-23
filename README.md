# Climalert - Sistema de Monitoreo Climático

Servicio autónomo desarrollado en Spring Boot (Java 21) para el monitoreo del clima y envío automático de alertas meteorológicas.

## Requisitos previos para ejecutar
1. En el archivo `src/main/resources/application.properties`, configurar las siguientes variables:
   - `weather.api.key`: API Key válida de WeatherAPI.
   - `spring.mail.username`: Correo electrónico de envío.
   - `spring.mail.password`: Contraseña de aplicación del correo.

## Arquitectura y Decisiones
- Se utilizó **Spring Scheduling** (`@Scheduled`) para la ejecución de tareas asíncronas (consulta de API cada 5 min y procesamiento cada 1 min).
- Se implementó una base de datos en memoria **H2** para facilitar la corrección y ejecución directa.
- Se incluyeron **Tests Unitarios** utilizando Mockito para aislar el envío de correos y validar la lógica de detección de alertas de forma segura.
