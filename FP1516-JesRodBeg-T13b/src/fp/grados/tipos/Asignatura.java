package fp.grados.tipos;

public interface Asignatura extends Comparable<Asignatura> {
	public String getNombre();

	public String getAcronimo();

	public String getCodigo();

	public Double getCreditos();

	public TipoAsignatura getTipo();

	public Integer getCurso();

	public Departamento getDepartamento();

	void setDepartamento(Departamento d);

}