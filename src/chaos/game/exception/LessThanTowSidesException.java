package chaos.game.exception;

public class LessThanTowSidesException extends Exception {

	private static final long serialVersionUID = 6386817967737629659L;
	private final int sidesNumber;

	
	public LessThanTowSidesException(int sidesNumber) {
		super();
		this.sidesNumber = sidesNumber;
	}

	public LessThanTowSidesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int sidesNumber) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.sidesNumber = sidesNumber;
	}

	public LessThanTowSidesException(String message, Throwable cause, int sidesNumber) {
		super(message, cause);
		this.sidesNumber = sidesNumber;
	}

	public LessThanTowSidesException(String message, int sidesNumber) {
		super(message);
		this.sidesNumber = sidesNumber;
	}

	public LessThanTowSidesException(Throwable cause, int sidesNumber) {
		super(cause);
		this.sidesNumber = sidesNumber;
	}

	
	public int getSidesNumber() {
		return sidesNumber;
	}
	

	
}








