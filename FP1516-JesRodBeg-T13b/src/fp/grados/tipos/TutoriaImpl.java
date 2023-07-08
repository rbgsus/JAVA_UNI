package fp.grados.tipos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

import fp.grados.excepciones.ExcepcionTutoriaNoValida;

public class TutoriaImpl implements Tutoria {
	private DayOfWeek diaSemana;
	private LocalTime horaComienzo;
	private LocalTime horaFin;

	// T10
	// L,15:30,17:30
	public TutoriaImpl(String tutoria) {
		String[] campos = tutoria.split(",");
		if (campos.length != 3) {
			throw new IllegalArgumentException("Lo has roto, siempre igual...");
		}

		DayOfWeek diaSemana = DayOfWeek.valueOf(diaAbreviado(campos[0].trim()));
		LocalTime horaComienzo = LocalTime.parse(campos[1].trim(), DateTimeFormatter.ofPattern("H:m"));
		LocalTime horaFin = LocalTime.parse(campos[2].trim(), DateTimeFormatter.ofPattern("H:m"));

		checkDiaSemana(diaSemana);
		checkHoraComienzo(horaComienzo);
		checkHoraFin(horaFin);
		checkDuracionMinima(horaComienzo, horaFin);

		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaFin;

	}

	private String diaAbreviado(String d) {
		HashMap<String, String> res = new HashMap<String, String>();
		res.put("L", "MONDAY");
		res.put("M", "TUESDAY");
		res.put("X", "WEDNESDAY");
		res.put("J", "THRUSDAY");
		res.put("V", "FRYDAY");
		res.put("S", "SATURDAY");
		res.put("D", "SUNDAY");
		if (!res.containsKey(d)) {
			throw new IllegalArgumentException("Pon un día de la semana");
		}
		return res.get(d);
	}

	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, LocalTime horaFin) {
		checkDiaSemana(diaSemana);
		checkHoraComienzo(horaComienzo);
		checkHoraFin(horaFin);
		checkDuracionMinima(horaComienzo, horaFin);

		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaFin;
	}

	private void checkDuracionMinima(LocalTime horaComienzo, LocalTime horaFin) {
		Integer dur = (int) (horaComienzo.until(horaFin, ChronoUnit.MINUTES));
		if (dur < 30) {
			throw new ExcepcionTutoriaNoValida("La tutoría debe durar al menos 30 minutos.");
		}

	}

	public TutoriaImpl(DayOfWeek diaSemana, LocalTime horaComienzo, Integer duracion) {
		checkDuracion(duracion);
		checkDiaSemana(diaSemana);
		checkHoraComienzo(horaComienzo);
		checkHoraFinal(horaComienzo, duracion);

		this.diaSemana = diaSemana;
		this.horaComienzo = horaComienzo;
		this.horaFin = horaComienzo.plusMinutes(duracion);
	}

	private void checkHoraFinal(LocalTime horaComienzo, Integer duracion) {
		if (horaComienzo.plusMinutes(duracion).isAfter(LocalTime.of(21, 30))) {
			throw new ExcepcionTutoriaNoValida("las tutorias tienen que acabar antes de las 21:30");
		}
	}

	private void checkDiaSemana(DayOfWeek diaSemana) {
		if (!(diaSemana.getValue() == 1 || diaSemana.getValue() == 2 || diaSemana.getValue() == 3
				|| diaSemana.getValue() == 4 || diaSemana.getValue() == 5)) {
			throw new ExcepcionTutoriaNoValida("La tutoría solo puede ser de lunes a viernes.");
		}
	}

	private void checkHoraComienzo(LocalTime horaComienzo) {
		if (horaComienzo.isBefore(LocalTime.of(8, 30)) || horaComienzo.isAfter(LocalTime.of(21, 00))) {
			throw new ExcepcionTutoriaNoValida("La hora de comienzo debe ser posterior a las 8:30.");
		}
	}

	private void checkHoraFin(LocalTime horaFin) {
		if (horaFin.isAfter(LocalTime.of(21, 30)) || horaFin.isBefore(LocalTime.of(9, 00))) {
			throw new ExcepcionTutoriaNoValida("las tutorias tienen que acabar antes de las 21:30");
		}
	}

	private void checkDuracion(Integer duracion) {
		if (duracion < 30) {
			throw new ExcepcionTutoriaNoValida("La tutoría debe durar al menos 30 minutos.");
		}

	}

	private Character getDiaAbreviado() {
		Character diaSemana = null;
		switch (getDiaSemana()) {
		case MONDAY:
			diaSemana = 'L';
			break;
		case TUESDAY:
			diaSemana = 'M';
			break;
		case WEDNESDAY:
			diaSemana = 'X';
			break;
		case THURSDAY:
			diaSemana = 'J';
			break;
		case FRIDAY:
			diaSemana = 'V';
			break;
		case SATURDAY:
			diaSemana = 'S';
			break;
		case SUNDAY:
			diaSemana = 'D';
			break;
		}
		return diaSemana;
	}

	public DayOfWeek getDiaSemana() {
		return diaSemana;
	}

	public LocalTime getHoraComienzo() {
		return horaComienzo;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public Integer getDuracion() {
		return (int) getHoraComienzo().until(getHoraFin(), ChronoUnit.MINUTES);
	}

	public String toString() {
		String s = getDiaAbreviado() + " " + getHoraComienzo() + " - " + getHoraFin();
		return s;
	}

	public boolean equals(Object o) {
		boolean res = false;
		if (o instanceof Tutoria) {
			Tutoria t = (Tutoria) o;
			res = this.getDiaSemana().equals(t.getDiaSemana()) && this.getHoraComienzo().equals(t.getHoraComienzo());
		}
		return res;
	}

	public int hashCode() {
		int res = this.getDiaSemana().hashCode() + this.getHoraComienzo().hashCode() * 31;
		return res;

	}

	public int compareTo(Tutoria t) {
		int res = this.getDiaSemana().compareTo(t.getDiaSemana());
		if (res == 0) {
			res = this.getHoraComienzo().compareTo(t.getHoraComienzo());
		}
		return res;
	}
}