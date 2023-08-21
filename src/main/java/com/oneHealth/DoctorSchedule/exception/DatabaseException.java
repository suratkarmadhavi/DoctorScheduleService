package com.oneHealth.DoctorSchedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Custom exception class for representing DatabaseException.
 * This exception is thrown when there is an issue related to database operations.
 * It is annotated with @ResponseStatus to set the HTTP status code to UNPROCESSABLE_ENTITY (422) when this exception is thrown.
 * @author Madhavi
 * @version 1.0
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class DatabaseException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String message)
	{
		super(message);
	}
}

