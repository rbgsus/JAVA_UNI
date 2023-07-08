package fp.grados.excepciones;

public class ExcepcionGradoNoValido extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionGradoNoValido() {
		super();

	}

	public ExcepcionGradoNoValido(String msg) {
		super(msg);
	}

}
