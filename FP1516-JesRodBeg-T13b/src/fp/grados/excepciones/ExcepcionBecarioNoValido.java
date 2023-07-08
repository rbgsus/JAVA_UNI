package fp.grados.excepciones;

public class ExcepcionBecarioNoValido extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionBecarioNoValido() {
		super();
	}

	public ExcepcionBecarioNoValido(String msg) {
		super(msg);
	}
}
