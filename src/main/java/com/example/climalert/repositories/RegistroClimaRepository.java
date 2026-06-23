package com.example.climalert.repositories;

import com.example.climalert.models.RegistroClima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistroClimaRepository extends JpaRepository<RegistroClima, Long> {

  Optional<RegistroClima> findTopByOrderByFechaHoraDesc();
}