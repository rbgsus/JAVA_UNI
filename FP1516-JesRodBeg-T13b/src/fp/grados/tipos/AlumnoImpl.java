package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionAlumnoNoValido;
import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;

public class AlumnoImpl extends PersonaImpl implements Alumno {

	private Set<Asignatura> asignaturas;
	private Expediente expediente;

	// T10
	// 123456789Z,Ronaldo Luis, Nazario de Lima, 22/091976,
	// ronaldoelGordo@gmail.com
	public AlumnoImpl(String alumno) {
		super(alumno);
		checkEmailUniversidad(getEmail());
		this.asignaturas = new HashSet<>();
		this.expediente = new ExpedienteImpl();

	}

	public AlumnoImpl(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
		checkEmailUniversidad(email);
		asignaturas = new HashSet<>();
		this.expediente = new ExpedienteImpl();
	}

	private void checkEmailUniversidad(String email) {
		if (!email.endsWith("@alum.us.es")) {
			throw new ExcepcionAlumnoNoValido("El email debe terminar en @alum.us.es");

		}
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public Set<Asignatura> getAsignaturas() {
		return new HashSet<>(asignaturas);

	}

	public Boolean estaMatriculadoEn(Asignatura asig) {
		return asignaturas.contains(asig);
	}

	public void matriculaAsignatura(Asignatura asig) {
		if (estaMatriculadoEn(asig)) {
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno ya está matriculado en la asignatura");
		}

		asignaturas.add(asig);

	}

	public void eliminaAsignatura(Asignatura asig) {
		if (!estaMatriculadoEn(asig)) {
			throw new ExcepcionAlumnoOperacionNoPermitida("Él alumno no está matriculado en la asignatura");
		}

		asignaturas.remove(asig);
	}

	public void setEmail(String email) {
		checkEmailUniversidad(email);
		super.setEmail(email);
	}

	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota,
			Boolean mencionHonor) {
		if (estaMatriculadoEn(a)) {
			Nota n = new NotaImpl(a, curso, convocatoria, nota);
			expediente.nuevaNota(n);
		} else {
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno no está matriculado en la asignatura");
		}
	}

	public void evaluaAlumno(Asignatura a, Integer curso, Convocatoria convocatoria, Double nota) {
		if (estaMatriculadoEn(a)) {
			Nota n = new NotaImpl(a, curso, convocatoria, nota);
			expediente.nuevaNota(n);

		} else {
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno no está matriculado en la asignatura");
		}
	}

	public String toString() {
		return "(" + getCurso() + "º) " + super.toString();
	}

	public Integer getCurso() {
		Integer res = 0;
		for (Asignatura a : getAsignaturas()) {
			if (a.getCurso() > res) {
				res = a.getCurso();
			}
		}

		return res;
	}

	// T9
	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {
		SortedMap<Asignatura, Calificacion> res = new TreeMap<>();
		for (Nota n : getExpediente().getNotas()) {
			Calificacion c = res.get(n.getAsignatura());
			if (c == null || n.getCalificacion().compareTo(c) > 0) {
				res.put(n.getAsignatura(), n.getCalificacion());

			}
		}
		return res;

	}
//T12
	public SortedSet<Asignatura> getAsignaturasOrdenadasPorCurso() {
		Comparator<Asignatura> cmp = Comparator.comparing(Asignatura::getCurso).reversed()
				.thenComparing(Comparator.naturalOrder());
		SortedSet<Asignatura> res = new TreeSet<Asignatura>(cmp);
		res.addAll(asignaturas);
		return res;
	}
}