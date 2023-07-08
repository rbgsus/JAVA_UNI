package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionAsignaturaNoValida;

public class AsignaturaImpl implements Asignatura {

	private String nombre;
	private String codigo;
	private Double creditos;
	private TipoAsignatura tipo;
	private Integer curso;
	private Departamento departamento;

	// T10
	// "Fundamentos de Programacion#123456789Z#12.0#ANUAL#1"
	public AsignaturaImpl(String asignatura) {
		String[] campos = asignatura.split("#");
		if (campos.length != 5) {
			throw new IllegalArgumentException("Tas equivocao");
		}

		String codigo = campos[1].trim();
		Double creditos = new Double(campos[2].trim());
		Integer curso = new Integer(campos[4].trim());

		checkCodigo(codigo);
		checkCreditos(creditos);
		checkCurso(curso);

		this.nombre = campos[0].trim();
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = TipoAsignatura.valueOf(campos[3].trim());
		this.curso = curso;
		this.departamento = null;
	}

	public AsignaturaImpl(String nombre, String codigo, Double creditos, TipoAsignatura tipo, Integer curso) {
		checkCodigo(codigo);
		checkCreditos(creditos);
		checkCurso(curso);

		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
		this.departamento = null;
	}

	public AsignaturaImpl(String nombre, String codigo, Double creditos, TipoAsignatura tipo, Integer curso,
			Departamento departamento) {
		checkCodigo(codigo);
		checkCreditos(creditos);
		checkCurso(curso);

		this.nombre = nombre;
		this.codigo = codigo;
		this.creditos = creditos;
		this.tipo = tipo;
		this.curso = curso;
		setDepartamento(departamento);
	}

	private static void checkCodigo(String codigo) {
		Boolean esCorrecto = codigo.length() == 7 && Character.isDigit(codigo.charAt(0))
				&& Character.isDigit(codigo.charAt(1)) && Character.isDigit(codigo.charAt(2))
				&& Character.isDigit(codigo.charAt(3)) && Character.isDigit(codigo.charAt(4))
				&& Character.isDigit(codigo.charAt(5)) && Character.isDigit(codigo.charAt(6))
				&& Character.isDigit(codigo.charAt(6));
		if (!esCorrecto) {
			throw new ExcepcionAsignaturaNoValida("El código debe estar formado por 7 dígitos.");
		}
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento nuevoDepartamento) {
		// Si el nuevo departamento es el mismo que ya había no haríamos nada.
		if (nuevoDepartamento != this.departamento) {
			Departamento antiguoDepartamento = this.departamento;
			this.departamento = nuevoDepartamento;
			if (antiguoDepartamento != null) {
				// Si antes la asignatura pertenecía a otro departamento,
				// tenemos que actualizar ese departamento eliminando a
				// "esta asignatura"(es decir, a this) de las asignaturas
				// que conforman el departamento.
				antiguoDepartamento.eliminaAsignatura(this);
			}
			if (nuevoDepartamento != null) {
				// Si la asignatura pertenece a un nuevo departamento,
				// tenemos que actualizar este departamento para incluir a
				// "esta asignatura" (es decir, a this) en las asignaturas
				// que conforman el departamento.
				nuevoDepartamento.nuevaAsignatura(this);
			}
		}
	}

	private void checkCreditos(Double creditos) {
		if (creditos <= 0) {
			throw new ExcepcionAsignaturaNoValida("Los créditos deben ser un número positivo distinto de 0.");
		}
	}

	private static void checkCurso(Integer curso) {
		if (curso < 1 || curso > 4) {
			throw new ExcepcionAsignaturaNoValida("El número de creditos debe estar comprendido entre cero y cuatro");

		}
	}

	public String getNombre() {
		return nombre;
	}

	public String getAcronimo() {
		String res = "";
		for (char c : getNombre().toCharArray()) {
			if (Character.isUpperCase(c)) {
				res = res + c;
			}
		}
		return res;
	}

	public String getCodigo() {
		return codigo;
	}

	public Double getCreditos() {
		return creditos;
	}

	public TipoAsignatura getTipo() {
		return tipo;
	}

	public Integer getCurso() {
		return curso;
	}

	public String toString() {
		return "(" + getCodigo() + ") " + getNombre();

	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Asignatura) {
			Asignatura a = (Asignatura) o;
			res = this.getCodigo().equals(a.getCodigo());
		}

		return res;
	}

	public int hashCode() {
		return this.getCodigo().hashCode();

	}

	public int compareTo(Asignatura a) {
		return this.getCodigo().compareTo(a.getCodigo());
	}
}