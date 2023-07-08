package fp.grados.tipos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.TreeSet;

import fp.grados.excepciones.ExcepcionExpedienteOperacionNoPermitida;

public class ExpedienteImpl implements Expediente {

	private List<Nota> notas;

	public ExpedienteImpl() {
		this.notas = new ArrayList<Nota>();
	}

	private void checkExpediente(Nota nota) {
		Integer i = 0;
		for (Nota n : getNotas()) {
			if (n.getAsignatura().equals(n.getAsignatura()) && n.getCursoAcademico().equals(nota.getCursoAcademico())) {
				i++;
			}
		}
		if (i >= 2) {
			throw new ExcepcionExpedienteOperacionNoPermitida("");
		}

	}

	public List<Nota> getNotas() {
		return new ArrayList<Nota>(notas);
	}

	public void nuevaNota(Nota n) {
		checkExpediente(n);
		notas.remove(n);
		notas.add(n);
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Expediente) {
			Expediente e = (Expediente) o;
			res = this.getNotas().equals(e.getNotas());
		}
		return res;
	}

	public int hashCode() {
		return this.getNotas().hashCode();

	}

	public String toString() {
		return getNotas().toString();

	}

	public Double getNotaMedia() {
		Double res = 0.0;
		Integer i = 0;
		for (Nota n : getNotas()) {
			if (n.getValor() >= 5) {
				res = res + n.getValor();
				i++;
			}
		}
		if (i != 0) {
			res = res / i;
		}
		return res;
	}

	// T12
	public List<Nota> getNotasOrdenadasPorAsignatura() {
		Comparator<Nota> cmp = Comparator.comparing(Nota::getAsignatura).thenComparing(Comparator.naturalOrder());
		List<Nota> res = new ArrayList<Nota>();
		res.addAll(notas);
		Collections.sort(res, cmp);
		return res;

	}

	// T12
	public Nota getMejorNota() {

		Comparator<Nota> cmp = Comparator.comparing(Nota::getMencionHonor).reversed().thenComparing(Nota::getValor)
				.reversed().thenComparing(Nota::getConvocatoria).thenComparing(Nota::getCursoAcademico);
		SortedSet<Nota> res = new TreeSet<>(cmp);
		res.addAll(notas);
		if (notas.isEmpty()) {
			throw new NoSuchElementException("No hay notas");
		}

		return res.first();
	}
}