package fp.grados.excepciones;

public class ExcepcionDespachoNoValido extends RuntimeException {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionDespachoNoValido() {
		super();
	}

	public ExcepcionDespachoNoValido(String msg) {
		super(msg);
	}

}
