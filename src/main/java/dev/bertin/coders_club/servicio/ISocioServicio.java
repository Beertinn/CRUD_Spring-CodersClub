package dev.bertin.coders_club.servicio;

import dev.bertin.coders_club.modelo.Socio;

import java.util.List;

public interface ISocioServicio {
    public List<Socio> listarSocios();
    public Socio buscarSocioPorId(Integer idSocio);
    public void guardarSocio(Socio socio);
    public void eliminarSocio(Socio socio);

}
