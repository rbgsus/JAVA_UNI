package fp.grados.tipos;

import java.util.HashSet;
import java.util.Set;

import fp.grados.excepciones.ExcepcionDespachoNoValido;

public class DespachoImpl extends EspacioImpl implements Despacho {

	private Set<Profesor> profesores = new HashSet<Profesor>();

	// T10
	// F1.43,1,3
	public DespachoImpl(String despacho) {
		super(despacho + "," + TipoEspacio.OTRO);
		checkProfesores(getProfesores());
		this.profesores = new HashSet<Profesor>();

	}

	public DespachoImpl(String nombre, Integer capacidad, Integer planta, Set<Profesor> profesores) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		checkProfesores(profesores);
		this.profesores = profesores;
	}

	public DespachoImpl(String nombre, Integer capacidad, Integer planta, Profesor profesor) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		profesores.add(profesor);
	}

	public DespachoImpl(String nombre, Integer capacidad, Integer planta) {
		super(TipoEspacio.OTRO, nombre, capacidad, planta);
		this.profesores = new HashSet<Profesor>();
	}

	private void checkProfesores(Set<Profesor> maestros) {
		if (maestros.size() > getCapacidad()) {
			throw new ExcepcionDespachoNoValido(
					"El número de profesores no puede ser mayor a la capacidad del despacho.");
		}
	}

	public void setTipo(TipoEspacio t) {
		throw new UnsupportedOperationException("No puede modificarse el tipo, siempre tiene que ser 'Otro'.");
	}

	public void setCapacidad(Integer capacidad) {
		if (capacidad < getProfesores().size()) {
			throw new ExcepcionDespachoNoValido("La capacidad no se puede ser inferior a " + getProfesores().size());
		}
		super.setCapacidad(capacidad);
	}

	public Set<Profesor> getProfesores() {
		return new HashSet<>(profesores);
	}

	public void setProfesores(Set<Profesor> p) {
		checkProfesores(p);
		profesores = p;
	}

	public String toString() {
		return super.toString() + " " + getProfesores().toString();
	}

}