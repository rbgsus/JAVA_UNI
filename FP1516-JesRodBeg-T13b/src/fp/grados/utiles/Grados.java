package fp.grados.utiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import fp.grados.tipos.Alumno;
import fp.grados.tipos.AlumnoImpl;
import fp.grados.tipos.AlumnoImpl2;
import fp.grados.tipos.Asignatura;
import fp.grados.tipos.AsignaturaImpl;
import fp.grados.tipos.Beca;
import fp.grados.tipos.BecaImpl;
import fp.grados.tipos.Categoria;
import fp.grados.tipos.Centro;
import fp.grados.tipos.CentroImpl;
import fp.grados.tipos.CentroImpl2;
import fp.grados.tipos.Departamento;
import fp.grados.tipos.DepartamentoImpl;
import fp.grados.tipos.DepartamentoImpl2;
import fp.grados.tipos.Despacho;
import fp.grados.tipos.DespachoImpl;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.EspacioImpl;
import fp.grados.tipos.Expediente;
import fp.grados.tipos.ExpedienteImpl;
import fp.grados.tipos.ExpedienteImpl2;
import fp.grados.tipos.Grado;
import fp.grados.tipos.GradoImpl;
import fp.grados.tipos.GradoImpl2;
import fp.grados.tipos.Nota;
import fp.grados.tipos.Profesor;
import fp.grados.tipos.ProfesorImpl;
import fp.grados.tipos.ProfesorImpl2;
import fp.grados.tipos.TipoAsignatura;
import fp.grados.tipos.TipoBeca;
import fp.grados.tipos.TipoEspacio;
import fp.grados.tipos.Tutoria;

public class Grados {

	/************************ T 11 Asignatura ******************************/
	/************************ T 11 Asignatura ******************************/

	private static Map<String, Asignatura> asignaturasPorCodigo = new HashMap<String, Asignatura>();
// objetos creados
	public static Integer getNumAsignaturasCreadas() {
		return asignaturasPorCodigo.size();
	}

	public static Set<Asignatura> getAsignaturasCreadas() {
		return new HashSet<Asignatura>(asignaturasPorCodigo.values());
	}

	public static Set<String> getCodigosAsignaturasCreadas() {
		return new HashSet<String>(asignaturasPorCodigo.keySet());
	}

	public static Asignatura getAsignaturaCreada(String codigo) {
		return asignaturasPorCodigo.get(codigo);
	}
// actualiza poblacionales
	private static void actualizaPoblacionales(Asignatura a) {
		asignaturasPorCodigo.put(a.getCodigo(), a);
	}
// creacional por parámetros
	public static Asignatura createAsignatura(String nombre, String codigo, Double creditos, TipoAsignatura tipo,
			Integer curso, Departamento departamento) {
		Asignatura res = new AsignaturaImpl(nombre, codigo, creditos, tipo, curso, departamento);
		actualizaPoblacionales(res);
		return res;
	}
// creacional a partir de string
	public static Asignatura createAsignatura(String asignatura) {
		Asignatura res = new AsignaturaImpl(asignatura);
		actualizaPoblacionales(res);
		return res;
	}
// creacinal a partir de fichero
	public static List<Asignatura> createAsignaturas(String nombreFichero) {
		List<Asignatura> res = leeFichero(nombreFichero, s -> createAsignatura(s));
		return res;
	}
	/* ////////////////// T 11 FIN Asignatura ///////////////////////// */

	/************************ T 11 BECA ******************************/
	/************************ T 11 BECA ******************************/

	private static Set<Beca> becas = new HashSet<Beca>();
	private static Integer[] numBecasPorTipo = { 0, 0, 0 };

	// objetos creados
	public static Integer getNumBecasCreadas() {
		return becas.size();
	}

	public static Set<Beca> getBecasCreadas() {
		return new HashSet<Beca>(becas);
	}

	public static Integer getNumBecasTipo(TipoBeca tipo) {
		return numBecasPorTipo[tipo.ordinal()];
	}
// actualiza poblacionales
	private static void actualizaPoblacionales(Beca b) {
		becas.add(b);
		numBecasPorTipo[b.getTipo().ordinal()]++;
	}
// creacinal por parámetros
	public static Beca createBeca(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo) {
		Beca res = new BecaImpl(codigo, cuantiaTotal, duracion, tipo);
		actualizaPoblacionales(res);
		return res;
	}

