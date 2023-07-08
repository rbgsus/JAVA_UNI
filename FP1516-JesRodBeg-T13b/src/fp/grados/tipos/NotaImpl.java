package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionNotaNoValida;

public class NotaImpl implements Nota {
	private Asignatura asignatura;
	private Integer cursoAcademico;
	private Convocatoria convocatoria;
	private Double valor;
	private Boolean mencionHonor;

	// T10
	// Fundamentos de Programación#1234567#12.0#ANUAL#1;2014;PRIMERA;10.0;true
	public NotaImpl(String nota) {
		String[] campos = nota.split(";");
		if (campos.length != 5) {
			throw new IllegalArgumentException("Laspalmao");
		}

		Double valor = new Double(campos[3].trim());
		Boolean mencionHonor = new Boolean(campos[4].trim());

		checkValor(valor);
		checkMencionHonor(mencionHonor, valor);

		this.asignatura = new AsignaturaImpl(campos[0].trim());
		this.cursoAcademico = new Integer(campos[1]);
		this.convocatoria = Convocatoria.valueOf((campos[2].trim()));
		this.valor = valor;
		this.mencionHonor = mencionHonor;

	}

	public NotaImpl(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor,
			Boolean mencionHonor) {
		checkValor(valor);
		checkMencionHonor(mencionHonor, valor);
		this.asignatura = asignatura;
		this.cursoAcademico = cursoAcademico;
		this.convocatoria = convocatoria;
		this.valor = valor;
		this.mencionHonor = mencionHonor;

	}

	public NotaImpl(Asignatura asignatura, Integer cursoAcademico, Convocatoria convocatoria, Double valor) {
		this(asignatura, cursoAcademico, convocatoria, valor, false);
	}

	private static void checkValor(Double valor) {
		if (valor < 0 || valor > 10) {
			throw new ExcepcionNotaNoValida("El valor debe estar comprendido entre 0 y 10");
		}
	}

	private void checkMencionHonor(Boolean mencionHonor, Double valor) {
		if (mencionHonor == true && valor < 9) {
			if (valor < 9) {
				throw new ExcepcionNotaNoValida(
						"Una nota solo puede tener mención de honor si es mayor o igual que 9.");
			}
		}
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public Integer getCursoAcademico() {
		return cursoAcademico;
	}

	public Convocatoria getConvocatoria() {
		return convocatoria;
	}

	public Double getValor() {
		return valor;
	}

	public Boolean getMencionHonor() {
		return mencionHonor;
	}

	public Calificacion getCalificacion() {
		Calificacion res = null;
		if (valor < 5)
			res = Calificacion.SUSPENSO;
		else if (valor < 7)
			res = Calificacion.APROBADO;
		else if (valor < 9)
			res = Calificacion.NOTABLE;
		else if ((valor >= 9) && (getMencionHonor() == false))
			res = Calificacion.SOBRESALIENTE;
		else
			res = Calificacion.MATRICULA_DE_HONOR;
		return res;
	}

	public String toString() {
		Integer a = (getCursoAcademico());
		Integer b = a + 1;
		String s = getAsignatura() + ", " + getCursoAcademico() + " - " + b.toString().substring(2, 4) + ", "
				+ getConvocatoria() + ", " + getValor() + ", " + getCalificacion();
		return s;
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Nota) {
			Nota n = (Nota) o;
			res = this.getCursoAcademico().equals(n.getCursoAcademico())
					&& this.getAsignatura().equals(n.getAsignatura())
					&& this.getConvocatoria().equals(n.getConvocatoria());
		}
		return res;

	}

	public int hashCode() {
		return this.getCursoAcademico().hashCode() + this.getAsignatura().hashCode() * 31
				+ this.getConvocatoria().hashCode() * 31 * 31;
	}

	public int compareTo(Nota n) {
		int res = this.getCursoAcademico().compareTo(n.getCursoAcademico());
		if (res == 0) {
			res = this.getAsignatura().compareTo(n.getAsignatura());
			if (res == 0) {
				res = this.getConvocatoria().compareTo(n.getConvocatoria());
			}
		}
		return res;
	}

}