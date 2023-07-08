package fp.grados.tipos.test;

import java.util.List;

import fp.grados.excepciones.ExcepcionNotaNoValida;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Nota;
import fp.grados.tipos.NotaImpl;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.utiles.Grados;
import fp.grados.tipos.Convocatoria;

public class TestNota {

	public static void main(String[] args) {
		testConstructorString();

		testConstructor1Normal();
		testConstructor1Excepcion1();
		testConstructor1Excepcion2();
		testConstructor2Normal();
		testConstructor2Excepcion1();

		testIgualdad();
		testOrden();
	}

	private static void testConstructorString() {
		List<Nota> notas = Grados.leeFichero("res/notas.txt", s -> new NotaImpl(s));
		for (Nota n : notas) {
			mostrarNota(n);
		}
	}

	private static void testConstructor2Excepcion1() {
		System.out.println(
				"==================================Probando el segundo constructor con nota mayor de lo permitido");
		testConstructor2(new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1),
				2015, Convocatoria.PRIMERA, 11.0);

	}

	private static void testConstructor1Excepcion2() {
		System.out.println(
				"==================================Probando el primer constructor con nota mayor de lo permitido");
		testConstructor1(new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1),
				2015, Convocatoria.PRIMERA, 11.0, false);
	}

	private static void testConstructor1Excepcion1() {
		System.out.println(
				"==================================Probando el primer constructor, Matrícula de Honor incorrecto");
		testConstructor1(new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1),
				2015, Convocatoria.PRIMERA, 8.0, true);
	}

	private static void testConstructor1Normal() {
		System.out.println("==================================Probando el primer constructor");
		testConstructor1(new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1),
				2015, Convocatoria.PRIMERA, 9.1, true);
	}

	private static void testConstructor2Normal() {
		System.out.println("==================================Probando el segundo constructor");
		testConstructor2(new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1),
				2000, Convocatoria.PRIMERA, 8.0);
	}

	private static void testOrden() {
		System.out.println("\n============Probando orden natural");
		Nota menor = new NotaImpl(
				new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1), 2014,
				Convocatoria.PRIMERA, 8.0);
		Nota igual1 = new NotaImpl(
				new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1), 2015,
				Convocatoria.PRIMERA, 8.0);
		Nota igual2 = new NotaImpl(
				new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1), 2015,
				Convocatoria.PRIMERA, 8.0);
		Nota mayor = new NotaImpl(
				new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1), 2016,
				Convocatoria.PRIMERA, 8.0);
		System.out.print("(debe ser ANTES) ");
		compara(menor, igual1);
		System.out.print("(debe ser MISMA POSICIÓN) ");
		compara(igual1, igual2);
		System.out.print("(debe ser ANTES) ");
		compara(igual2, mayor);
	}

	private static void testIgualdad() {
		System.out.println("\n===========Probando igualdad con dos objetos iguales");
		Nota n1 = new NotaImpl(
				new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1), 2015,
				Convocatoria.PRIMERA, 8.0);
		Nota n2 = new NotaImpl(
				new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1), 2015,
				Convocatoria.PRIMERA, 8.0);
		Nota n3 = new NotaImpl(
				new AsignaturaImpl("Fundamentos de Programación", "0000217", 12.0, TipoAsignatura.ANUAL, 1), 2016,
				Convocatoria.PRIMERA, 8.0);
		Nota n4 = new NotaImpl(new AsignaturaImpl("Cálculo", "0000217", 12.0, TipoAsignatura.ANUAL, 1), 2017,
				Convocatoria.SEGUNDA, 4.0);
		Nota n5 = new NotaImpl(new AsignaturaImpl("Circuitos", "0000217", 12.0, TipoAsignatura.ANUAL, 1), 2018,
				Convocatoria.TERCERA, 9.0);

		System.out.println("Código hash del objeto n1 (" + n1 + "): " + n1.hashCode());
		System.out.println("Código hash del objeto n2 (" + n2 + "): " + n2.hashCode());
		System.out.println("Código hash del objeto n3 (" + n3 + "): " + n3.hashCode());
		System.out.println("Código hash del objeto n4 (" + n4 + "): " + n4.hashCode());
		System.out.println("Código hash del objeto n5 (" + n5 + "): " + n5.hashCode());
		System.out.println("¿Es n1 igual a n2? (debe ser true): " + n1.equals(n2));
		System.out.println("¿Es n1 distinto de n3? (debe ser true): " + !n1.equals(n3));
		System.out.println("¿Es n1 distinto de n4? (debe ser true): " + !n1.equals(n4));
		System.out.println("¿Es n1 distinto de n5? (debe ser true): " + !n1.equals(n5));
	}

	// Métodos auxiliares
	private static void compara(Nota n1, Nota n2) {
		System.out.print("El objeto <" + n1 + ">");
		if (n1.compareTo(n2) < 0) {
			System.out.print(" va ANTES que el objeto ");
		} else if (n1.compareTo(n2) > 0) {
			System.out.print(" va DESPUÉS que el objeto ");
		} else {
			System.out.print(" va en la MISMA POSICIÓN que el objeto ");
		}
		System.out.println("<" + n2 + ">");
	}

	private static void testConstructor1(Asignatura asignatura, Integer cursoacademico, Convocatoria convocatoria,
			Double valor, Boolean mencionhonor) {
		try {
			Nota n = new NotaImpl(asignatura, cursoacademico, convocatoria, valor, mencionhonor);
			mostrarNota(n);
		} catch (ExcepcionNotaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepcón inesperada. El constructor no funciona correctamente");
		}

	}

	private static void testConstructor2(Asignatura asignatura, Integer cursoacademico, Convocatoria convocatoria,
			Double valor) {
		try {
			Nota n = new NotaImpl(asignatura, cursoacademico, convocatoria, valor);
			mostrarNota(n);
		} catch (ExcepcionNotaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionNotaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}

	}

	private static void mostrarNota(Nota n) {
		System.out.println("Nota --> <" + n + ">");
		System.out.println("\tAsignatura: <" + n.getAsignatura() + ">");
		System.out.println("\tCalificación: <" + n.getCalificacion() + ">");
		System.out.println("\tConvocatoria: <" + n.getConvocatoria() + ">");
		System.out.println("\tCurso Académico:  <" + n.getCursoAcademico() + ">");
		System.out.println("\tValor:  <" + n.getValor() + ">");
		System.out.println("\tMención de Honor:  <" + n.getMencionHonor() + ">");
	}

}