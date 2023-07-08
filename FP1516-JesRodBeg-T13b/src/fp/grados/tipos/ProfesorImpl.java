package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionProfesorNoValido;
import fp.grados.excepciones.ExcepcionProfesorOperacionNoPermitida;

public class ProfesorImpl extends PersonaImpl implements Profesor {
	private static final Double LIMITE_CREDITOS_PROFESOR = 32.0;
	private static final Double LIMITE_CREDITOS_AYUDANTE = 6.0;

	private Categoria categoria;
	private SortedSet<Tutoria> tutorias;
	private Departamento departamento;
	private List<Asignatura> asignaturas;
	private List<Double> creditos;

	public ProfesorImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email,
			Categoria categoria) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEdadProfesor(new PersonaImpl(dni, nombre, apellidos, fechaNacimiento, email));
		this.categoria = categoria;
		this.tutorias = new TreeSet<Tutoria>();
		this.departamento = null;
		this.asignaturas = new ArrayList<>();
		this.creditos = new ArrayList<>();
	}

	public ProfesorImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email,
			Categoria categoria, Departamento departamento) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEdadProfesor(new PersonaImpl(dni, nombre, apellidos, fechaNacimiento, email));
		this.categoria = categoria;
		this.tutorias = new TreeSet<Tutoria>();

		setDepartamento(departamento);
		this.asignaturas = new ArrayList<>();
		this.creditos = new ArrayList<>();
	}

	private void checkEdadProfesor(Persona p) {
		Integer edad = p.getEdad();
		if (edad < 18)
			throw new ExcepcionProfesorNoValido("El profesor tiene que ser mayor de edad.");
	}

	public List<Asignatura> getAsignaturas() {
		return new ArrayList<>(asignaturas);
	}

	public List<Double> getCreditos() {
		return new ArrayList<Double>(creditos);
	}

	public void imparteAsignatura(Asignatura asig, Double dedicacion) {
		checkAsignaturaDepartamento(asig);
		checkCreditosAsignatura(asig, dedicacion);
		if (asignaturas.contains(asig)) {
			// El profesor ya imparte esta asignatura: // hay que actualizar la
			// dedicación
			actualizaDedicacion(asig, dedicacion);
		} else {
			// El profesor no imparte aún esta asignatura:
			// hay que añadirla a ambas listas
			nuevaAsignatura(asig, dedicacion);
		}
	}

	public Double dedicacionAsignatura(Asignatura asig) {
		Double res = 0.0;
		int posicionAsignatura = asignaturas.indexOf(asig);
		if (posicionAsignatura >= 0) {
			res = creditos.get(posicionAsignatura);
		}
		return res;
	}

	// Añadimos la asignatura y la dedicación al final de sendas listas
	private void nuevaAsignatura(Asignatura asig, Double dedicacion) {
		checkLimiteCreditos(getDedicacionTotal() + (dedicacion - dedicacionAsignatura(asig)), getCategoria());
		asignaturas.add(asig);
		creditos.add(dedicacion);
	}

	// Modifica la posición correspondiente de la lista de dedicaciones
	private void actualizaDedicacion(Asignatura asig, Double dedicacion) {
		int posicionAsignatura = asignaturas.indexOf(asig);
		creditos.set(posicionAsignatura, dedicacion);
	}

	public void eliminaAsignatura(Asignatura asig) {
		int posicionAsignatura = asignaturas.indexOf(asig);
		if (posicionAsignatura >= 0) {
			creditos.remove(posicionAsignatura);
			asignaturas.remove(posicionAsignatura);
		}

	}

	private void checkAsignaturaDepartamento(Asignatura asig) {
		if (getDepartamento() == null || !getDepartamento().getAsignaturas().contains(asig))
			throw new ExcepcionProfesorOperacionNoPermitida(
					"Un profesor no puede impartir una asignatura de otro departamento");
	}

	private void checkCreditosAsignatura(Asignatura asig, Double dedicacion) {
		if (dedicacion <= 0 || dedicacion > asig.getCreditos()) {
			throw new ExcepcionProfesorOperacionNoPermitida(
					"La dedicación debe ser mayor que 0 y menor oigual que el número de créditos de la asignatura");
		}
	}

	public SortedSet<Tutoria> getTutorias() {
		return new TreeSet<Tutoria>(tutorias);
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		checkLimiteCreditos(getDedicacionTotal(), categoria);
		this.categoria = categoria;

	}

	public void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia) {
		Tutoria t = new TutoriaImpl(dia, horaComienzo, duracionMinutos);
		tutorias.add(t);
	}

	public void borraTutoria(LocalTime horaComienzo, DayOfWeek dia) {
		Tutoria t = new TutoriaImpl(dia, horaComienzo, 60);
		tutorias.remove(t);

	}

	public void borraTutorias() {
		tutorias.clear();
	}

	public Departamento getDepartamento() {
		return departamento;
	}
	
	public void setFechaNacimiento(LocalDate fecha) {
		checkFecha(fecha);
		super.setFechaNacimiento(fecha);
	}
	
	private void checkFecha(LocalDate fecha){
		if((int) fecha.until(LocalDate.now(), ChronoUnit.YEARS)< 18){
			throw new ExcepcionProfesorNoValido("El profesor debe tener al menos 18 años");
		}

	}

	private void checkLimiteCreditos(Double dedicacion, Categoria categoria) {
		if (dedicacion > LIMITE_CREDITOS_PROFESOR && !(categoria == Categoria.AYUDANTE)) {
			throw new ExcepcionProfesorOperacionNoPermitida(
					"Un profesor imparte un límite de " + LIMITE_CREDITOS_PROFESOR + " créditos.");
		}
		if (dedicacion > LIMITE_CREDITOS_AYUDANTE && (categoria == Categoria.AYUDANTE)) {
			throw new ExcepcionProfesorOperacionNoPermitida(
					"Un ayudante imparte un límite de " + LIMITE_CREDITOS_AYUDANTE + " créditos.");
		}
	}

	public void setDepartamento(Departamento nuevoDepartamento) {
		if (nuevoDepartamento != this.departamento) {
			Departamento antiguoDepartamento = this.departamento;
			this.departamento = nuevoDepartamento;

			if (antiguoDepartamento != null) {
				antiguoDepartamento.eliminaProfesor(this);

			}
			if (nuevoDepartamento != null) {
				nuevoDepartamento.nuevoProfesor(this);
			}
		}
	}

	public String toString() {
		return super.toString() + " (" + getCategoria() + ")";
	}

	public Double getDedicacionTotal() {
		Double res = 0.0;
		for (Double dedicacion : getCreditos()) {
			res = res + dedicacion;
		}
		return res;
	}
}