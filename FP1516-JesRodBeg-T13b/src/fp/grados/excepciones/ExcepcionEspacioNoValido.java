package fp.grados.excepciones;

public class ExcepcionEspacioNoValido extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionEspacioNoValido() {
		super();

	}

	public ExcepcionEspacioNoValido(String msg) {
		super(msg);
	}
}
