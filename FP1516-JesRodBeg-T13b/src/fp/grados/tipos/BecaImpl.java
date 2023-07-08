package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionBecaNoValida;

public class BecaImpl implements Beca {
	private static final Double CUANTIA_MINIMA = 1500.0;
	private String codigo;
	private Double cuantiaTotal;
	private Integer duracion;
	private TipoBeca tipo;

	// T10
	// ABC1234,   6000.0,  12,  ORDINARIA

	public BecaImpl(String beca) {
		String[] campos = beca.split(",");
		if (campos.length != 4) {
			throw new IllegalArgumentException("Algo has puesto mal");
		}

		String codigo = campos[0].trim();
		Double cuantiaTotal = new Double(campos[1].trim());
		Integer duracion = new Integer(campos[2].trim());

		checkCodigo(codigo);
		checkCuantiaTotal(cuantiaTotal);
		checkDuracion(duracion);

		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = TipoBeca.valueOf(campos[3].trim());

	}

	public BecaImpl(String codigo, Double cuantiaTotal, Integer duracion, TipoBeca tipo) {
		checkCodigo(codigo);
		checkCuantiaTotal(cuantiaTotal);
		checkDuracion(duracion);

		this.codigo = codigo;
		this.cuantiaTotal = cuantiaTotal;
		this.duracion = duracion;
		this.tipo = tipo;

	}

	public BecaImpl(String codigo, TipoBeca tipo) {
		checkCodigo(codigo);

		this.codigo = codigo;
		this.tipo = tipo;
		this.cuantiaTotal = CUANTIA_MINIMA;
		this.duracion = 1;
	}

	private static void checkCodigo(String codigo) {
		Boolean esCorrecto = codigo.length() == 7 && Character.isLetter(codigo.charAt(0))
				&& Character.isLetter(codigo.charAt(1)) && Character.isLetter(codigo.charAt(2))
				&& Character.isDigit(codigo.charAt(3)) && Character.isDigit(codigo.charAt(4))
				&& Character.isDigit(codigo.charAt(5)) && Character.isDigit(codigo.charAt(6));

		if (!esCorrecto) {
			throw new ExcepcionBecaNoValida("El código debe estar formado por 3 letras y 4 dígitos.");
		}
	}

	private static void checkCuantiaTotal(Double cuantiaTotal) {
		if (cuantiaTotal < CUANTIA_MINIMA) {
			throw new ExcepcionBecaNoValida("La cuantía total debe ser como mínimo " + CUANTIA_MINIMA + " euros.");
		}

	}

	private static void checkDuracion(Integer duracion) {
		if (duracion < 1) {
			throw new ExcepcionBecaNoValida("La duración debe ser de al menos un mes");
		}

	}

	public String getCodigo() {
		return codigo;
	}

	public Double getCuantiaTotal() {
		return cuantiaTotal;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public TipoBeca getTipo() {
		return tipo;
	}

	public Double getCuantiaMensual() {
		return getCuantiaTotal() / getDuracion();
	}

	public void setCuantiaTotal(Double cuantiaTotal) {
		checkCuantiaTotal(cuantiaTotal);
		this.cuantiaTotal = cuantiaTotal;
	}

	public void setDuracion(Integer duracion) {
		checkDuracion(duracion);
		this.duracion = duracion;
	}

	public String toString() {
		String s = "[" + getCodigo() + ", " + getTipo() + "]";
		return s;
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Beca) {
			Beca b = (Beca) o;
			res = this.getCodigo().equals(b.getCodigo()) && this.getTipo().equals(b.getTipo());
		}
		return res;
	}

	public int hashCode() {
		return this.getCodigo().hashCode() + this.getTipo().hashCode() * 31;
	}

	public int compareTo(Beca b) {
		int res = this.getCodigo().compareTo(b.getCodigo());
		if (res == 0) {
			res = this.getTipo().compareTo(b.getTipo());
		}
		return res;
	}
}