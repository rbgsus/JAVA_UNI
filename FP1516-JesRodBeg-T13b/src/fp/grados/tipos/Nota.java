package fp.grados.tipos;

public interface Nota extends Comparable<Nota> {
	public Asignatura getAsignatura();

	public Convocatoria getConvocatoria();

	public Double getValor();

	public Boolean getMencionHonor();

	public Integer getCursoAcademico();

	public Calificacion getCalificacion();

}