	public static Beca createBeca(String codigo, TipoBeca tipo) {
		Beca res = new BecaImpl(codigo, tipo);
		actualizaPoblacionales(res);
		return res;
	}
// creacinal copia
	public static Beca createBeca(Beca beca) {
		Beca res = new BecaImpl(beca.getCodigo(), beca.getCuantiaTotal(), beca.getDuracion(), beca.getTipo());
		actualizaPoblacionales(res);
		return res;
	}
// creacinal a partir de string
	public static Beca createBeca(String beca) {
		Beca res = new BecaImpl(beca);
		actualizaPoblacionales(res);
		return res;
	}
// creacinal a partir de fichero
	public static List<Beca> createBecas(String nombreFichero) {
		List<Beca> res = leeFichero(nombreFichero, s -> createBeca(s));
		return res;
	}

	/* ////////////////// T 11 FIN Beca ///////////////////////// */

	/************************ T 11 PROFESOR ******************************/
	/************************ T 11 PROFESOR ******************************/

	private static Boolean usarImplementacionMap = true;
	private static Set<Profesor> profesores = new HashSet<Profesor>();

// objetos creados
	public static Integer getNumProfesoresCreados() {
		return profesores.size();
	}

	public static Set<Profesor> getProfesoresCreados() {
		return new HashSet<Profesor>(profesores);
	}

	public static void setUsarImplementacionMapProfesor(Boolean b) {
		usarImplementacionMap = b;
	}
// actualiza poblacionales
	private static void actualizaPoblacionales(Profesor p) {
		profesores.add(p);
	}
// creacional por parámetros
	public static Profesor createProfesor(String dni, String nombre, String apellidos, LocalDate fechaNacimiento,
			String email, Categoria categoria, Departamento departamento) {
		Profesor res = null;
		if (usarImplementacionMap) {
			res = new ProfesorImpl2(dni, nombre, apellidos, fechaNacimiento, email, categoria, departamento);
		} else {
			res = new ProfesorImpl(dni, nombre, apellidos, fechaNacimiento, email, categoria, departamento);
		}
		actualizaPoblacionales(res);
		return res;
	}
// creacional copia
	public static Profesor createProfesor(Profesor profesor) {
		Profesor res = createProfesor(profesor.getDNI(), profesor.getNombre(), profesor.getApellidos(),
				profesor.getFechaNacimiento(), profesor.getEmail(), profesor.getCategoria(),
				profesor.getDepartamento());
		copiaAsignaturasProfesor(res, profesor);
		copiaTutoriasProfesor(res, profesor);
		return res;
	}

	private static void copiaAsignaturasProfesor(Profesor res, Profesor profesor) {
		for (Asignatura a : profesor.getAsignaturas()) {
			res.imparteAsignatura(a, profesor.dedicacionAsignatura(a));
		}
	}

	private static void copiaTutoriasProfesor(Profesor res, Profesor profesor) {
		for (Tutoria t : profesor.getTutorias()) {
			res.nuevaTutoria(t.getHoraComienzo(), t.getDuracion(), t.getDiaSemana());
		}
	}

	/* ////////////////////////// T 11 FIN PROFESOR ///////////////////////// */

	
	
	
	
	
	
	
	/************************ T 11 DEPARTAMENTO ******************************/
	/************************ T 11 DEPARTAMENTO ******************************/

	private static Set<Departamento> departamentos = new HashSet<Departamento>();

// objetos creados
	public static Integer getNumDepartamentosCreados() {
		return departamentos.size();
	}

	public static Set<Departamento> getDepartamentosCreados() {
		return new HashSet<Departamento>(departamentos);
	}
// actualiza poblacionales
	private static void actualizaPoblacionales(Departamento d) {
		departamentos.add(d);
	}

//	public static Departamento createDepartamento(String nombre) {
//		Departamento res = new DepartamentoImpl(nombre);
//		actualizaPoblacionales(res);
//		return res;
//	}
	
	public static Departamento createDepartamento(String nombre){
		Departamento res = null;
		if (usarJava8) {
			res = new DepartamentoImpl2(nombre);
		}else{
			res = new DepartamentoImpl(nombre);
		}
		actualizaPoblacionales(res);
		return res;
	}	
	

	/* ////////////////// T 11 FIN DEPARTAMENTO ///////////////////////// */

	/************************ T 11 ALUMNO ******************************/
	/************************ T 11 ALUMNO ******************************/

	private static Set<Alumno> alumnos = new HashSet<Alumno>();
	
// actualiza poblacionales
	private static void actualizaPoblaciones(Alumno a) {
		alumnos.add(a);
	}
//objetos creados
	public static Integer getNumAlumnosCreados() {
		return alumnos.size();
	}

	public static Set<Alumno> getAlumnosCreados() {
		return new HashSet<>(alumnos);
	}

