package fp.grados.excepciones;

public class ExcepcionProfesorNoValido extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionProfesorNoValido() {
		super();
	}

	public ExcepcionProfesorNoValido(String msg) {
		super(msg);
	}
}
