package dev.bertin.coders_club.servicio;

import dev.bertin.coders_club.modelo.Socio;
import dev.bertin.coders_club.repositorio.SocioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocioServicio implements ISocioServicio{

    @Autowired
    private SocioRepositorio socioRepositorio;

    @Override
    public List<Socio> listarSocios() {
        List<Socio> socios = socioRepositorio.findAll();
        return socios;
    }

    @Override
    public Socio buscarSocioPorId(Integer idSocio) {
        Socio socio = socioRepositorio.findById(idSocio).orElse(null);
        return socio;

    }

    @Override
    public void guardarSocio(Socio socio) {
        socioRepositorio.save(socio);
    }

    @Override
    public void eliminarSocio(Socio socio) {
        socioRepositorio.delete(socio);

    }
}