	//
	// CREACIONAL POR PARAMETRO
	// public static Alumno createAlumno(String dni, String nombre, String
	// apellidos, LocalDate fechaNacimiento, String email) {
	// Alumno a = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento,
	// email);
	// actualizaPoblaciones(a);
	// return a;
	// }

// creacional por parámetros
	public static Alumno createAlumno(String dni, String nombre, String apellidos, LocalDate fechaNacimiento,
			String email) {
		Alumno res = null;
		if (usarJava8) {
			res = new AlumnoImpl2(dni, nombre, apellidos, fechaNacimiento, email);
		} else {
			res = new AlumnoImpl(dni, nombre, apellidos, fechaNacimiento, email);
		}
		actualizaPoblaciones(res);
		return res;
	}

	// COPIA CON SUS ASIGNATURAS Y SU EXPEDIENTE

	// public static Alumno createAlumno(Alumno a) {
	// Alumno res = new AlumnoImpl(a.getDNI(), a.getNombre(), a.getApellidos(),
	// a.getFechaNacimiento(), a.getEmail());
	// actualizaPoblaciones(res);
	// copiaAsignaturas(res, a);
	// copiaExpediente(res, a);
	// return res;
	//
	// }

	public static Alumno createAlumno(Alumno a) {
		Alumno res = null;
		if (usarJava8) {
			res = new AlumnoImpl2(a.getDNI(), a.getNombre(), a.getApellidos(), a.getFechaNacimiento(), a.getEmail());
		} else {
			res = new AlumnoImpl(a.getDNI(), a.getNombre(), a.getApellidos(), a.getFechaNacimiento(), a.getEmail());
		}
		copiaAsignaturas(res, a);
		copiaExpediente(res, a);
		actualizaPoblaciones(a);
		return res;

	}

	private static void copiaAsignaturas(Alumno res, Alumno a) {
		for (Asignatura asig : a.getAsignaturas()) {
			res.matriculaAsignatura(asig);
		}
	}

	private static void copiaExpediente(Alumno res, Alumno a) {
		for (Nota n : a.getExpediente().getNotas()) {
			res.evaluaAlumno(n.getAsignatura(), n.getCursoAcademico(), n.getConvocatoria(), n.getValor(),
					n.getMencionHonor());
		}
	}

	// CREACIONAL A PARTIR DE STRING
	//
	// public static Alumno createAlumnos(String alumno) {
	// Alumno res = new AlumnoImpl(alumno);
	// actualizaPoblaciones(res);
	// return res;
	// }

// creacional a partir de String	
	public static Alumno createAlumnos(String alumno) {
		Alumno res = null;
		if (usarJava8) {
			res = new AlumnoImpl2(alumno);
		} else {
			res = new AlumnoImpl(alumno);
		}
		actualizaPoblaciones(res);
		return res;
	}

	// A PARTIR DE UN FICHERO
	public static List<Alumno> createAlumno(String nombreFichero) {
		List<Alumno> res = leeFichero(nombreFichero, s -> createAlumnos(s));
		return res;
	}

	/* ////////////////// T 11 FIN ALUMNO ///////////////////////// */
	//////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////

	
	
	
	

	
	
	
	
	/************************ T 11 CENTRO ******************************/
	/************************ T 11 CENTRO ******************************/

	private static Set<Centro> centros = new HashSet<>();
	private static Integer maximoPlantas = null;
	private static Integer maximoSotanos = null;
	private static Double mediaPlantas = 0.0;
	private static Double mediaSotanos = 0.0;
	
// actualiza poblacionales
	private static void actualizaPoblacionales(Centro c) {
		centros.add(c);
		if(maximoPlantas == null || c.getNumeroPlantas()>maximoPlantas){
			maximoPlantas = c.getNumeroPlantas();
		}
		if(maximoSotanos == null || c.getNumeroSotanos() > maximoSotanos){
			maximoSotanos = c.getNumeroSotanos();
		}
		mediaPlantas = mediaPlantas + c.getNumeroPlantas();
		mediaSotanos = mediaSotanos + c.getNumeroSotanos();
	}
	
// objetos creados
	public static Integer getNumCentrosCreados() {
		return centros.size();
	}

	public static Set<Centro> getCentrosCreados() {
		return new HashSet<>(centros);
	}	
	
	
	public static Integer getMaxPlantas() {

		return maximoPlantas;
		
//		Integer res = null;
//		for (Centro c : getCentrosCreados()) {
//			if (res == null || res.compareTo(c.getNumeroPlantas()) < 0) {
//				res = c.getNumeroPlantas();
//			}
//		}
//		return res;

	}

