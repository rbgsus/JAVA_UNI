package fp.grados.tipos;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GradoImpl2 extends GradoImpl {

	// T13.B AULA
	public GradoImpl2(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas) {
		super(nombre, asignaturasObligatorias, asignaturasOptativas, numeroMinimoCreditosOptativas);
	}

	public Double getNumeroTotalCreditos() {
		return getAsignaturasObligatorias().stream().mapToDouble(a -> a.getCreditos()).sum()
				+ getNumeroMinimoCreditosOptativas();
	}

	public Set<Asignatura> getAsignaturas(Integer curso) {
		return Stream.concat(getAsignaturasObligatorias().stream(), getAsignaturasOptativas().stream())
				.filter(a -> a.getCurso().equals(curso)).collect(Collectors.toSet());
	}

	// T 13.B CASA
	public Asignatura getAsignatura(String codigo) {
		return todasAsignaturas().filter(a -> a.getCodigo().equals(codigo)).findAny().orElse(null);
	}

	private Stream<Asignatura> todasAsignaturas() {
		return Stream.concat(getAsignaturasObligatorias().stream(), getAsignaturasOptativas().stream());
	}

	public Set<Departamento> getDepartamentos() {
		return todasAsignaturas().map(Asignatura::getDepartamento).collect(Collectors.toSet());

	}

	public Set<Profesor> getProfesores() {
		return getDepartamentos().stream().flatMap(d -> d.getProfesores().stream()).collect(Collectors.toSet());

	}

	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		return new TreeMap<Asignatura, Double>(
				todasAsignaturas().collect(Collectors.toMap(a -> a, a -> a.getCreditos())));

	}

}
