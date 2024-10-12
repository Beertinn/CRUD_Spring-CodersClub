package dev.bertin.coders_club;

import dev.bertin.coders_club.modelo.Socio;
import dev.bertin.coders_club.servicio.ISocioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CodersClubApplication  implements CommandLineRunner {

	@Autowired
	private ISocioServicio socioServicio;

	private static final Logger logger = LoggerFactory.getLogger(CodersClubApplication.class);
	//Salto de linea para el logger
	String nl = System.lineSeparator();


	public static void main(String[] args) {
		logger.info("*** Iniciando la aplicacion ***");
		SpringApplication.run(CodersClubApplication.class, args);
		logger.info("*** Aplicacion finalizada ***");
	}


	@Override
	public void run(String... args) throws Exception {
		menu();
	}

	public void menu(){
		boolean salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			try{
				int opcion = mostrarMenu(consola);
				salir = opcionSeleccionada(opcion,consola);
			} catch (Exception e) {
				logger.error("Ocurrio un error: "+e.getMessage());
			}finally {
				logger.info(nl);
			}
		}
	}

	public  void mostrarSocios(){
		logger.info("---Lista de Socios---");
		List<Socio> lista = new ArrayList<>();
		lista = socioServicio.listarSocios();
		for(Socio socioLista : lista){
			logger.info(socioLista.toString());
		}
	}

	public  int mostrarMenu(Scanner consola){
		logger.info("""
				***** Aplicacion Coders Club *****
				1) Mostrar lista de socios.
				2) Buscar socio por ID
				3) Agregar Socio.
				4) Modificar Socio.
				5) Eliminar Socio.
				6) Salir.
				Elige una opcion: \s""");
		return Integer.parseInt(consola.nextLine());
	}

	public  boolean opcionSeleccionada(int opcion, Scanner consola){
		var salida = false;
		switch(opcion){
			case 1 ->mostrarSocios();
			case 2 ->buscarSocioId(consola);
			case 3 ->agregarSocio(consola);
			case 4 ->modificarSocio(consola);
			case 5 ->eliminarSocio(consola);
			case 6 ->{
				logger.info("*** Saliendo *** . . .");
				salida = true;
			}
			default -> logger.info("Opcion no reconocida");
		}
		return salida;

	}

	public void buscarSocioId(Scanner consola){
		logger.info("Ingresa el ID que quieres buscar: ");
		Integer id = Integer.parseInt(consola.nextLine());
		Socio socio = socioServicio.buscarSocioPorId(id);
		if(socio != null){
			logger.info("Socio encontrado: "+nl+socio);
		}else{
			logger.info("Socio no encontrado");
		}

	}

	public void agregarSocio(Scanner consola){
		logger.info("+++Nuevo Socio+++");
		logger.info("Nombre: ");
		String nombre = consola.nextLine();
		logger.info("Apellido: ");
		String apellido = consola.nextLine();
		logger.info("No. de membresia: ");
		Integer membresia = Integer.parseInt(consola.nextLine());
		Socio socio = new Socio();
		socio.setNombre(nombre);
		socio.setApellido(apellido);
		socio.setMembresia(membresia);
		socioServicio.guardarSocio(socio);
		logger.info("Socio agregado: "+socio+nl);

	}

	public void modificarSocio(Scanner consola){
		logger.info("Ingresa el id del socio a modificar: ");
		int id = Integer.parseInt(consola.nextLine());
		Socio socio = socioServicio.buscarSocioPorId(id);
		if(socio != null) {
			logger.info("Nombre nuevo: ");
			String nombre = consola.nextLine();
			logger.info("Apellido nuevo: ");
			String apellido = consola.nextLine();
			logger.info("No. de membresia nuevo: ");
			Integer membresia = Integer.parseInt(consola.nextLine());
			Socio socioUpdate = new Socio(id, nombre, apellido, membresia);
			socioServicio.guardarSocio(socioUpdate);
			logger.info("Socio modificado :" + socioUpdate + nl);

		}else{
			logger.info("Socio no encontrado.");
		}

	}

	public  void eliminarSocio(Scanner consola){
		logger.info("Ingresa el ID que quieres eliminar: ");
		Integer id = Integer.parseInt(consola.nextLine());
		Socio socio = socioServicio.buscarSocioPorId(id);
		if (socio != null) {
			socioServicio.eliminarSocio(socio);
			logger.info("Socio eliminado");
		}else {
			logger.info("Socio no encontrado.");
		}
	}
}
