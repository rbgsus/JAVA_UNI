package fp.grados.excepciones;

public class ExcepcionCentroNoValido extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionCentroNoValido() {
		super();
	}

	public ExcepcionCentroNoValido(String msg) {
		super(msg);
	}
}
