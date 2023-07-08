package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface Tutoria extends Comparable<Tutoria>{

	public DayOfWeek getDiaSemana();

	public LocalTime getHoraComienzo();

	public LocalTime getHoraFin();

	public Integer getDuracion();

}