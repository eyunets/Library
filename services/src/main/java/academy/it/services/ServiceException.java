package academy.it.services;

public class ServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4558101711059140526L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
