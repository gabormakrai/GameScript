package gs.exception;

public class CompileException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CompileException(String message, Throwable exception) {
		super(message, exception);
	}
	
}
