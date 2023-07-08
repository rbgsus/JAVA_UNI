package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.SortedSet;

public interface Profesor extends Persona {
	SortedSet<Tutoria> getTutorias();

	Categoria getCategoria();

	void setCategoria(Categoria categoria);

	void nuevaTutoria(LocalTime horaComienzo, Integer duracionMinutos, DayOfWeek dia);

	void borraTutoria(LocalTime horaComienzo, DayOfWeek dia);

	void borraTutorias();

	public Departamento getDepartamento();

	void setDepartamento(Departamento d);

	List<Asignatura> getAsignaturas();

	List<Double> getCreditos();

	Double getDedicacionTotal();

	Double dedicacionAsignatura(Asignatura asig);

	void imparteAsignatura(Asignatura asig, Double dedicacion);

	void eliminaAsignatura(Asignatura asig);
}