package dev.bertin.coders_club.repositorio;

import dev.bertin.coders_club.modelo.Socio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocioRepositorio extends JpaRepository<Socio,Integer> {
}
