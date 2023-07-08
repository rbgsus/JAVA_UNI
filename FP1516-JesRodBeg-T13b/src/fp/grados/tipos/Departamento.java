package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Departamento extends Comparable<Departamento> {

	String getNombre();

	// relacionado con asignatura:
	Set<Asignatura> getAsignaturas();

	void nuevaAsignatura(Asignatura asig);

	void eliminaAsignatura(Asignatura asig);

	// relacionado con profesor:

	Set<Profesor> getProfesores();

	void nuevoProfesor(Profesor p);

	void eliminaProfesor(Profesor p);

	/////////////////////////////////////////////

	Boolean existeProfesorAsignado(Asignatura a);

	Boolean estanTodasAsignaturasAsignadas();

	void eliminaAsignacionProfesorado(Asignatura a);

	void borraTutorias();

	void borraTutorias(Categoria c);

	SortedMap<Asignatura, SortedSet<Profesor>> getProfesoresPorAsignatura();

	SortedMap<String, SortedSet<Tutoria>> getTutoriasPorProfesor();

	// T12
	Profesor getProfesorMaximaDedicacionMediaPorAsignatura();

}