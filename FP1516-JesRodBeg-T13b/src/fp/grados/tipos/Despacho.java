package fp.grados.tipos;

import java.util.Set;

public interface Despacho extends Espacio {

	Set<Profesor> getProfesores();

	void setProfesores(Set<Profesor> p);

}