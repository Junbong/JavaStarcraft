package com.bong.starcraft.game.exception;


/**
 * Created by bong on 15. 6. 15.
 */
public class NotProperTribeException extends RuntimeException {
	public NotProperTribeException(Throwable cause) {
		super(cause);
	}



	public NotProperTribeException() {
		super();
	}



	public NotProperTribeException(String message) {
		super(message);
	}



	public NotProperTribeException(String message, Throwable cause) {
		super(message, cause);
	}
}
