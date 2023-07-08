package fp.grados.tipos;

import java.util.List;

public interface Expediente {

	List<Nota> getNotas();

	Double getNotaMedia();

	void nuevaNota(Nota n);
	
	List<Nota> getNotasOrdenadasPorAsignatura();

}