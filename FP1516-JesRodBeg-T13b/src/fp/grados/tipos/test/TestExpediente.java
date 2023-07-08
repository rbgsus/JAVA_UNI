package fp.grados.tipos.test;

import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.Expediente;
import fp.grados.tipos.ExpedienteImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestExpediente {
	public static void main(String[] args) {
		testConstructor();
		
		testGetNotaMedia1();
		testGetNotaMedia2();
		
		testNuevaNota1Normal();
		testNuevaNota1Excepcion();
		
		testNuevaNota2Normal();
		testNuevaNota2Excepcion();

		
		testIgualdad();
	}

	private static void testConstructor() {
		System.out.println("========Probando el constructor======================================================================================");
		Expediente ex = new ExpedienteImpl();
		mostrarExpediente(ex);
	}
	
	private static void testGetNotaMedia1(){
		System.out.println("\n========Probando el método getNotaMedia para un expedientes con notas mayores o iguales a 5======================================================================================");
		Expediente ex = new ExpedienteImpl();
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.ANUAL, 2);
		Nota n1 = new NotaImpl(fp, 2015, Convocatoria.PRIMERA, 9.0, true);
		Nota n2 = new NotaImpl(adda, 2015, Convocatoria.PRIMERA, 5.0, false);
		testNuevaNota(n1, ex);
		testNuevaNota(n2, ex);
		testGetNotaMedia(ex);
	}
	
	private static void testGetNotaMedia2(){
		System.out.println("\n========Probando el método getNotaMedia para un expedientes sin notas mayores o iguales a 5======================================================================================");
		Expediente ex = new ExpedienteImpl();
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.ANUAL, 2);
		Nota n1 = new NotaImpl(fp, 2015, Convocatoria.PRIMERA, 3.0, false);
		Nota n2 = new NotaImpl(adda, 2015, Convocatoria.PRIMERA, 4.0, false);
		testNuevaNota(n1, ex);
		testNuevaNota(n2, ex);
		testGetNotaMedia(ex);
	}
	
	private static void testNuevaNota1Normal(){
		System.out.println("\n========Probando nuevaNota sin ninguna nota creada======================================================================================");
		Expediente ex = new ExpedienteImpl();
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "0000230", 10.0, TipoAsignatura.ANUAL, 1);
		Nota n = new NotaImpl(asig, 2015, Convocatoria.PRIMERA, 9.5, true);
		testNuevaNota(n, ex);
	}
	
	private static void testNuevaNota1Excepcion(){
		System.out.println("\n========Probando nuevaNota sin ninguna nota creada con un expediente que contiene notas de dos convocatorias de una misma asignatura y curso======================================================================================");
		Expediente ex = new ExpedienteImpl();
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "0000230", 10.0, TipoAsignatura.ANUAL, 1);
		Nota n1 = new NotaImpl(asig, 2015, Convocatoria.PRIMERA, 7.0, false);
		Nota n2 = new NotaImpl(asig, 2015, Convocatoria.SEGUNDA, 9.0, true);
		Nota n3 = new NotaImpl(asig, 2015, Convocatoria.TERCERA, 5.0, false);
		testNuevaNota(n1, ex);
		testNuevaNota(n2, ex);
		testNuevaNota(n3, ex);
	}
	
	private static void testNuevaNota2Normal(){
		System.out.println("\n========Probando nuevaNota con una nota ya creada======================================================================================");
		Expediente ex = new ExpedienteImpl();
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "0000230", 10.0, TipoAsignatura.ANUAL, 1);
		Nota n1 = new NotaImpl(asig, 2015, Convocatoria.PRIMERA, 9.5, true);
		Nota n2 = new NotaImpl(asig, 2015, Convocatoria.PRIMERA, 4.5, false);
		ex.nuevaNota(n1);
		testNuevaNota(n2, ex);
	}
	
	private static void testNuevaNota2Excepcion(){
		System.out.println("\n========Probando nuevaNota con una nota ya creada con un expediente que contiene notas de dos convocatorias de una misma asignatura y curso======================================================================================");
		Expediente ex = new ExpedienteImpl();
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "0000230", 10.0, TipoAsignatura.ANUAL, 1);
		Nota n1 = new NotaImpl(asig, 2015, Convocatoria.PRIMERA, 9.5, true);
		Nota n2 = new NotaImpl(asig, 2015, Convocatoria.PRIMERA, 7.0, false);
		Nota n3 = new NotaImpl(asig, 2015, Convocatoria.SEGUNDA, 9.0, true);
		Nota n4 = new NotaImpl(asig, 2015, Convocatoria.TERCERA, 5.0, false);
		ex.nuevaNota(n1);
		testNuevaNota(n2, ex);
		testNuevaNota(n3, ex);
		testNuevaNota(n4, ex);
	}
	
	private static void testIgualdad() {
		System.out.println("\n========Probando igualdad con dos objetos iguales======================================================================================");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "0000230", 10.0, TipoAsignatura.ANUAL, 1);
		Nota n1 = new NotaImpl(asig, 2015, Convocatoria.PRIMERA, 9.5, true);
		Nota n2 = new NotaImpl(asig, 2015, Convocatoria.PRIMERA, 9.5, true);
		Nota n3 = new NotaImpl(asig, 2015, Convocatoria.TERCERA, 4.5, false);
		Nota n4 = new NotaImpl(asig, 2015, Convocatoria.SEGUNDA, 8.5, false);
		Expediente ex1 = new ExpedienteImpl();
		Expediente ex2 = new ExpedienteImpl();
		Expediente ex3 = new ExpedienteImpl();
		Expediente ex4 = new ExpedienteImpl();
		ex1.nuevaNota(n1);
		ex2.nuevaNota(n2);
		ex3.nuevaNota(n3);
		ex4.nuevaNota(n4);
		System.out.println("Código hash del objeto ex1 (" + ex1 + "): " + ex1.hashCode());
		System.out.println("Código hash del objeto ex2 (" + ex2 + "): " + ex2.hashCode());
		System.out.println("Código hash del objeto ex3 (" + ex3 + "): " + ex3.hashCode());
		System.out.println("Código hash del objeto ex4 (" + ex4 + "): " + ex4.hashCode());
		System.out.println("¿Es ex1 igual a ex2? (debe ser true): " + ex1.equals(ex2));
		System.out.println("¿Es ex1 distinto de ex3? (debe ser true): " + !ex1.equals(ex3));
		System.out.println("¿Es ex1 distinto de ex4? (debe ser true): " + !ex1.equals(ex4));
	}


	
	//////////////////////////////////////////////////
	//Métodos auxiliares
	
	private static void testGetNotaMedia(Expediente ex){
		System.out.println("La nota media del expediente es: " + ex.getNotaMedia());
	}
	
	private static void testNuevaNota(Nota n, Expediente ex) {
		try{
			System.out.println("El expediente antes de la operación es: " + ex.getNotas());
			System.out.println("Nueva nota a añadir: " + n);
			ex.nuevaNota(n);
			System.out.println("El expediente después de la operación es: " + ex.getNotas());
		} catch (ExcepcionExpedienteOperacionNoPermitida e) {
			System.out.println("Se ha capturado la excepción ExcepcionExpedienteOperacionNoPermitida: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}

	private static void mostrarExpediente(Expediente ex) {
		System.out.println("Expediente--> <" + ex + ">");
		System.out.println("Notas: <" + ex.getNotas() + ">");
	}
}