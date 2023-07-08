package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public interface Grado extends Comparable<Grado> {

	String getNombre();

	Set<Asignatura> getAsignaturasObligatorias();

	Set<Asignatura> getAsignaturasOptativas();

	Double getNumeroMinimoCreditosOptativas();

	Double getNumeroTotalCreditos();

	Set<Departamento> getDepartamentos();

	Set<Profesor> getProfesores();

	Set<Asignatura> getAsignaturas(Integer curso);

	Asignatura getAsignatura(String codigo);

	SortedMap<Asignatura, Double> getCreditosPorAsignatura();
// T12
	SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas();
}