	public static Integer getMaxSotanos() {
		
		return maximoSotanos;
		
		
//		Integer res = null;
//		for (Centro c : getCentrosCreados()) {
//			if (res.compareTo(c.getNumeroSotanos()) < 0) {
//				res = c.getNumeroSotanos();
//
//			}
//		}
//		return res;
	}

	public static Double getMediaPlantas() {
		
		return mediaPlantas/getNumCentrosCreados();
/**		
		Double res = 0.0;
		Integer i = 0;
		for (Centro c : getCentrosCreados()) {
			if (c.getNumeroPlantas() > res) {
				i++;
				res = res + c.getNumeroPlantas();
			}
			if (i != null) {
				res = res / i;
			} else {
				res = 0.0;
			}
		}
		return res;
		**/
	}

	public static Double getMediaSotanos() {
		
		return mediaSotanos/getNumCentrosCreados();		
/**		
		Double res = 0.0;
		Integer i = 0;
		for (Centro c : getCentrosCreados()) {
			if (c.getNumeroSotanos() > res) {
				i++;
				res = res + c.getNumeroSotanos();
			}
			if (i != null) {
				res = res / i;
			} else {
				res = 0.0;
			}
		}
		return res; **/
	}

//	public static Centro createCentro(String nombre, String direccion, Integer plantas, Integer sotanos) {
//		Centro c = new CentroImpl(nombre, direccion, plantas, sotanos);
//		actualizaPoblacionales(c);
//		return c;
//	}
//
//	public static Centro createCentro(Centro c) {
//		Centro res = new CentroImpl(c.getNombre(), c.getDireccion(), c.getNumeroPlantas(), c.getNumeroSotanos());
//		actualizaPoblacionales(res);
//		copiaEspaciosCentro(c, res);
//		return res;
//	}

	private static void copiaEspaciosCentro(Centro c, Centro copia) {
		for (Espacio e : copia.getEspacios()) {
			c.nuevoEspacio(e);
		}
	}

	//////////////////////////////// T12
	private static Boolean usarJava8 = true;

	public static void setUsarJava8(Boolean b) {
		usarJava8 = b;
	}

// creacional copia	
	public static Centro createCentro(Centro c) {
		Centro res = null;
		if (usarJava8) {
			res = new CentroImpl2(c.getNombre(), c.getDireccion(), c.getNumeroPlantas(), c.getNumeroSotanos());
		} else {
			res = new CentroImpl(c.getNombre(), c.getDireccion(), c.getNumeroPlantas(), c.getNumeroSotanos());
		}
		actualizaPoblacionales(res);
		copiaEspaciosCentro(c, res);
		return res;
	}

// creacional por parámetros	
	public static Centro createCentro(String nombre, String direccion, Integer numPlantas, Integer numSotanos) {
		Centro res = null;
		if (usarJava8) {
			res = new CentroImpl2(nombre, direccion, numPlantas, numSotanos);
		} else {
			res = new CentroImpl(nombre, direccion, numPlantas, numSotanos);
		}
		actualizaPoblacionales(res);
		return res;
	}

	/////////////////////////// FIN T12

	/********************** FIIIIINNNN CENTROOOOOOO	 *****************************/

		
	/************************ T 11 GRADO ******************************/
	/************************ T 11 GRADO ******************************/

	private static Set<Grado> grados = new HashSet<>();

// actualiza poblacionales	
	private static void actualizaPoblacionales(Grado g) {
		grados.add(g);
	}

// objetos creados	
	public static Integer getNumGradosCreados() {
		return grados.size();
	}

	public static Set<Grado> getGradosCreados() {
		return new HashSet<>(grados);
	}

	public static Double getMediaAsignaturasGrados() {
		Double d = 0.0;
		Double res = 0.0;
		for (Grado g : getGradosCreados()) {
			d = d + g.getAsignaturasObligatorias().size() + g.getAsignaturasOptativas().size();
		}
		if (d > 0) {
			res = d / getNumGradosCreados();
		}
		return res;
	}

	public static Double getMediaAsignaturasObligatoriasGrados() {
		Double d = 0.0;
		Double res = 0.0;
		for (Grado g : getGradosCreados()) {
			d = d + g.getAsignaturasObligatorias().size();
		}
		if (d > 0) {
			res = d / getNumGradosCreados();
		}
		return res;
	}

