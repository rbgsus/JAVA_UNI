package fp.grados.excepciones;

public class ExcepcionAlumnoNoValido extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionAlumnoNoValido() {
		super();
	}

	public ExcepcionAlumnoNoValido(String msg) {
		super(msg);
	}
}
