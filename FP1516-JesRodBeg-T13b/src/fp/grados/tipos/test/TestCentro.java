package fp.grados.tipos.test;

import java.time.LocalDate;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoEspacio;

public class TestCentro {
	public static void main(String[] args) {
		testConstructorNormal();
		testConstructorExcepcional1();
		testConstructorExcepcional2();
			
		testNuevoEspacioNormal();
		testNuevoEspacioExcepcion();
		
		testEliminaEspacio();
		
		testGetEspacioMayorCapacidadNormal();
		testGetEspacioMayorCapacidadExcepcion();
		
		testGetDespachos();
		
		testGetDespachosDepartamento();
		
		testGetProfesores();
		
		testGetProfesoresDepartamento();
		
		testGetConteosEspacios();
		
		testIgualdad();
		testOrden();
	}

	private static void testConstructorNormal() {
		System.out.println("========Probando el constructor======================================================================================");
		testConstructor("Escuela Técnica Superior de Ingeniería Informática", "Av. de Reina Mercedes s/n", 3, 1);
	}
	
	private static void testConstructorExcepcional1() {
		System.out.println("\n========Probando el constructor con un número de plantas menor a 1======================================================================================");
		testConstructor("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 0, 1);
	}
	
	private static void testConstructorExcepcional2() {
		System.out.println("\n========Probando el constructor con un número de sotanos negativo======================================================================================");
		testConstructor("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 2, -1);
	}
	
	private static void testNuevoEspacioNormal(){
		System.out.println("\n========Probando nuevoEspacio======================================================================================");
		Espacio es = new EspacioImpl(TipoEspacio.LABORATORIO, "A3.10", 30, -2);
		Centro c = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 5, 2);
		testNuevoEspacio(c, es);
	}
	
	private static void testNuevoEspacioExcepcion(){
		System.out.println("\n========Probando nuevoEspacio con un número incorrecto de pisos/sotanos respecto a las plantas======================================================================================");
		Espacio es = new EspacioImpl(TipoEspacio.LABORATORIO, "A3.10", 30, -2);
		Centro c = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 1, 1);
		testNuevoEspacio(c, es);
	}
	
