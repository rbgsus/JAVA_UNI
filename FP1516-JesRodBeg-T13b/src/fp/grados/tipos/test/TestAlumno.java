package fp.grados.tipos.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;
import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Convocatoria;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.utiles.Grados;

public class TestAlumno {
	public static void main(String[] args) {
		testConstructorString();

		testConstructorNormal();
		testConstructorExcepcion();

		testSetEmailNormal();
		testSetEmailExcepcion();

		testGetCurso1();
		testGetCurso2();

		testMatriculaAsignaturaNormal();
		testMatriculaAsignaturaExcepcion();

		testEliminaAsignaturaNormal();
		testEliminaAsignaturaExcepcion();

		testEvaluaAlumno1Normal();
		testEvaluaAlumno1Excepcion();

		testEvaluaAlumno2Normal();
		testEvaluaAlumno2Excepcion();
	}

	private static void testConstructorString() {
		List<Alumno> alumnos = Grados.leeFichero("res/alumnos.txt", s -> new AlumnoImpl(s));
		for (Alumno a : alumnos) {
			mostrarAlumno(a);
		}
	}

	private static void testConstructorNormal() {
		System.out.println(
				"========Probando el primer constructor======================================================================================");
		testConstructor("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@alum.us.es");
	}

	private static void testConstructorExcepcion() {
		System.out.println(
				"\n========Probando el primer constructor, email incorrecto======================================================================================");
		testConstructor("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com");
	}

	private static void testSetEmailNormal() {
		System.out.println(
				"\n========Probando setEmail======================================================================================");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		testSetEmail(a, "juan@alum.us.es");
	}

	private static void testSetEmailExcepcion() {
		System.out.println(
				"\n========Probando setEmail, email incorrecto======================================================================================");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		testSetEmail(a, "juan@gmail.com");
	}

