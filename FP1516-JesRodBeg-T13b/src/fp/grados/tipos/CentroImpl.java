package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionCentroNoValido;
import fp.grados.excepciones.ExcepcionCentroOperacionNoPermitida;

public class CentroImpl implements Centro {

	private String nombre;
	private String direccion;
	private Integer plantas;
	private Integer sotanos;
	private Set<Espacio> espacios;

	public CentroImpl(String nombre, String direccion, Integer plantas, Integer sotanos) {
		checkPlantas(plantas);
		checkSotanos(sotanos);

		this.nombre = nombre;
		this.direccion = direccion;
		this.plantas = plantas;
		this.sotanos = sotanos;
		espacios = new HashSet<>();

	}

	private void checkPlantas(Integer p) {
		if (p < 1) {
			throw new ExcepcionCentroNoValido("El número de plantas debe ser como mínimo 1");
		}
	}

	public void checkSotanos(Integer s) {
		if (s < 0) {
			throw new ExcepcionCentroNoValido("El número de sótanos debe ser mayor o igual que cero");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getNumeroPlantas() {
		return plantas;
	}

	public Integer getNumeroSotanos() {
		return sotanos;
	}

	public Set<Espacio> getEspacios() {
		return new HashSet<>(espacios);
	}

	public Integer[] getConteosEspacios() {
		// Integer[] res = new Integer[5];
		Integer[] res = { 0, 0, 0, 0, 0 };
		for (Espacio e : getEspacios()) {
			switch (e.getTipo()) {
			case TEORIA:
				res[0]++;
				break;
			case LABORATORIO:
				res[1]++;
				break;
			case SEMINARIO:
				res[2]++;
				break;
			case EXAMEN:
				res[3]++;
				break;
			case OTRO:
				res[4]++;
				break;
			}
		}
		return res;
	}

	public Set<Despacho> getDespachos() {
		Set<Despacho> res = new HashSet<>();
		for (Espacio e : getEspacios()) {
			if (e instanceof Despacho) {
				res.add((Despacho) e);
			}
		}
		return res;
	}

	public Set<Despacho> getDespachos(Departamento dept) {
		Set<Despacho> res = new HashSet<>();
		for (Despacho desp : getDespachos()) {
			if (existeProfesorDepartamento(desp.getProfesores(), dept))
				res.add(desp);

		}

		return res;

	}

	private boolean existeProfesorDepartamento(Set<Profesor> profesores, Departamento dept) {
		Boolean res = false;
		for (Profesor p : profesores) {
			if (p.getDepartamento().equals(dept)) {
				res = true;
			}
		}
		return res;
	}

	public void nuevoEspacio(Espacio e) {
		Integer plantaEspacio = e.getPlanta();
		Integer plantasCentro = getNumeroPlantas();
		Integer sotanosCentro = getNumeroSotanos();
		if (-sotanosCentro <= plantaEspacio) {
			if (plantaEspacio <= plantasCentro - 1) {
				espacios.add(e);
			} else {
				throw new ExcepcionCentroOperacionNoPermitida("No está comprendido dentro del intervalo.");
			}
		} else {
			throw new ExcepcionCentroOperacionNoPermitida("No está comprendido dentro del intervalo.");
		}
	}

	public void eliminaEspacio(Espacio e) {
		if (espacios.contains(e)) {
			espacios.remove(e);
		}
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Centro) {
			Centro c = (Centro) o;
			res = this.getNombre().equals(c.getNombre());
		}

		return res;
	}

	public int hashCode() {
		return getNombre().hashCode();
	}

	public int compareTo(Centro c) {
		return this.getNombre().compareTo(c.getNombre());

	}

	public String toString() {
		return this.getNombre();
	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> res = new HashSet<>();
		for (Despacho desp : getDespachos()) {
			res.addAll(desp.getProfesores());
		}
		return res;
	}

	public Set<Profesor> getProfesores(Departamento d) {
		Set<Profesor> res = new HashSet<>();
		for (Profesor prof : getProfesores()) {
			if (prof.getDepartamento().equals(d)) {
				res.add(prof);
			}
		}
		return res;
	}

	public Espacio getEspacioMayorCapacidad() {
		Espacio res = null;
		Integer cap = 0;
		for (Espacio es : getEspacios()) {
			if (es.getCapacidad() > cap) {
				cap = es.getCapacidad();
				res = es;
			}
		}
		if (res == null) {
			throw new ExcepcionCentroOperacionNoPermitida("No existe ningún espacio");
		}
		return res;
	}
	
	// T9
	
	public SortedMap<String, Despacho> getDespachosPorProfesor() {
		SortedMap<String, Despacho> res = new TreeMap<String, Despacho>();
		for (Despacho d : getDespachos()) {
			anyadeProfesor(d, res);
		}
		return res;

	}

	private void anyadeProfesor(Despacho d, SortedMap<String, Despacho> res) {
		for (Profesor p : d.getProfesores()) {
			res.put(p.toString(), d);
		}
	}

	// T 12
	public SortedSet<Espacio> getEspaciosOrdenadosPorCapacidad() {
		Comparator<Espacio> cmp = Comparator.comparing(Espacio::getCapacidad).reversed()
				.thenComparing(Comparator.naturalOrder());
		SortedSet<Espacio> res = new TreeSet<Espacio>(cmp);
		res.addAll(espacios);
		return res;

	}

}
