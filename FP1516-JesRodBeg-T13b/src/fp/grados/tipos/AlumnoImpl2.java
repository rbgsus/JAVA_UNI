package fp.grados.tipos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import fp.grados.excepciones.ExcepcionAlumnoOperacionNoPermitida;

public class AlumnoImpl2 extends AlumnoImpl {

	public AlumnoImpl2(String alumno) {
		super(alumno);
	}

	// T12
	public AlumnoImpl2(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, String email) {
		super(dni, nombre, apellidos, fechaNacimiento, email);
	}

	// T12
	public Integer getCurso() {
		Integer res = 0;
		Optional<Asignatura> cmp = getAsignaturas().stream().max(Comparator.comparing(Asignatura::getCurso));
		if (cmp.isPresent()) {
			res = cmp.get().getCurso();

		}else{
			throw new ExcepcionAlumnoOperacionNoPermitida("El alumno no está matriculado");
		}

		return res;
	}

	// T13

	public SortedMap<Asignatura, Calificacion> getCalificacionPorAsignatura() {
		Map<Asignatura, Calificacion> res = getExpediente().getNotas().stream().collect(Collectors.toMap(Nota::getAsignatura, Nota::getCalificacion,
				(c1, c2) -> maximaCalificacion(c1,c2)));
		return new TreeMap<Asignatura, Calificacion>(res);

	}

	private Calificacion maximaCalificacion(Calificacion c1, Calificacion c2){
		Calificacion res;
		if(c1.compareTo(c2)>=0){
			res = c1;
		}else{
			res = c1;
		}return res;
	}


}
