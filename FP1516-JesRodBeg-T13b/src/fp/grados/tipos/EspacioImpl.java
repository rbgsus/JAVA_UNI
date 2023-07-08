package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionEspacioNoValido;

public class EspacioImpl implements Espacio {
	private TipoEspacio tipo;
	private String nombre;
	private Integer capacidad;
	private Integer planta;

	// T10
	// A0.10,0,100,TEORIA

	public EspacioImpl(String espacio) {
		String[] campos = espacio.split(",");
		if (campos.length != 4) {
			throw new IllegalArgumentException("no vale así");
		}

		Integer capacidad = new Integer(campos[2].trim());
		checkCapacidad(capacidad);

		this.tipo = TipoEspacio.valueOf(campos[3].trim());
		this.nombre = campos[0].trim();
		this.capacidad = capacidad;
		this.planta = new Integer(campos[1].trim());

	}

	public EspacioImpl(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {
		checkCapacidad(capacidad);
		this.tipo = tipo;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.planta = planta;

	}

	private static void checkCapacidad(Integer capacidad) {
		if (capacidad < 1)
			throw new ExcepcionEspacioNoValido("La capacidad debe de ser mayor a 0");

	}

	public TipoEspacio getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public Integer getPlanta() {
		return planta;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	public void setCapacidad(Integer capacidad) {
		checkCapacidad(capacidad);
		this.capacidad = capacidad;
	}

	public void setTipo(TipoEspacio tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		String s = getNombre() + " (Planta " + getPlanta() + ")";
		return s;
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Espacio) {
			Espacio e = (Espacio) o;
			res = this.getNombre().equals(e.getNombre()) && this.getPlanta().equals(e.getPlanta());
		}
		return res;
	}

	public int hashCode() {
		return getPlanta().hashCode() + getNombre().hashCode() * 31;
	}

	public int compareTo(Espacio e) {
		int res = this.getPlanta().compareTo(e.getPlanta());
		if (res == 0)
			res = getNombre().compareTo(e.getNombre());
		return res;
	}

}