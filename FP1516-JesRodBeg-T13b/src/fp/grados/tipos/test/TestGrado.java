package fp.grados.tipos.test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionGradoNoValido;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.TipoAsignatura;

public class TestGrado {
	public static void main(String[] args) {
		testConstructorNormal();
		testConstructorExcepcion1();
		testConstructorExcepcion2();
		testConstructorExcepcion3();
		testConstructorExcepcion4();
		
		testGetNumeroTotalCreditos();
		
		testGetDepartamentos();
		
		testGetProfesores();

		testGetAsignaturasCurso();
		
		testGetAsignaturaCodigo();
		
		testIgualdad();
		testOrden();
	}

	private static void testConstructorNormal() {
		System.out.println("========Probando el constructor======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Set<Asignatura> asignaturasObligatorias = new HashSet<>();
		Set<Asignatura> asignaturasOptativas = new HashSet<>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		testConstructor("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
	}

	private static void testConstructorExcepcion1() {
		System.out.println("\n========Probando el constructor con asignaturas optativas con diferentes créditos======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 6.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Set<Asignatura> asignaturasObligatorias = new HashSet<>();
		Set<Asignatura> asignaturasOptativas = new HashSet<>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		asignaturasOptativas.add(ir);
		asignaturasOptativas.add(so);
		testConstructor("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
	}

	private static void testConstructorExcepcion2() {
		System.out.println("\n========Probando el constructor con asignaturas optativas que no son cuatrimestrales======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 12.0, TipoAsignatura.ANUAL, 1, dep);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Set<Asignatura> asignaturasObligatorias = new HashSet<>();
		Set<Asignatura> asignaturasOptativas = new HashSet<>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		asignaturasOptativas.add(ir);
		asignaturasOptativas.add(so);
		testConstructor("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
	}

	private static void testConstructorExcepcion3() {
		System.out.println("\n========Probando el constructor con un mínimo de créditos optativos menor que 0======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Set<Asignatura> asignaturasObligatorias = new HashSet<>();
		Set<Asignatura> asignaturasOptativas = new HashSet<>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		asignaturasOptativas.add(ir);
		asignaturasOptativas.add(so);
		testConstructor("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, -3.0);
	}

	private static void testConstructorExcepcion4() {
		System.out.println("\n========Probando el constructor con un mínimo de créditos optativos mayor el número total de créditos optativos======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Set<Asignatura> asignaturasObligatorias = new HashSet<>();
		Set<Asignatura> asignaturasOptativas = new HashSet<>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		asignaturasOptativas.add(ir);
		asignaturasOptativas.add(so);
		Double numeroTotalCreditosOptativas = adda.getCreditos() + ir.getCreditos() + so.getCreditos();
		testConstructor("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, numeroTotalCreditosOptativas + 1.0);
	}

	private static void testGetNumeroTotalCreditos() {
		System.out.println("\n========Probando getNumeroTotalCreditos======================================================================================");
		Departamento dep = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Set<Asignatura> asignaturasObligatorias = new HashSet<>();
		Set<Asignatura> asignaturasOptativas = new HashSet<>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		asignaturasOptativas.add(ir);
		asignaturasOptativas.add(so);
		Grado g = new GradoImpl("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
		testGetNumeroTotalCreditos(g);
	}
	
	private static void testGetDepartamentos() {
		System.out.println("\n========Probando getDepartamentos======================================================================================");
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl("Ciencias de la Computación e Inteligencia Artificial");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, lsi);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, lsi);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, lsi);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, ccia);
		Set<Asignatura> asignaturasObligatorias = new HashSet<>();
		Set<Asignatura> asignaturasOptativas = new HashSet<>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		asignaturasOptativas.add(ir);
		asignaturasOptativas.add(so);
		Grado g = new GradoImpl("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
		testGetDepartamentos(g);
	}
	
	private static void testGetProfesores() {
		System.out.println("\n========Probando getProfesores======================================================================================");
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl("Ciencias de la Computación e Inteligencia Artificial");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, lsi);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, lsi);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, lsi);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, ccia);
		Profesor pr1 = new ProfesorImpl("12345678Z", "Juan", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR);
		Profesor pr2 = new ProfesorImpl("12345678Z", "Pepe", "Nadie Nadie", LocalDate.of(1950, 3, 15), "juan.nadie@gmail.com", Categoria.TITULAR);
		Set<Asignatura> asignaturasObligatorias = new HashSet<>();
		Set<Asignatura> asignaturasOptativas = new HashSet<>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		asignaturasOptativas.add(ir);
		asignaturasOptativas.add(so);
		lsi.nuevoProfesor(pr1);
		ccia.nuevoProfesor(pr2);
		Grado g = new GradoImpl("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
		testGetProfesores(g);
	}
	
	private static void testGetAsignaturasCurso() {
		System.out.println("\n========Probando getAsignaturas(Curso)======================================================================================");
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl("Ciencias de la Computación e Inteligencia Artificial");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, lsi);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, lsi);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2, lsi);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2, ccia);
		Set<Asignatura> asignaturasObligatorias = new HashSet<>();
		Set<Asignatura> asignaturasOptativas = new HashSet<>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		asignaturasOptativas.add(ir);
		asignaturasOptativas.add(so);
		Grado g = new GradoImpl("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
		testGetAsignaturas(g, 2);
	}
	
	private static void testGetAsignaturaCodigo() {
		System.out.println("\n========Probando getAsignatura(Codigo)======================================================================================");
		Departamento lsi = new DepartamentoImpl("Lenguajes y Sistemas Informáticos");
		Departamento ccia = new DepartamentoImpl("Ciencias de la Computación e Inteligencia Artificial");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, lsi);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, lsi);
		Asignatura ir = new AsignaturaImpl("Ingeniería de Requisitos", "2050020", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2, lsi);
		Asignatura so = new AsignaturaImpl("Sistemas Operativos", "2050014", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 2, ccia);
		Set<Asignatura> asignaturasObligatorias = new HashSet<>();
		Set<Asignatura> asignaturasOptativas = new HashSet<>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		asignaturasOptativas.add(ir);
		asignaturasOptativas.add(so);
		Grado g = new GradoImpl("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
		testGetAsignatura(g, "2050001");
	}
	
	private static void testIgualdad() {
		System.out.println("\n========Probando igualdad======================================================================================");
		Departamento dep = new DepartamentoImpl("LSI");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		Grado g1 = new GradoImpl("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
		Grado g2 = new GradoImpl("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
		Grado g3 = new GradoImpl("Ingeniería Informática de Computadores", asignaturasObligatorias, asignaturasOptativas, 12.0);
		System.out.println("Código hash del objeto g1 (" + g1 + "): " + g1.hashCode());
		System.out.println("Código hash del objeto g2 (" + g2 + "): " + g2.hashCode());
		System.out.println("Código hash del objeto g3 (" + g3 + "): " + g3.hashCode());
		System.out.println("¿Es g1 igual a g2? (debe ser true): " + g1.equals(g2));
		System.out.println("¿Es g1 distinto de g3? (debe ser true): " + !g1.equals(g3));
	}
	
	private static void testOrden() {
		System.out.println("\n========Probando orden natural======================================================================================");
		Departamento dep = new DepartamentoImpl("LSI");
		Asignatura fp = new AsignaturaImpl("Fundamentos de Programacion", "2050001", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Asignatura adda = new AsignaturaImpl("Analisis y Diseño de Datos y Algoritmos", "2050010", 12.0, TipoAsignatura.PRIMER_CUATRIMESTRE, 1, dep);
		Set<Asignatura> asignaturasObligatorias = new HashSet<Asignatura>();
		Set<Asignatura> asignaturasOptativas = new HashSet<Asignatura>();
		asignaturasObligatorias.add(fp);
		asignaturasOptativas.add(adda);
		Grado menor = new GradoImpl("Ingeniería Informática de Computadores", asignaturasObligatorias, asignaturasOptativas, 12.0);
		Grado igual1 = new GradoImpl("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
		Grado igual2 = new GradoImpl("Ingeniería Informática de Software", asignaturasObligatorias, asignaturasOptativas, 12.0);
		Grado mayor = new GradoImpl("Ingeniería Informática de Tecnologías Informáticas", asignaturasObligatorias, asignaturasOptativas, 12.0);
		System.out.println("- Debe ser ANTES: ");
		compara(menor, igual1);
		System.out.println("- Debe ser MISMA POSICIÓN: ");
		compara(igual1, igual2);
		System.out.println("- Debe ser DESPUÉS: ");
		compara(mayor, igual2);
	}


	
	//////////////////////////////////////////////////
	//Métodos auxiliares
	
	private static void testConstructor(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas, Double numeroMinimoCreditosOptativas) {
		try {
			Grado g = new GradoImpl(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
			mostrarGrado(g);
		} catch (ExcepcionGradoNoValido e) {
			System.out.println("Se ha capturado la excepción ExcepcionGradoNoValido: \n\t" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha capturado una excepción insesperada.");
		}
	}
	
	private static void testGetNumeroTotalCreditos(Grado g){
		System.out.println("Las asignaturas obligatorias del grado '" + g + "' son:");
		mostrarCreditosAsignaturasObligatoriasGrado(g);
		System.out.println("Las asignaturas optativas del grado '" + g + "' son:");
		mostrarCreditosAsignaturasOptativasGrado(g);
		System.out.println("El número total de créditos para el grado '" + g + "' es: " + g.getNumeroTotalCreditos());
	}
	
	private static void testGetDepartamentos(Grado g){
		System.out.println("Los departamentos del grado '" + g + "' son:");
		mostrarDepartamentosGrado(g);
	}
	
	private static void testGetProfesores(Grado g){
		System.out.println("Los profesores que imparten docencia en el grado '" + g + "' son:");
		mostrarProfesoresGrado(g);
	}
	
	private static void testGetAsignaturas(Grado g, Integer curso){
		System.out.println("Las asignaturas obligatorias del grado '" + g + "' son:");
		mostrarCursoAsignaturasObligatoriasGrado(g);
		System.out.println("Las asignaturas optativas del grado '" + g + "' son:");
		mostrarCursoAsignaturasOptativasGrado(g);
		System.out.println("Las asignaturas, tanto obligatorias como optativas, del grado '" + g + "' que pertenecen al curso " + curso + " son: " + g.getAsignaturas(curso));
	}
	
	private static void testGetAsignatura(Grado g, String codigo){
		System.out.println("Las asignaturas obligatorias del grado '" + g + "' son:");
		mostrarAsignaturasObligatoriasGrado(g);
		System.out.println("Las asignaturas optativas del grado '" + g + "' son:");
		mostrarAsignaturasOptativasGrado(g);
		System.out.println("Las asignatura del grado '" + g + "' cuyo código es " + codigo + " es: " + g.getAsignatura(codigo));
	}
	
	private static void compara(Grado g1, Grado g2) {
		System.out.print("El objeto <" + g1 + ">");
		if (g1.compareTo(g2) < 0) {
			System.out.print(" va ANTES que el objeto ");
		} else if (g1.compareTo(g2) > 0) {
			System.out.print(" va DESPUÉS que el objeto ");
		} else {
			System.out.print(" va en la MISMA POSICIÓN que el objeto ");
		}
		System.out.println("<" + g2 + ">");
	}
	
	private static void mostrarCreditosAsignaturasObligatoriasGrado(Grado g) {
		for (Asignatura asignaturasObligatorias : g.getAsignaturasObligatorias()) {
			System.out.println(asignaturasObligatorias + " <" + asignaturasObligatorias.getCreditos() + " créditos>");
		}
	}
	
	private static void mostrarCreditosAsignaturasOptativasGrado(Grado g) {
		for (Asignatura asignaturasOptativas : g.getAsignaturasOptativas()) {
			System.out.println(asignaturasOptativas + " <" + asignaturasOptativas.getCreditos() + " créditos>");
		}
	}
	
	private static void mostrarDepartamentosGrado(Grado g) {
		for (Departamento departamentos : g.getDepartamentos()) {
			System.out.println(departamentos);
		}
	}
	
	private static void mostrarProfesoresGrado(Grado g) {
		for (Profesor profesores : g.getProfesores()) {
			System.out.println(profesores);
		}
	}
	
	private static void mostrarCursoAsignaturasObligatoriasGrado(Grado g) {
		for (Asignatura asignaturasObligatorias : g.getAsignaturasObligatorias()) {
			System.out.println(asignaturasObligatorias + " <" + asignaturasObligatorias.getCurso() + "º>");
		}
	}
	
	private static void mostrarCursoAsignaturasOptativasGrado(Grado g) {
		for (Asignatura asignaturasOptativas : g.getAsignaturasOptativas()) {
			System.out.println(asignaturasOptativas + " <" + asignaturasOptativas.getCurso() + "º>");
		}
	}
	
	private static void mostrarAsignaturasObligatoriasGrado(Grado g) {
		for (Asignatura asignaturasObligatorias : g.getAsignaturasObligatorias()) {
			System.out.println(asignaturasObligatorias);
		}
	}
	
	private static void mostrarAsignaturasOptativasGrado(Grado g) {
		for (Asignatura asignaturasOptativas : g.getAsignaturasOptativas()) {
			System.out.println(asignaturasOptativas);
		}
	}

	private static void mostrarGrado(Grado g) {
		System.out.println("Grado --> <" + g + ">");
		System.out.println("\tAsignaturas obligatorias: <" + g.getAsignaturasObligatorias() + ">");
		System.out.println("\tAsignaturas optativas: <" + g.getAsignaturasOptativas() + ">");
		System.out.println("\tNúmero mínimo de créditos de asignaturas optativas: <" + g.getNumeroMinimoCreditosOptativas() + ">");
	}
}