	public static Double getMediaAsignaturasOptativasGrados() {
		Double d = 0.0;
		Double res = 0.0;
		for (Grado g : getGradosCreados()) {
			d = d + g.getAsignaturasOptativas().size();
		}
		if (d > 0) {
			res = d / getNumGradosCreados();
		}
		return res;
	}

//	public static Grado createGrado(String nombre, Set<Asignatura> asignaturasObligatorias,
//			Set<Asignatura> asignaturasOptativas, Double numeroMinimoCreditosOptativas) {
//		Grado g = new GradoImpl(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
//		actualizaPoblacionales(g);
//		return g;
//	}
	// T13

// creacional por parámetros	
	public static Grado createGrado(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas, Double numeroMinimoCreditosOptativas){
		Grado res = null;
		if(usarJava8){
			res =new GradoImpl2(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
		}else{
			res = new GradoImpl(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
		}
		actualizaPoblacionales(res);
		return res;
	}
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	/////////////////////// FIN GRADO FIN GRADO Ya soy ingeniero
	////////////////////////////////////////////////////////////////////////////////// /////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**************************** T 11 ESPACIO *******************************/
	/**************************** T 11 ESPACIO *******************************/

	private static SortedSet<Espacio> espacios = new TreeSet<>();

// actualiza poblacionales	
	private static void actualizaPoblacionales(Espacio e) {
		espacios.add(e);
	}

// objetos creados	
	public static Integer getNumEspaciosCreados() {
		return espacios.size();
	}

	public static SortedSet<Espacio> getEspaciosCreados() {
		return new TreeSet<>(espacios);
	}
	

	public static Integer getPlantaMayorEspacio() {
		Integer res = null;
		if(!espacios.isEmpty()){
			res = espacios.last().getPlanta();
		}
		return res;		
	}
	
	

	public static Integer getPlantaMenorEspacio() {
		Integer res = null;
		if(!espacios.isEmpty()){
			res = espacios.first().getPlanta();
		}
		return res;
	}

// creacional por parámetros	
	public static Espacio createEspacio(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		Espacio e = new EspacioImpl(tipo, nombre, capacidad, planta);
		actualizaPoblacionales(e);
		return e;
	}

// creacional copia	
	public static Espacio createEspacio(Espacio e) {
		Espacio res = new EspacioImpl(e.getTipo(), e.getNombre(), e.getCapacidad(), e.getPlanta());
		actualizaPoblacionales(res);
		return res;
	}

// creacional a partir de string	
	public static Espacio createEspacio(String espacio) {
		Espacio e = new EspacioImpl(espacio);
		actualizaPoblacionales(e);
		return e;
	}

// creacional a partir de fichero	
	public static List<Espacio> createEspacios(String nombreFichero) {
		List<Espacio> res = leeFichero(nombreFichero, s -> createEspacio(s));
		return res;
	}
	//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	/////////////////////// FIN ESPACIO /////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**************************** T 11 DESPACHO *******************************/
	/**************************** T 11 DESPACHO *******************************/

// creacional por parámetros	
	public static Despacho createDespacho(String nombre, Integer capacidad, Integer planta) {
		Despacho d = new DespachoImpl(nombre, capacidad, planta);
		actualizaPoblacionales(d);
		return d;
	}

// creacional copia	
	public static Despacho createDespacho(Despacho d) {
		Despacho despacho = new DespachoImpl(d.getNombre(), d.getCapacidad(), d.getPlanta());
		copiaProfesoresDespacho(despacho, d);
		actualizaPoblacionales(despacho);
		return despacho;
	}

	private static void copiaProfesoresDespacho(Despacho despacho, Despacho d) {
		despacho.setProfesores(d.getProfesores());
	}

// creacinal a partir de string	
	public static Despacho createDespacho(String d) {
		Despacho res = new DespachoImpl(d);
		actualizaPoblacionales(res);
		return res;
	
	}

// creacional a partir de un fichero	
	public static List<Despacho> createDespachos(String nombreFichero) {
		List<Despacho> res = leeFichero(nombreFichero, s -> createDespacho(s));
		return res;
	}

	//////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	/////////////////////// FIN DESPACHO /////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	public static <T> List<T> leeFichero(String nombreFichero, Function<String, T> funcion_deString_aT) {
		List<T> res = null;
		try {
			res = Files.lines(Paths.get(nombreFichero)).map(funcion_deString_aT).collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error en lectura del fichero: " + nombreFichero);
		}

		return res;
	}
	
	/**	  EXPEDIENTE   **/
	private static Set<Expediente> expedientes = new HashSet<>();
	
	private static void actualizaPoblacionales(Expediente e) {
		expedientes.add(e);
	}
	public static Expediente createExpediente(){
		Expediente res = null;
		if(usarJava8){
			res = new ExpedienteImpl2();
		}else{
			res = new ExpedienteImpl();
		}
		actualizaPoblacionales(res);
		return res;
	}
	
	
	

}
