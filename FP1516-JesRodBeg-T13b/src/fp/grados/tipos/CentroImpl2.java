package fp.grados.tipos;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

// T12 AULA
public class CentroImpl2 extends CentroImpl {

	public CentroImpl2(String nombre, String direccion, Integer plantas, Integer sotanos) {
		super(nombre, direccion, plantas, sotanos);

	}

	public Espacio getEspacioMayorCapacidad() {
		Optional<Espacio> res = getEspacios().stream().max(Comparator.comparing(Espacio::getCapacidad));
		if (!res.isPresent()) {
			throw new ExcepcionCentroOperacionNoPermitida("No existe ningún espacio");
		}

		return res.get();

	}

	// T13 AULA
	public Integer[] getConteosEspacios() {
		Integer[] conteos = { 0, 0, 0, 0, 0 };
		getEspacios().stream().forEach(e -> conteos[e.getTipo().ordinal()]++);
		return conteos;
		/**
		 * SOLUCIÓN ALTERNATIVA private Integer cuentaEspacioTipo(TipoEspacio
		 * tipo, Set<Espacio> espacios) { return (int)
		 * espacios.stream().filter(e -> e.getTipo().equals(tipo)).count(); }
		 * 
		 * public Integer[] getConteosEspaciosAlternativa() { return new
		 * Integer[] { cuentaEspacioTipo(TipoEspacio.TEORIA, getEspacios()),
		 * cuentaEspacioTipo(TipoEspacio.LABORATORIO, getEspacios()),
		 * cuentaEspacioTipo(TipoEspacio.SEMINARIO, getEspacios()),
		 * cuentaEspacioTipo(TipoEspacio.EXAMEN, getEspacios()),
		 * cuentaEspacioTipo(TipoEspacio.OTRO, getEspacios()), }; }
		 **/

	}

	public Set<Profesor> getProfesores() {
		return getDespachos().stream().flatMap(despacho -> despacho.getProfesores().stream())
				.collect(Collectors.toSet());
	}

	public Set<Profesor> getProfesores(Departamento departamento) {
		return getDespachos().stream().flatMap(despacho -> despacho.getProfesores().stream())
				.filter(profesores -> profesores.getDepartamento().equals(departamento)).collect(Collectors.toSet());
		/*
		 * Solución alternativa: return getProfesores().stream() .filter(p ->
		 * p.getDepartamento().equals(dep)) .collect(Collectors.toSet());
		 */
	}

	// T 13.A CASA
	public Set<Despacho> getDespachos() {
		return getEspacios().stream().filter(e -> e instanceof Despacho).map(e -> (Despacho) e)
				.collect(Collectors.toSet());

	}

	public Set<Despacho> getDespachos(Departamento departamento) {
		return getDespachos().stream().filter(d -> contieneProfesor(departamento)).collect(Collectors.toSet());

	}

	private boolean contieneProfesor(Departamento departamento) {
		return getProfesores().stream().anyMatch(p -> p.getDepartamento().equals(departamento));
	}

	// T 13.B AULA

	public SortedMap<String, Despacho> getDespachosPorProfesor() {
		return new TreeMap<String, Despacho>(
				getProfesores()
				.stream()
				.collect(Collectors.toMap(p -> p.toString(), p -> buscaDespacho(p))));
	}

	private Despacho buscaDespacho(Profesor p) {
		return getDespachos().stream().filter(d -> d.getProfesores().contains(p)).findFirst().get();
	}

}
