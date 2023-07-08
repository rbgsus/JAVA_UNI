package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;
import fp.grados.tipos.Tutoria;
import fp.grados.tipos.TutoriaImpl;
import fp.grados.utiles.Grados;

public class TestTutoria {

	public static void main(String[] args) {
		testConstructorString();

		testConstructor1Normal();
		testConstructor1Excepcional1();
		testConstructor1Excepcional2();
		testConstructor1Excepcional3();

		testConstructor2Normal();
		testConstructor2Excepcional1();
		testConstructor2Excepcional2();
		testConstructor2Excepcional3();

		testIgualdad();
		testOrden();
	}

	private static void testConstructorString() {
		List<Tutoria> tutorias = Grados.leeFichero("res/tutorias.txt", s -> new TutoriaImpl(s));
		for (Tutoria t : tutorias) {
			mostrarTutoria(t);
		}
	}

	private static void testConstructor2Excepcional3() {
		System.out.println(
				"==================================Probando el segundo constructor duración menor a lo permitido");
		testConstructor2(DayOfWeek.FRIDAY, LocalTime.of(12, 30), 20);

	}

	private static void testConstructor2Excepcional2() {
		System.out.println(
				"==================================Probando el segundo constructor con hora de comienzo incorrecta");
		testConstructor2(DayOfWeek.FRIDAY, LocalTime.of(5, 30), 30);

	}

	private static void testConstructor2Excepcional1() {
		System.out.println(
				"==================================Probando el segundo constructor con dia de la semana incorrecto");
		testConstructor2(DayOfWeek.SUNDAY, LocalTime.of(9, 30), 30);

	}

	private static void testConstructor1Excepcional3() {
		System.out.println(
				"==================================Probando el primer constructor con dia de la semana incorrecto");
		testConstructor1(DayOfWeek.SATURDAY, LocalTime.of(7, 30), LocalTime.of(9, 30));

	}

	private static void testConstructor1Excepcional2() {
		System.out.println(
				"==================================Probando el primer constructor con hora de fin posterior a la fijada");
		testConstructor1(DayOfWeek.FRIDAY, LocalTime.of(21, 30), LocalTime.of(23, 30));

	}

	private static void testConstructor1Excepcional1() {
		System.out.println(
				"==================================Probando el primer constructor con hora de comienzo anterior a la fijada");
		testConstructor1(DayOfWeek.FRIDAY, LocalTime.of(7, 10), LocalTime.of(9, 10));
	}

	private static void testConstructor1Normal() {
		System.out.println("==================================Probando el primer constructor");
		testConstructor1(DayOfWeek.THURSDAY, LocalTime.of(11, 40), LocalTime.of(12, 40));
	}

	private static void testConstructor2Normal() {
		System.out.println("==================================Probando el segundo constructor");
		testConstructor2(DayOfWeek.TUESDAY, LocalTime.of(12, 00), 30);
	}

	private static void testOrden() {
		System.out.println("\n============Probando orden natural");
		Tutoria menor = new TutoriaImpl(DayOfWeek.MONDAY, LocalTime.of(10, 30), LocalTime.of(11, 30));
		Tutoria igual1 = new TutoriaImpl(DayOfWeek.WEDNESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30));
		Tutoria igual2 = new TutoriaImpl(DayOfWeek.WEDNESDAY, LocalTime.of(10, 30), LocalTime.of(11, 30));
		Tutoria mayor = new TutoriaImpl(DayOfWeek.WEDNESDAY, LocalTime.of(12, 30), LocalTime.of(13, 30));
		System.out.print("(debe ser ANTES) ");
		compara(menor, igual1);
		System.out.print("(debe ser MISMA POSICIÓN) ");
		compara(igual1, igual2);
		System.out.print("(debe ser ANTES) ");
		compara(igual2, mayor);
	}

	private static void testIgualdad() {
		System.out.println("\n===========Probando igualdad con dos objetos iguales");

		Tutoria t1 = new TutoriaImpl(DayOfWeek.FRIDAY, LocalTime.of(10, 30), LocalTime.of(11, 30));
		Tutoria t2 = new TutoriaImpl(DayOfWeek.FRIDAY, LocalTime.of(10, 30), LocalTime.of(11, 30));

		Tutoria t3 = new TutoriaImpl(DayOfWeek.WEDNESDAY, LocalTime.of(11, 30), LocalTime.of(11, 30));
		Tutoria t4 = new TutoriaImpl(DayOfWeek.THURSDAY, LocalTime.of(15, 30), LocalTime.of(16, 30));
		Tutoria t5 = new TutoriaImpl(DayOfWeek.TUESDAY, LocalTime.of(18, 30), LocalTime.of(18, 30));

		System.out.println("Código hash del objeto t1 (" + t1 + "): " + t1.hashCode());
		System.out.println("Código hash del objeto t2 (" + t2 + "): " + t2.hashCode());
		System.out.println("Código hash del objeto t3 (" + t3 + "): " + t3.hashCode());
		System.out.println("Código hash del objeto t4 (" + t4 + "): " + t4.hashCode());
		System.out.println("Código hash del objeto t5 (" + t5 + "): " + t5.hashCode());
		System.out.println("¿Es t1 igual a t2? (debe ser true): " + t1.equals(t2));
		System.out.println("¿Es t1 distinto de t3? (debe ser true): " + !t1.equals(t3));
		System.out.println("¿Es t1 distinto de t4? (debe ser true): " + !t1.equals(t4));
		System.out.println("¿Es t1 distinto de t5? (debe ser true): " + !t1.equals(t5));
	}

	// Métodos auxiliares
	private static void compara(Tutoria t1, Tutoria t2) {
		System.out.print("El objeto <" + t1 + ">");
		if (t1.compareTo(t2) < 0) {
			System.out.print(" va ANTES que el objeto ");
		} else if (t1.compareTo(t2) > 0) {
			System.out.print(" va DESPUÉS que el objeto ");
		} else {
			System.out.print(" va en la MISMA POSICIÓN que el objeto ");
		}
		System.out.println("<" + t2 + ">");
	}

	private static void testConstructor1(DayOfWeek dia, LocalTime horaComienzo, LocalTime horaFin) {

		try {
			Tutoria t = new TutoriaImpl(dia, horaComienzo, horaFin);
			mostrarTutoria(t);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionPersonaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
	}

	private static void testConstructor2(DayOfWeek dia, LocalTime horaComienzo, Integer duracion) {
		try {
			Tutoria t = new TutoriaImpl(dia, horaComienzo, duracion);
			mostrarTutoria(t);
		} catch (ExcepcionTutoriaNoValida e) {
			System.out.println("******************** Se ha capturado la excepción ExcepcionTutoriaNoValida");
		} catch (Exception e) {
			System.out.println(
					"******************** Se ha capturado una excepción inesperada. El constructor no funciona correctamente");
		}
	}

	private static void mostrarTutoria(Tutoria t) {
		System.out.println("Tutoria --> <" + t + ">");
		System.out.println("\tDia de la Semana: <" + t.getDiaSemana() + ">");
		System.out.println("\tHora de Comienzo: <" + t.getHoraComienzo() + ">");
		System.out.println("\tHora de Fin: <" + t.getHoraFin() + ">");
		System.out.println("\tDuración:  <" + t.getDuracion() + ">");
	}
}