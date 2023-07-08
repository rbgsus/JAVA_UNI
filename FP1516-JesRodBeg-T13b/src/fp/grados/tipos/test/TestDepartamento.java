package fp.grados.tipos.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestDepartamento {
	public static void main(String[] args) {
		testConstructor();

		testRelacionBidireccionalAsignaturas();
		testRelacionBidireccionalProfesores();

		testBorraTutorias();

		testBorraTutoriasCategoria();

		testExisteProfesorAsignado();

		testEstanTodasAsignaturasAsignadas();

		testEliminaAsignacionProfesoradoAsignatura();

		testIgualdad();
		testOrden();
	}

	private static void testConstructor() {
		System.out.println(
				"========Probando el constructor======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		mostrarDepartamento(dep);
	}

	private static void testRelacionBidireccionalAsignaturas() {
		System.out.println(
				"\n========Probando la relación bidireccional entre Departamento y Asignatura======================================================================================");
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl("Ciencias de la Computación e Inteligencia Artificial");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programación", "2050001", 12.0, TipoAsignatura.ANUAL, 1,
				lsi);
		System.out.println("- Mostramos el departamento LSI y CCIA y la asignatura FP para ver sus estados actuales:");
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);
		System.out.println(
				"- Añadimos la asignatura FP al departamento CCIA, mediante la operación nuevaAsignatura del tipo Departamento, y volvemos a ver sus estados actuales:");
		ccia.nuevaAsignatura(fp);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);
		System.out.println(
				"- Cambiamos al departamento LSI la asignatura FP, mediante la operación setDepartamento del tipo Asignatura, y volvemos a ver sus estados actuales:");
		fp.setDepartamento(lsi);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);
		System.out.println(
				"- Eliminamos del departamento LSI la asignatura FP, mediante la operación eliminaAsignatura del tipo Departamento, y volvemos a ver sus estados actuales:");
		lsi.eliminaAsignatura(fp);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarAsignatura(fp);
	}

	private static void testRelacionBidireccionalProfesores() {
		System.out.println(
				"\n========Probando la relación bidireccional entre Departamento y Profesor======================================================================================");
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl("Ciencias de la Computación e Inteligencia Artificial");
		Profesor juan = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@gmail.es", Categoria.TITULAR, lsi);
		System.out.println("- Mostramos el departamento LSI y CCIA y el profesor Juan para ver sus estados actuales:");
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarProfesor(juan);
		System.out.println(
				"- Añadimos el profesor Juan al departamento CCIA, mediante la operación nuevoProfesor del tipo Departamento, y volvemos a ver sus estados actuales:");
		ccia.nuevoProfesor(juan);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarProfesor(juan);
		System.out.println(
				"- Cambiamos al departamento LSI el profesor Juan, mediante la operación setDepartamento del tipo Profesor, y volvemos a ver sus estados actuales:");
		juan.setDepartamento(lsi);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarProfesor(juan);
		System.out.println(
				"- Eliminamos del departamento LSI el profesor Juan, mediante la operación eliminaProfesor del tipo Departamento, y volvemos a ver sus estados actuales:");
		lsi.eliminaProfesor(juan);
		mostrarDepartamento(lsi);
		mostrarDepartamento(ccia);
		mostrarProfesor(juan);
	}

	private static void testBorraTutorias() {
		System.out.println(
				"\n========Probando borraTutorias======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Profesor pr1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor pr2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Profesor pr3 = new ProfesorImpl("12345678Z", "Luisa", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"luisa.nadie@us.es", Categoria.AYUDANTE);
		pr1.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.MONDAY);
		pr2.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.MONDAY);
		pr3.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.MONDAY);
		dep.nuevoProfesor(pr1);
		dep.nuevoProfesor(pr2);
		dep.nuevoProfesor(pr3);
		testBorraTutorias(dep);
	}

	private static void testBorraTutoriasCategoria() {
		System.out.println(
				"\n========Probando borraTutorias(Categoria)======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Profesor pr1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor pr2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Profesor pr3 = new ProfesorImpl("12345678Z", "Luisa", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"luisa.nadie@us.es", Categoria.AYUDANTE);
		pr1.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.MONDAY);
		pr2.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.MONDAY);
		pr3.nuevaTutoria(LocalTime.of(10, 30), 60, DayOfWeek.MONDAY);
		dep.nuevoProfesor(pr1);
		dep.nuevoProfesor(pr2);
		dep.nuevoProfesor(pr3);
		testBorraTutoriasCategoria(dep, Categoria.AYUDANTE);
	}

	private static void testExisteProfesorAsignado() {
		System.out.println(
				"\n========Probando existeProfesorAsignado======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Profesor pr1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor pr2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Profesor pr3 = new ProfesorImpl("12345678Z", "Luisa", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"luisa.nadie@us.es", Categoria.AYUDANTE);
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0,
				TipoAsignatura.ANUAL, 2);
		dep.nuevoProfesor(pr1);
		dep.nuevoProfesor(pr2);
		dep.nuevoProfesor(pr3);
		dep.nuevaAsignatura(fp);
		pr1.imparteAsignatura(fp, 3.0);
		testExisteProfesorAsignado(dep, fp);
		testExisteProfesorAsignado(dep, adda);
	}

	private static void testEstanTodasAsignaturasAsignadas() {
		System.out.println(
				"\n========Probando estanTodasAsignaturasAsignadas======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Profesor pr1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor pr2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Profesor pr3 = new ProfesorImpl("12345678Z", "Luisa", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"luisa.nadie@us.es", Categoria.AYUDANTE);
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0,
				TipoAsignatura.ANUAL, 2);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 3);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 6.0, TipoAsignatura.PRIMER_CUATRIMESTRE,
				2);
		dep.nuevoProfesor(pr1);
		dep.nuevoProfesor(pr2);
		dep.nuevoProfesor(pr3);
		dep.nuevaAsignatura(fp);
		dep.nuevaAsignatura(adda);
		dep.nuevaAsignatura(ir);
		dep.nuevaAsignatura(so);
		pr1.imparteAsignatura(ir, 1.0);
		pr1.imparteAsignatura(so, 3.5);
		pr2.imparteAsignatura(so, 3.0);
		pr2.imparteAsignatura(adda, 3.0);
		pr3.imparteAsignatura(ir, 2.0);
		pr3.imparteAsignatura(fp, 3.0);
		testEstanTodasAsignaturasAsignadas(dep);
		pr2.eliminaAsignatura(adda);
		testEstanTodasAsignaturasAsignadas(dep);
	}

	private static void testEliminaAsignacionProfesoradoAsignatura() {
		System.out.println(
				"\n========Probando eliminaAsignacionProfesoradoAsignatura======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Profesor pr1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"juan.nadie@us.es", Categoria.AYUDANTE);
		Profesor pr2 = new ProfesorImpl("12345678Z", "Miguel", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"miguel.nadie@us.es", Categoria.CATEDRATICO);
		Profesor pr3 = new ProfesorImpl("12345678Z", "Luisa", "Nadie Nadie", LocalDate.of(1950, 3, 15),
				"luisa.nadie@us.es", Categoria.AYUDANTE);
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.ANUAL, 1);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0,
				TipoAsignatura.ANUAL, 2);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 6.0,
				TipoAsignatura.PRIMER_CUATRIMESTRE, 3);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 6.0, TipoAsignatura.PRIMER_CUATRIMESTRE,
				2);
		dep.nuevoProfesor(pr1);
		dep.nuevoProfesor(pr2);
		dep.nuevoProfesor(pr3);
		dep.nuevaAsignatura(fp);
		dep.nuevaAsignatura(adda);
		dep.nuevaAsignatura(ir);
		dep.nuevaAsignatura(so);
		pr1.imparteAsignatura(ir, 1.0);
		pr1.imparteAsignatura(so, 3.5);
		pr2.imparteAsignatura(so, 3.0);
		pr2.imparteAsignatura(adda, 3.0);
		pr3.imparteAsignatura(ir, 2.0);
		pr3.imparteAsignatura(fp, 3.0);
		testEliminaAsignacionProfesoradoAsignatura(dep, so);
	}

	private static void testIgualdad() {
		System.out.println(
				"\n========Probando igualdad con dos objetos iguales======================================================================================");
		Departamento d1 = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento d2 = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento d3 = new DepartamentoImpl("Arquitectura y Tecnología de Computadores");
		System.out.println("Código hash del objeto d1 (" + d1 + "): " + d1.hashCode());
		System.out.println("Código hash del objeto d2 (" + d2 + "): " + d2.hashCode());
		System.out.println("Código hash del objeto d3 (" + d3 + "): " + d3.hashCode());
		System.out.println("¿Es d1 igual a d2? (debe ser true): " + d1.equals(d2));
		System.out.println("¿Es d1 distinto de d3? (debe ser true): " + !d1.equals(d3));
	}

	private static void testOrden() {
		System.out.println(
				"\n========Probando orden natural======================================================================================");
		Departamento menor = new DepartamentoImpl("Arquitectura y Tecnología de Computadores");
		Departamento igual1 = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento igual2 = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento mayor = new DepartamentoImpl("Tecnología Electrónica");
		System.out.println("- Debe ser ANTES: ");
		compara(menor, igual1);
		System.out.println("- Debe ser MISMA POSICIÓN: ");
		compara(igual1, igual2);
		System.out.println("- Debe ser DESPUÉS: ");
		compara(mayor, igual2);
	}

	//////////////////////////////////////////////////
	// Métodos auxiliares

	private static void compara(Departamento d1, Departamento d2) {
		System.out.print("El objeto <" + d1 + ">");
		if (d1.compareTo(d2) < 0) {
			System.out.print(" va ANTES que el objeto ");
		} else if (d1.compareTo(d2) > 0) {
			System.out.print(" va DESPUÉS que el objeto ");
		} else {
			System.out.print(" va en la MISMA POSICIÓN que el objeto ");
		}
		System.out.println("<" + d2 + ">");
	}

	private static void testBorraTutorias(Departamento dep) {
		System.out.println("Las tutorias de los profesores del departamento " + dep + " antes de la operación son:");
		mostrarTutoriasDepartamento(dep);
		System.out.println("Tutorias a eliminar:");
		mostrarTutoriasDepartamento(dep);
		dep.borraTutorias();
		System.out.println("Las tutorias de los profesores del departamento " + dep + " después de la operación son:");
		mostrarTutoriasDepartamento(dep);
	}

	private static void testBorraTutoriasCategoria(Departamento dep, Categoria cat) {
		System.out.println("Las tutorias de los profesores del departamento " + dep + " antes de la operación son:");
		mostrarTutoriasDepartamento(dep);
		System.out.println("Tutorias a eliminar:");
		for (Profesor pr : dep.getProfesores()) {
			if (pr.getCategoria() == cat) {
				System.out.println("Profesor " + pr + ": " + pr.getTutorias());
			}
		}
		dep.borraTutorias(cat);
		System.out.println("Las tutorias de los profesores del departamento " + dep + " después de la operación son:");
		mostrarTutoriasDepartamento(dep);
	}

	private static void testExisteProfesorAsignado(Departamento dep, Asignatura asig) {
		System.out.println("Los profesores del departamento " + dep + " imparten las siguientes asignaturas:");
		mostrarProfesoresDepartamento(dep);
		System.out.println(
				"¿Existe algún profesor asignado a la asignatura " + asig + "?: " + dep.existeProfesorAsignado(asig));
	}

	private static void testEstanTodasAsignaturasAsignadas(Departamento dep) {
		System.out.println("Las asignaturas del departamento " + dep + " son:");
		mostrarAsignaturasDepartamento(dep);
		System.out.println("Los profesores del departamento " + dep + " imparten las siguientes asignaturas:");
		mostrarProfesoresDepartamento(dep);
		System.out.println("¿Tienen todas las asignaturas asignado al menos un profesor?: "
				+ dep.estanTodasAsignaturasAsignadas());
	}

	private static void testEliminaAsignacionProfesoradoAsignatura(Departamento dep, Asignatura asig) {
		System.out.println("Los profesores del departamento " + dep
				+ " imparten las siguientes asignaturas antes de la operación:");
		mostrarProfesoresDepartamento(dep);
		System.out.println("Asignatura a eliminar:" + asig);
		dep.eliminaAsignacionProfesorado(asig);
		System.out.println("Los profesores del departamento " + dep
				+ " imparten las siguientes asignaturas después de la operación:");
		mostrarProfesoresDepartamento(dep);
	}

	private static void mostrarTutoriasDepartamento(Departamento dep) {
		for (Profesor pr : dep.getProfesores()) {
			System.out.println("Profesor " + pr + ": " + pr.getTutorias());
		}
	}

	private static void mostrarProfesoresDepartamento(Departamento dep) {
		for (Profesor pr : dep.getProfesores()) {
			System.out.println("Profesor " + pr + ": " + pr.getAsignaturas());
		}
	}

	private static void mostrarAsignaturasDepartamento(Departamento dep) {
		for (Asignatura asig : dep.getAsignaturas()) {
			System.out.println(asig);
		}
	}

	private static void mostrarAsignatura(Asignatura asig) {
		System.out.println("Asignatura --> <" + asig + ">");
		System.out.println("\tDepartamento: <" + asig.getDepartamento() + ">");
	}

	private static void mostrarProfesor(Profesor pr) {
		System.out.println("Profesor --> <" + pr + ">");
		System.out.println("\tDepartamento: <" + pr.getDepartamento() + ">");
	}

	private static void mostrarDepartamento(Departamento dep) {
		System.out.println("Departamento --> <" + dep + ">");
		System.out.println("\tAsignaturas: <" + dep.getAsignaturas() + ">");
		System.out.println("\tProfesores: <" + dep.getProfesores() + ">");
	}
}