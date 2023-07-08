package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class DepartamentoImpl implements Departamento {

	private String nombre;
	private Set<Asignatura> asignaturas;
	private Set<Profesor> profesores;

	public DepartamentoImpl(String nombre) {
		this.nombre = nombre;
		asignaturas = new HashSet<>();
		profesores = new HashSet<>();
	}

	public String getNombre() {
		return nombre;
	}

	public Set<Asignatura> getAsignaturas() {
		return new HashSet<>(asignaturas);

	}

	public void nuevaAsignatura(Asignatura asig) {
		asignaturas.add(asig);
		asig.setDepartamento(this);
	}

	public void eliminaAsignatura(Asignatura asig) {
		if (asignaturas.remove(asig)) {
			asignaturas.remove(asig);
			asig.setDepartamento(null);
		}
	}

	public Set<Profesor> getProfesores() {
		return new HashSet<>(profesores);
	}

	public void nuevoProfesor(Profesor prof) {
		profesores.add(prof);
		prof.setDepartamento(this);
	}

	public void eliminaProfesor(Profesor prof) {
		if (profesores.remove(prof)) {
			profesores.remove(prof);
			prof.setDepartamento(null);
		}

	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Departamento) {
			Departamento d = (Departamento) o;
			res = this.getNombre().equals(d.getNombre());
		}
		return res;

	}

	public int hashCode() {
		return this.getNombre().hashCode();
	}

	public int compareTo(Departamento d) {
		return this.getNombre().compareTo(d.getNombre());
	}

	public String toString() {
		return getNombre();

	}

	public Boolean existeProfesorAsignado(Asignatura a) {
		Boolean res = false;
		for (Profesor prof : getProfesores()) {
			if (prof.getAsignaturas().contains(a)) {
				res = true;
				break;
			}
		}
		return res;
	}

	public Boolean estanTodasAsignaturasAsignadas() {
		Boolean res = true;
		for (Asignatura a : getAsignaturas()) {
			if (!existeProfesorAsignado(a)) {
				res = false;
				break;
			}
		}
		return res;

	}

	public void eliminaAsignacionProfesorado(Asignatura a) {
		for (Profesor prof : getProfesores()) {
			if (prof.getAsignaturas().contains(a)) {
				prof.eliminaAsignatura(a);
			}
		}
	}

	public void borraTutorias() {
		for (Profesor prof : getProfesores()) {
			prof.borraTutorias();
		}
	}

	public void borraTutorias(Categoria c) {
		for (Profesor prof : getProfesores()) {
			if (prof.getCategoria().equals(c)) {
				prof.borraTutorias();
			}
		}
	}

	public SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura() {
		SortedMap<Asignatura, SortedSet<Profesor>> res = new TreeMap<>();

		for (Profesor p : getProfesores()) {
			anyadeAsignatura(p, res);
		}
		return res;
	}

	private void anyadeAsignatura(Profesor p, SortedMap<Asignatura, SortedSet<Profesor>> res) {
		for (Asignatura a : p.getAsignaturas()) {
			if (res.containsKey(a)) {
				res.get(a).add(p);
			} else {
				SortedSet<Profesor> s = new TreeSet<>();
				s.add(p);
				res.put(a, s);
			}
		}

	}
// T9
	public SortedMap<String, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		SortedMap<String, SortedSet<Tutoria>> res = new TreeMap<>();
		for (Profesor p : getProfesores()) {
			anyadeTutoria(p, res);

		}
		return res;
	}

	private void anyadeTutoria(Profesor p, SortedMap<String, SortedSet<Tutoria>> res) {
		for (Tutoria t : p.getTutorias()) {
			if (res.containsKey(p.toString())) {
				res.get(p.toString()).add(t);
			} else {
				SortedSet<Tutoria> s = new TreeSet<Tutoria>();
				s.add(t);
				res.put(p.toString(), s);
			}
		}
	}

	// T12
	public Profesor getProfesorMaximaDedicacionMediaPorAsignatura() {
		Comparator<Profesor> cmp = Comparator
				.comparing(profesor -> profesor.getDedicacionTotal() / profesor.getAsignaturas().size());
		return getProfesores().stream().filter(profesor -> !profesor.getAsignaturas().isEmpty()).max(cmp).get();

	}

}
