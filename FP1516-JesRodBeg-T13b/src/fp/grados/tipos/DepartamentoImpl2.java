package fp.grados.tipos;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DepartamentoImpl2 extends DepartamentoImpl {

	// T13 AULA
	public DepartamentoImpl2(String nombre) {
		super(nombre);
	}

	public void borraTutorias() {
		getProfesores().stream().forEach(Profesor::borraTutorias);
	}

	public void borraTutorias(Categoria categoria) {
		getProfesores().stream().filter(p -> p.getCategoria().equals(categoria)).forEach(Profesor::borraTutorias);
	}

	public Boolean existeProfesorAsignado(Asignatura a) {
		return getProfesores().stream().anyMatch(p -> p.getAsignaturas().contains(a));
	}

	public Boolean estanTodasAsignaturasAsignadas() {
		return getAsignaturas().stream().allMatch(a -> existeProfesorAsignado(a));
	}

	public void eliminaAsignacionProfesorado(Asignatura a) {
		getProfesores().stream().filter(p -> p.getAsignaturas().contains(a)).forEach(p -> p.eliminaAsignatura(a));
	}

		// T13b AULA
	public SortedMap<String, SortedSet<Tutoria>> getTutoriasPorProfesor() {
		return new TreeMap<String, SortedSet<Tutoria>>(
				getProfesores().stream().collect(Collectors.toMap(p -> p.toString(), p -> p.getTutorias())));
	}

}