	private static void testGetCurso1() {
		System.out.println(
				"\n========Probando el método getCurso para un alumno con asignaturas matriculadas======================================================================================");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0,
				TipoAsignatura.ANUAL, 2);
		testMatriculaAsignatura(a, fp);
		testMatriculaAsignatura(a, adda);
		testGetCurso(a);
	}

	private static void testGetCurso2() {
		System.out.println(
				"\n========Probando el método getCurso para un alumno sin asignaturas matriculadas======================================================================================");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		testGetCurso(a);
	}

	private static void testMatriculaAsignaturaNormal() {
		System.out.println(
				"\n========Probando matriculaAsignatura======================================================================================");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		testMatriculaAsignatura(a, asig);
	}

	private static void testMatriculaAsignaturaExcepcion() {
		System.out.println(
				"\n========Probando matriculaAsignatura, matricula doble en una asignatura======================================================================================");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		a.matriculaAsignatura(asig);
		testMatriculaAsignatura(a, asig);
	}

	private static void testEliminaAsignaturaNormal() {
		System.out.println(
				"\n========Probando eliminaAsignatura======================================================================================");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		a.matriculaAsignatura(asig);
		testEliminaAsignatura(a, asig);
	}

	private static void testEliminaAsignaturaExcepcion() {
		System.out.println(
				"\n========Probando eliminaAsignatura, asignatura no matriculada======================================================================================");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura asig = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		testEliminaAsignatura(a, asig);
	}

	private static void testEvaluaAlumno1Normal() {
		System.out.println(
				"\n========Probando el método evaluaAlumno1======================================================================================");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		a.matriculaAsignatura(fp);
		testEvaluaAlumno1(a, fp, 2015, Convocatoria.PRIMERA, 9.5);
	}

	private static void testEvaluaAlumno1Excepcion() {
		System.out.println(
				"\n========Probando el método evaluaAlumno1 de un alumno que no está matriculado en la asignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		testEvaluaAlumno1(a, fp, 2015, Convocatoria.PRIMERA, 9.5);
	}

	private static void testEvaluaAlumno2Normal() {
		System.out.println(
				"\n========Probando el método evaluaAlumno2======================================================================================");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		a.matriculaAsignatura(fp);
		testEvaluaAlumno2(a, fp, 2015, Convocatoria.PRIMERA, 9.5, true);
	}

	private static void testEvaluaAlumno2Excepcion() {
		System.out.println(
				"\n========Probando el método evaluaAlumno2 de un alumno que no está matriculado en la asignatura");
		Alumno a = new AlumnoImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@alum.us.es");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		testEvaluaAlumno2(a, fp, 2015, Convocatoria.PRIMERA, 9.5, true);
	}

	//////////////////////////////////////////////////
	// Métodos auxiliares

	private static void testConstructor(String dni, String nombre, String apellidos, LocalDate fechaNacimiento,
			String email) {
		try {
			Alumno a = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento, email);
			mostrarAlumno(a);
		} catch (ExcepcionAlumnoNoValido e) {
			System.out.println("Se ha capturado la excepción ExcepcionAlumnoNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}

	private static void testSetEmail(Alumno a, String nuevoEmail) {
		try {
			System.out.println("El email antes de la operación es: " + a.getEmail());
			System.out.println("El nuevo email es: " + nuevoEmail);
			a.setEmail(nuevoEmail);
			System.out.println("El email después de la operación es: " + a.getEmail());
		} catch (ExcepcionAlumnoNoValido e) {
			System.out.println("Se ha capturado la excepción ExcepcionAlumnoNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}

	private static void testGetCurso(Alumno a) {
		System.out.println(
				"Las asignaturas del alumno " + a.getNombre() + " " + a.getApellidos() + " son: " + a.getAsignaturas());
		System.out.println("El curso de este alumno es: " + a.getCurso());
	}

	private static void testMatriculaAsignatura(Alumno a, Asignatura asig) {
		try {
			System.out.println("Las asignaturas antes de la operación son: " + a.getAsignaturas());
			System.out.println("Nueva asignatura a matricular: " + asig);
			a.matriculaAsignatura(asig);
			System.out.println("Las asignaturas después de la operación son: " + a.getAsignaturas());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}

	private static void testEliminaAsignatura(Alumno a, Asignatura asig) {
		try {
			System.out.println("Las asignaturas antes de la operación son: " + a.getAsignaturas());
			System.out.println("Asignatura a eliminar: " + asig);
			a.eliminaAsignatura(asig);
			System.out.println("Las asignaturas después de la operación son: " + a.getAsignaturas());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}

	private static void testEvaluaAlumno1(Alumno a, Asignatura asig, Integer curso, Convocatoria convocatoria,
			Double nota) {
		try {
			System.out.println("Las asignaturas del alumno son: " + a.getAsignaturas());
			System.out.println("Asignatura a la que se va a añadir una nota: " + asig);
			System.out.println("El expediente del alumno antes de la operación es: " + a.getExpediente());
			a.evaluaAlumno(asig, curso, convocatoria, nota);
			System.out.println("El expediente del alumno después de la operación es: " + a.getExpediente());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}

	private static void testEvaluaAlumno2(Alumno a, Asignatura asig, Integer curso, Convocatoria convocatoria,
			Double nota, Boolean mencionHonor) {
		try {
			System.out.println("Las asignaturas del alumno son: " + a.getAsignaturas());
			System.out.println("Asignatura a la que se va a añadir una nota: " + asig);
			System.out.println("El expediente del alumno antes de la operación es: " + a.getExpediente());
			a.evaluaAlumno(asig, curso, convocatoria, nota, mencionHonor);
			System.out.println("El expediente del alumno después de la operación es: " + a.getExpediente());
		} catch (ExcepcionAlumnoOperacionNoPermitida e) {
			System.out
					.println("Se ha capturado la excepción ExcepcionAlumnoOperacionNoPermitida: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción inesperada.");
		}
	}

	private static void mostrarAlumno(Alumno a) {
		System.out.println("Alumno --> <" + a + ">");
		System.out.println("\tDNI: <" + a.getDNI() + ">");
		System.out.println("\tNombre: <" + a.getNombre() + ">");
		System.out.println("\tApellidos: <" + a.getApellidos() + ">");
		System.out.println("\tFecha Nacimiento: <"
				+ a.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ">");
		System.out.println("\tEdad: <" + a.getEdad() + ">");
		System.out.println("\tEmail:  <" + a.getEmail() + ">");
		System.out.println("\tAsignaturas:  <" + a.getAsignaturas() + ">");
		System.out.println("\tExpediente:  <" + a.getExpediente() + ">");
	}
}