	private static void testEliminaEspacio(){
		System.out.println("\n========Probando eliminaEspacio======================================================================================");
		Espacio es = new EspacioImpl(TipoEspacio.LABORATORIO, "A3.10", 30, 0);
		Centro c = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 1, 0);
		c.nuevoEspacio(es);
		testEliminaEspacio(c, es);
	}
	
	private static void testGetEspacioMayorCapacidadNormal(){
		System.out.println("\n========Probando getEspacioMayorCapacidad======================================================================================");
		Espacio es1 = new EspacioImpl(TipoEspacio.LABORATORIO, "A3.10", 30, 0);
		Espacio es2 = new EspacioImpl(TipoEspacio.LABORATORIO, "B4.10", 16, 1);
		Espacio es3 = new EspacioImpl(TipoEspacio.LABORATORIO, "C5.10", 45, 0);
		Centro c = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 2, 0);
		c.nuevoEspacio(es1);
		c.nuevoEspacio(es2);
		c.nuevoEspacio(es3);
		testGetEspacioMayorCapacidad(c);
	}
	
	private static void testGetEspacioMayorCapacidadExcepcion(){
		System.out.println("\n========Probando getEspacioMayorCapacidad sin espacios======================================================================================");
		Centro c = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 2, 0);
		testGetEspacioMayorCapacidad(c);
	}
	
	private static void testGetDespachos() {
		System.out.println("\n========Probando getDespachos======================================================================================");
		Centro etsii = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 4, 1);
		Despacho d1 = new DespachoImpl("F0.20", 1, 0);
		Despacho d2 = new DespachoImpl("F0.21", 1, 0);
		Despacho d3 = new DespachoImpl("F0.22", 1, 0);
		etsii.nuevoEspacio(d1);
		etsii.nuevoEspacio(d2);
		etsii.nuevoEspacio(d3);
		testGetDespachos(etsii);
	}
	
	private static void testGetDespachosDepartamento() {
		System.out.println("\n========Probando getDespachos(Departamento)======================================================================================");
		Centro etsii = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 4, 1);
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor p2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),	"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Profesor p3 = new ProfesorImpl("12345678Z", "Luisa", "Nadie Nadie", LocalDate.of(1950, 3, 15), "luisa.nadie@us.es", Categoria.AYUDANTE);
		Despacho d1 = new DespachoImpl("F0.20", 1, 0, p1);
		Despacho d2 = new DespachoImpl("F0.21", 2, 0, p2);
		Despacho d3 = new DespachoImpl("F0.22", 1, 0, p3);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl("Ciencias de la Computación e Inteligencia Artificial");
		lsi.nuevoProfesor(p1);
		lsi.nuevoProfesor(p2);
		ccia.nuevoProfesor(p3);
		etsii.nuevoEspacio(d1);
		etsii.nuevoEspacio(d2);
		etsii.nuevoEspacio(d3);
		testGetDespachosDepartamento(etsii, lsi);
	}
	
	private static void testGetProfesores() {
		System.out.println("\n========Probando getProfesores======================================================================================");
		Centro etsii = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 4, 1);
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor p2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),	"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Despacho d1 = new DespachoImpl("F0.20", 1, 0, p1);
		Despacho d2 = new DespachoImpl("F0.21", 2, 0, p2);
		etsii.nuevoEspacio(d1);
		etsii.nuevoEspacio(d2);
		testGetProfesores(etsii);
	}
	
	private static void testGetProfesoresDepartamento() {
		System.out.println("\n========Probando getProfesores(Departamento)======================================================================================");
		Centro etsii = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 4, 1);
		Profesor p1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor p2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),	"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Despacho d1 = new DespachoImpl("F0.20", 1, 0, p1);
		Despacho d2 = new DespachoImpl("F0.21", 2, 0, p2);
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		lsi.nuevoProfesor(p1);
		lsi.nuevoProfesor(p2);
		etsii.nuevoEspacio(d1);
		etsii.nuevoEspacio(d2);
		testGetProfesoresDepartamento(etsii, lsi);
	}
	
	private static void testGetConteosEspacios() {
		System.out.println("\n========Probando getConteosEspacios======================================================================================");
		Centro etsii = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 4, 1);
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.TEORIA, "A0.10", 50, 0));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.TEORIA, "A1.10", 50, 1));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.TEORIA, "A2.10", 50, 2));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.EXAMEN, "A3.10", 150, 3));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.EXAMEN, "A3.11", 150, 3));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.LABORATORIO, "F1.30", 30, 1));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.LABORATORIO, "F1.31", 30, 1));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.LABORATORIO, "F1.32", 30, 1));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.LABORATORIO, "F1.33", 30, 1));
		etsii.nuevoEspacio(new EspacioImpl(TipoEspacio.SEMINARIO, "Salón de Grados", 60, 1));
		etsii.nuevoEspacio(new DespachoImpl("F0.20", 1, 0));
		etsii.nuevoEspacio(new DespachoImpl("F0.21", 3, 0));
		etsii.nuevoEspacio(new DespachoImpl("F0.22", 2, 0));
		mostrarEspaciosCentro(etsii);
		testGetConteosEspacios(etsii);
	}
	
	private static void testIgualdad() {
		System.out.println("\n========Probando igualdad con dos objetos iguales======================================================================================");
		Centro c1 = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 5, 2);
		Centro c2 = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 5, 2);
		Centro c3 = new CentroImpl("Facultad de biología", "Av. Reina Mercedes s/n, Sevilla", 5, 2);
		Centro c4 = new CentroImpl("Facultad de física", "Av. Reina Mercedes s/n, Sevilla", 5, 2);
		System.out.println("Código hash del objeto c1 (" + c1 + "): " + c1.hashCode());
		System.out.println("Código hash del objeto c2 (" + c2 + "): " + c2.hashCode());
		System.out.println("Código hash del objeto c3 (" + c3 + "): " + c3.hashCode());
		System.out.println("Código hash del objeto c4 (" + c4 + "): " + c4.hashCode());
		System.out.println("¿Es c1 igual a c2? (debe ser true): " + c1.equals(c2));
		System.out.println("¿Es c1 distinto de c3? (debe ser true): " + !c1.equals(c3));
		System.out.println("¿Es c1 distinto de c4? (debe ser true): " + !c1.equals(c4));
	}

	private static void testOrden() {
		System.out.println("\n========Probando orden natural======================================================================================");
		Centro menor = new CentroImpl("Escuela Técnica Superior de Ingeniería Informática", "Av. Reina Mercedes s/n, Sevilla", 5, 2);
		Centro igual1 = new CentroImpl("Facultad de biología", "Av. Reina Mercedes s/n, Sevilla", 5, 2);
		Centro igual2 = new CentroImpl("Facultad de biología", "Av. Reina Mercedes s/n, Sevilla", 5, 2);
		Centro mayor = new CentroImpl("Facultad de física", "Av. Reina Mercedes s/n, Sevilla", 5, 2);
		System.out.println("- Debe ser ANTES: ");
		compara(menor, igual1);
		System.out.println("- Debe ser MISMA POSICIÓN: ");
		compara(igual1, igual2);
		System.out.println("- Debe ser DESPUÉS: ");
		compara(mayor, igual2);
	}
	
	
	
	//////////////////////////////////////////////////
	//Métodos auxiliares
	
	private static void testConstructor(String nombre, String direccion, Integer numeroPlantas, Integer numeroSotanos) {
		try {
			Centro c = new CentroImpl(nombre, direccion, numeroPlantas, numeroSotanos);
			mostrarCentro(c);
		} catch (ExcepcionCentroNoValido e) {
			System.out.println("Se ha capturado la excepción ExcepcionCentroNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testNuevoEspacio(Centro c, Espacio es) {
		try {
			System.out.println("Los espacios del centro antes de la operación son: " + c.getEspacios());
			System.out.println("Nuevo espacio a añadir: " + es);
			c.nuevoEspacio(es);
			System.out.println("Los espacios del centro después de la operación son: " + c.getEspacios());
		} catch (ExcepcionCentroOperacionNoPermitida e) {
			System.out.println("Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testEliminaEspacio(Centro c, Espacio es) {
		System.out.println("Los espacios del centro antes de la operación son: " + c.getEspacios());
		System.out.println("Espacio a eliminar: " + es);
		c.eliminaEspacio(es);
		System.out.println("Los espacios del centro después de la operación son: " + c.getEspacios());
	}
	
	private static void testGetEspacioMayorCapacidad(Centro c){
		try{
			System.out.println("Espacios del centro " + c + " con su capacidad:");
			for (Espacio es : c.getEspacios()) {
				System.out.println(es + " <" + es.getCapacidad() + ">");
			}
			System.out.println("El espacio del centro " + c + " con mayor capacidad es: " + c.getEspacioMayorCapacidad());
		} catch (ExcepcionCentroOperacionNoPermitida e) {
			System.out.println("Se ha capturado la excepción ExcepcionCentroOperacionNoPermitida: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}
	
	private static void testGetDespachos(Centro c) {
		System.out.println("Despachos del centro " + c + ":");
		for (Despacho desp : c.getDespachos()) {
			System.out.println(desp);
		}
	}
	
	private static void testGetDespachosDepartamento(Centro c, Departamento dep) {
		System.out.println("Despachos del centro " + c + " con profesores del departamento " + dep);
		for (Despacho desp : c.getDespachos(dep)) {
			System.out.println(desp);
		}
	}
	
	private static void testGetProfesores(Centro c) {
		System.out.println("Profesores con despacho en el centro " + c + ":");
		for (Profesor pr : c.getProfesores()) {
			System.out.println(pr);
		}
	}
	
	private static void testGetProfesoresDepartamento(Centro c, Departamento dep) {
		System.out.println("Profesores con despacho en el centro " + c + " que pertenecen al departamento " + dep);
		for (Profesor pr : c.getProfesores()) {
			System.out.println(pr);
		}
	}
	
	private static void testGetConteosEspacios(Centro c) {
		System.out.println("Conteo de tipos de espacios:");
		Integer[] conteos = c.getConteosEspacios();
		for (int i = 0; i < conteos.length; i++) {
			System.out.println("Espacios de tipo " + TipoEspacio.values()[i] + ": " + conteos[i]);
		}
	}

	private static void compara(Centro c1, Centro c2) {
		System.out.print("El objeto <" + c1 + ">");
		if (c1.compareTo(c2) < 0) {
			System.out.print(" va ANTES que el objeto ");
		} else if (c1.compareTo(c2) > 0) {
			System.out.print(" va DESPUÉS que el objeto ");
		} else {
			System.out.print(" va en la MISMA POSICIÓN que el objeto ");
		}
		System.out.println("<" + c2 + ">");
	}
	
	private static void mostrarEspaciosCentro(Centro c) {
		System.out.println("Espacios del centro " + c + ":");
		for (Espacio es : c.getEspacios()) {
			System.out.println(es.getTipo() + ": " + es);
		}
	}

	private static void mostrarCentro(Centro c) {
		System.out.println("Centro--> <" + c + ">");
		System.out.println("Nombre: <" + c.getNombre() + ">");
		System.out.println("Dirección: <" + c.getDireccion() + ">");
		System.out.println("Número de plantas: <" + c.getNumeroPlantas() + ">");
		System.out.println("Número de sotanos: <" + c.getNumeroSotanos() + ">");
		System.out.println("Espacios: <" + c.getEspacios() + ">");
	}
}