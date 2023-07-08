package fp.grados.tipos.test;

import java.util.List;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.utiles.Grados;

public class TestAsignatura {

	public static void main(String[] args) {
		testConstructorString();

		testConstructorNormal();
		testConstructorExcepcion1();
		testConstructorExcepcion2();
		testConstructorExcepcion3();
		testConstructorExcepcion4();
		testConstructorExcepcion5();
		testConstructorExcepcion6();
		testConstructorExcepcion7();
		testIgualdad();
		testOrden();
	}

	// Casos de prueba
	private static void testConstructorString() {
		List<Asignatura> asignaturas = Grados.leeFichero("res/asignaturas.txt", s -> new AsignaturaImpl(s));
		for (Asignatura a : asignaturas) {
			mostrarAsignatura(a);
		}
	}

	private static void testConstructorNormal() {
		System.out.println("==================================Probando el constructor");
		testConstructor("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
	}

	private static void testConstructorExcepcion1() {
		System.out.println("==================================Probando el constructor, código de asignatura más largo");
		testConstructor("Fundamentos de Programación", "20500010", 12.0, TipoAsignatura.ANUAL, 1);
	}

	private static void testConstructorExcepcion2() {
		System.out.println("==================================Probando el constructor, código de asignatura más corto");
		testConstructor("Fundamentos de Programación", "205000", 12.0, TipoAsignatura.ANUAL, 1);
	}

	private static void testConstructorExcepcion3() {
		System.out
				.println("==================================Probando el constructor, código de asignatura no numérico");
		testConstructor("Fundamentos de Programación", "2A50001", 12.0, TipoAsignatura.ANUAL, 1);
	}

	private static void testConstructorExcepcion4() {
		System.out.println("==================================Probando el constructor, créditos incorrectos (0.0)");
		testConstructor("Fundamentos de Programación", "2050001", 0.0, TipoAsignatura.ANUAL, 1);
	}

	private static void testConstructorExcepcion5() {
		System.out.println("==================================Probando el constructor, créditos incorrectos (-1.0)");
		testConstructor("Fundamentos de Programación", "2050001", -1.0, TipoAsignatura.ANUAL, 1);
	}

	private static void testConstructorExcepcion6() {
		System.out.println("==================================Probando el constructor, curso menor de 1");
		testConstructor("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, -2);
	}

	private static void testConstructorExcepcion7() {
		System.out.println("==================================Probando el constructor, curso mayor de 4");
		testConstructor("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 5);
	}

	private static void testIgualdad() {
		System.out.println("\n===============================Probando igualdad");
		// Creamos un objeto
		Asignatura a1 = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		// Creamos un objeto igual al anterior
		Asignatura a2 = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		// Creamos un objeto distinto
		Asignatura a3 = new AsignaturaImpl("Estructura de Computadores", "2050009", 6.0,
				TipoAsignatura.SEGUNDO_CUATRIMESTRE, 1);
		System.out.println("Código hash del objeto a1 (" + a1 + "): " + a1.hashCode());
		System.out.println("Código hash del objeto a2 (" + a2 + "): " + a2.hashCode());
		System.out.println("Código hash del objeto a3 (" + a3 + "): " + a3.hashCode());
		System.out.println("¿Es a1 igual a a2? (debe ser true): " + a1.equals(a2));
		System.out.println("¿Es a1 distinto de a3? (debe ser true): " + !a1.equals(a3));
	}

	private static void testOrden() {
		System.out.println("\n===============================Probando orden natural");
		// Creamos cuatro objetos tales que menor < igual1 == igual2 < mayor
		Asignatura menor = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura igual1 = new AsignaturaImpl("Estructura de Computadores", "2050009", 6.0,
				TipoAsignatura.SEGUNDO_CUATRIMESTRE, 1);
		Asignatura igual2 = new AsignaturaImpl("Estructura de Computadores", "2050009", 6.0,
				TipoAsignatura.SEGUNDO_CUATRIMESTRE, 1);
		Asignatura mayor = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 3);
		System.out.print("(debe ser ANTES) ");
		compara(menor, igual1);
		System.out.print("(debe ser MISMA POSICIÓN) ");
		compara(igual1, igual2);
		System.out.print("(debe ser ANTES) ");
		compara(igual2, mayor);
	}

	// Métodos auxiliares

	private static void compara(Asignatura a1, Asignatura a2) {
		System.out.print("El objeto <" + a1 + ">");
		if (a1.compareTo(a2) < 0) {
			System.out.print(" va ANTES que el objeto ");
		} else if (a1.compareTo(a2) > 0) {
			System.out.print(" va DESPUÉS que el objeto ");
		} else {
			System.out.print(" va en la MISMA POSICIÓN que el objeto ");
		}
		System.out.println("<" + a2 + ">");
	}

	private static void testConstructor(String nombre, String codigo, Double creditos, TipoAsignatura tipo,
			Integer curso) {
		try {
			Asignatura a = new AsignaturaImpl(nombre, codigo, creditos, tipo, curso);
			mostrarAsignatura(a);
		} catch (ExcepcionAsignaturaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionAsignaturaNoValida");
		} catch (Exception e) {
			System.out.println("******************** ¡¡¡Se ha capturado una EXCEPCIÓN INESPERADA!!!");
		}
	}

	private static void mostrarAsignatura(Asignatura a) {
		System.out.println("Assignatura --> <" + a + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tCódigo: <" + a.getCodigo() + ">");
		System.out.println("\tCréditos: <" + a.getCreditos() + ">");
		System.out.println("\tTipo: <" + a.getTipo() + ">");
		System.out.println("\tCurso: <" + a.getCurso() + ">");
	}
}