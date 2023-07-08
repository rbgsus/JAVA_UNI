package fp.grados.tipos;

import java.time.LocalDate;

public interface Becario extends Alumno {

	Beca getBeca();

	LocalDate getFechaComienzo();

	void setFechaComienzo(LocalDate fecha);

	LocalDate getFechaFin();
}
