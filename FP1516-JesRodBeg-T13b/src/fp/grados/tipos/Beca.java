package fp.grados.tipos;

public interface Beca extends Comparable<Beca> {

	public String getCodigo();

	public Double getCuantiaTotal();

	public Integer getDuracion();

	public TipoBeca getTipo();

	public Double getCuantiaMensual(); 

	public void setCuantiaTotal(Double cuantiaTotal);

	public void setDuracion(Integer duracion);

}
