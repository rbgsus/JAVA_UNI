package fp.grados.tipos.test;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionBecarioNoValido;
import fp.grados.tipos.Beca;
import fp.grados.tipos.BecaImpl;
import fp.grados.tipos.Becario;
import fp.grados.tipos.BecarioImpl;
import fp.grados.tipos.TipoBeca;

public class TestBecario {
	public static void main(String[] args) {
		testConstructor1Normal();
		testConstructor1Excepcion();

		testConstructor2Normal();
		testConstructor2Excepcion();

		testSetFechaComienzoNormal();
		testSetFechaComienzoExcepcion();

		testSetEmailExcepcion();
	}

	private static void testConstructor1Normal() {
		System.out.println("========Probando el primer constructor======================================================================================");
		Beca beca = new BecaImpl("ABC1234", TipoBeca.ORDINARIA);
		testConstructor1("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", beca, LocalDate.of(2017, 1, 1));
	}

	private static void testConstructor1Excepcion() {
		System.out.println("\n========Probando el primer constructor, fecha de comienzo incorrecta======================================================================================");
		Beca beca = new BecaImpl("ABC1234", TipoBeca.ORDINARIA);
		testConstructor1("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", beca, LocalDate.of(2010, 1, 1));
	}

	private static void testConstructor2Normal() {
		System.out.println("\n========Probando el segundo constructor======================================================================================");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", "ABC1234", 12000.0, 12, TipoBeca.ORDINARIA, LocalDate.of(2017, 1, 1));
	}

	private static void testConstructor2Excepcion() {
		System.out.println("\n========Probando el segundo constructor, fecha de comienzo incorrecta======================================================================================");
		testConstructor2("12345678Z", "Juan", "Nadie Nadie",LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", "ABC1234", 12000.0, 12, TipoBeca.ORDINARIA, LocalDate.of(2010, 1, 1));
	}

	private static void testSetFechaComienzoNormal() {
		System.out.println("\n========Probando setFechaComienzo======================================================================================");
		Beca beca = new BecaImpl("ABC1234", TipoBeca.ORDINARIA);
		Becario b = new BecarioImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", beca, LocalDate.of(2017,1, 1));
		testsetFechaComienzo(b, LocalDate.of(2018, 1, 1));
	}

	private static void testSetFechaComienzoExcepcion() {
		System.out.println("\n========Probando setFechaComienzo, fecha incorrecta======================================================================================");
		Beca beca = new BecaImpl("ABC1234", TipoBeca.ORDINARIA);
		Becario b = new BecarioImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", beca, LocalDate.of(2017,1, 1));
		testsetFechaComienzo(b, LocalDate.of(2010, 1, 1));
	}

	private static void testSetEmailExcepcion() {
		System.out.println("\n========Probando setEmail======================================================================================");
		Beca beca = new BecaImpl("ABC1234", TipoBeca.ORDINARIA);
		Becario b = new BecarioImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es", beca, LocalDate.of(2017,1, 1));
		testSetEmail(b, "juan@alumn.us.es");
	}
	
	
	
	//////////////////////////////////////////////////
	//Métodos auxiliares
	
	private static void testConstructor1(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email, Beca beca, LocalDate fechaComienzo) {
		try {
			Becario b = new BecarioImpl(dni, nombre, apellidos,	fechaNacimiento, email, beca, fechaComienzo);
			mostrarBecario(b);
		} catch (ExcepcionBecarioNoValido e) {
			System.out.println("Se ha capturado la excepción ExcepcionBecarioNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}

	private static void testConstructor2(String dni, String nombre,	String apellidos, LocalDate fechaNacimiento, String email, String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo, LocalDate fechaComienzo) {
		try {
			Becario b = new BecarioImpl(dni, nombre, apellidos, fechaNacimiento, email, codigo, cuantiaTotal, duracion,	tipo, fechaComienzo);
			mostrarBecario(b);
		} catch (ExcepcionBecarioNoValido e) {
			System.out.println("Se ha capturado la excepción ExcepcionBecarioNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}

	private static void testsetFechaComienzo(Becario b, LocalDate fechaComienzo) {
		try {
			System.out.println("La fecha de comienzo antes de la operación es: " + b.getFechaComienzo());
			System.out.println("La nueva fecha de comienzo es: " + fechaComienzo);
			b.setFechaComienzo(fechaComienzo);
			System.out.println("La fecha de comienzo después de la operación es: " + b.getFechaComienzo());
		} catch (ExcepcionBecarioNoValido e) {
			System.out.println("Se ha capturado la excepción ExcepcionBecarioNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testSetEmail(Becario b, String email) {
		try {
			System.out.println("El email antes de la operación es: " + b.getEmail());
			System.out.println("El nuevo email es: " + email);
			b.setEmail(email);
		} catch (UnsupportedOperationException e) {
			System.out.println("Se ha capturado la excepción UnsupportedOperationException: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}

	private static void mostrarBecario(Becario b) {
		System.out.println("Becario --> <" + b + ">");
		System.out.println("\tDNI: <" + b.getDNI() + ">");
		System.out.println("\tNombre: <" + b.getNombre() + ">");
		System.out.println("\tApellidos: <" + b.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <" + b.getFechaNacimiento() + ">");
		System.out.println("\tEdad: <" + b.getEdad() + ">");
		System.out.println("\tEmail:  <" + b.getEmail() + ">");
		System.out.println("\tAsignaturas:  <" + b.getAsignaturas() + ">");
		System.out.println("\tBeca: <" + b.getBeca() + ">");
		System.out.println("\tFecha de comienzo: <" + b.getFechaComienzo() + ">");
		System.out.println("\tFecha de fin: <" + b.getFechaFin() + ">");
	}
}