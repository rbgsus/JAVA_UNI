package fp.grados.tipos;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionGradoNoValido;

public class GradoImpl implements Grado {

	private String nombre;
	private Set<Asignatura> asignaturasObligatorias;
	private Set<Asignatura> asignaturasOptativas;
	private Double numeroMinimoCreditosOptativas;

	public GradoImpl(String nombre, Set<Asignatura> asignaturasObligatorias, Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas) {
		checkMinimoCreditosOptativas(asignaturasOptativas, numeroMinimoCreditosOptativas);
		checkAsignaturasOptativas(asignaturasOptativas);

		this.nombre = nombre;
		this.numeroMinimoCreditosOptativas = numeroMinimoCreditosOptativas;
		this.asignaturasObligatorias = new HashSet<>(asignaturasObligatorias);
		this.asignaturasOptativas = new HashSet<>(asignaturasOptativas);
	}

	public String getNombre() {
		return nombre;
	}

	public Set<Asignatura> getAsignaturasObligatorias() {
		return new HashSet<>(asignaturasObligatorias);
	}

	public Set<Asignatura> getAsignaturasOptativas() {
		return new HashSet<>(asignaturasOptativas);
	}

	public Double getNumeroMinimoCreditosOptativas() {
		return numeroMinimoCreditosOptativas;

	}

	public Set<Asignatura> getAsignaturas(Integer curso) {
		Set<Asignatura> asignaturas = new HashSet<>();
		for (Asignatura a : getAsignaturas()) {
			if (a.getCurso() == curso) {
				asignaturas.add(a);

			}
		}
		return asignaturas;
	}

	private Set<Asignatura> getAsignaturas() {
		Set<Asignatura> asignaturas = new HashSet<>();
		for (Asignatura asig : getAsignaturasObligatorias()) {
			asignaturas.add(asig);
		}
		for (Asignatura asig : getAsignaturasOptativas()) {
			asignaturas.add(asig);
		}
		return asignaturas;
	}

	public Asignatura getAsignatura(String codigo) {
		Asignatura res = null;
		for (Asignatura a : getAsignaturas()) {
			if (a.getCodigo().equals(codigo))
				res = a;
		}
		return res;
	}

	public Double getNumeroTotalCreditos() {
		Double numeroTotalCreditosObligatorios = 0.0;
		for (Asignatura a : getAsignaturasObligatorias()) {
			numeroTotalCreditosObligatorios = numeroTotalCreditosObligatorios + a.getCreditos();
		}
		Double numeroTotalCreditos = numeroTotalCreditosObligatorios + getNumeroMinimoCreditosOptativas();
		return numeroTotalCreditos;
	}

	public Set<Departamento> getDepartamentos() {
		Set<Departamento> departamentos = new HashSet<>();

		for (Asignatura a : getAsignaturasObligatorias()) {
			departamentos.add(a.getDepartamento());
		}
		for (Asignatura a : getAsignaturasOptativas()) {
			departamentos.add(a.getDepartamento());
		}
		return departamentos;

	}

	public Set<Profesor> getProfesores() {
		Set<Profesor> profesores = new HashSet<>();
		for (Departamento d : getDepartamentos()) {
			profesores.addAll(d.getProfesores());
		}
		return profesores;
	}

	private void checkAsignaturasOptativas(Set<Asignatura> asignaturasOptativas) {
		Double creditos = 0.0;
		Integer i = 0;
		for (Asignatura a : asignaturasOptativas) {
			if (i == 0) {
				creditos = a.getCreditos();
			}
			i++;
			if (!(a.getCreditos().equals(creditos))) {
				throw new ExcepcionGradoNoValido(
						"Todas las asignaturas optativas de un grado deben tener el mismo número de créditos.");
			}
			if (a.getTipo().equals(TipoAsignatura.ANUAL)) {
				throw new ExcepcionGradoNoValido("Las asignaturas deben ser cuatrimestrales.");
			}
		}
	}

	private void checkMinimoCreditosOptativas(Set<Asignatura> asignaturasOptativas,
			Double numeroMinimoCreditosOptativas) {
		Double numeroTotalCreditosOptativas = 0.0;
		for (Asignatura a : asignaturasOptativas) {
			numeroTotalCreditosOptativas = numeroTotalCreditosOptativas + a.getCreditos();
		}
		if (numeroMinimoCreditosOptativas < 0 || numeroMinimoCreditosOptativas > numeroTotalCreditosOptativas) {
			throw new ExcepcionGradoNoValido(
					"El número mínimo de créditos de asignaturas optativas que debe cursar un alumno debe estar comprendido entre cero y "
							+ numeroTotalCreditosOptativas + ".");
		}
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Grado) {
			Grado g = (Grado) o;
			res = this.getNombre().equals(g.getNombre());
		}
		return res;
	}

	public int hashCode() {
		return this.getNombre().hashCode();
	}

	public int compareTo(Grado g) {
		return this.getNombre().compareTo(g.getNombre());

	}

	public String toString() {
		return getNombre();
	}

	//T9
	public SortedMap<Asignatura, Double> getCreditosPorAsignatura() {
		SortedMap<Asignatura, Double> res = new TreeMap<>();
		for (Asignatura a : getAsignaturas()) {
			res.put(a, a.getCreditos());
		}
		return res;

	}

	// T12
	public SortedSet<Departamento> getDepartamentosOrdenadosPorAsignaturas() {
		Comparator<Departamento> cmp = Comparator.comparing((Departamento d) -> d.getAsignaturas().size()).reversed()
				.thenComparing(Comparator.naturalOrder());
		SortedSet<Departamento> res = new TreeSet<Departamento>(cmp);
		res.addAll(getDepartamentos());
		return res;
	}

}
