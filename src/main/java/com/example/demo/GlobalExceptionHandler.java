package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBookingNotFound(BookingNotFoundException ex) {
		
		//Added exception logging to track errors with Global Logging
	    log.error("Booking not found error: {}", ex.getMessage());

	    ErrorResponse error = new ErrorResponse(ex.getMessage(), 404);

	    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneric(Exception ex) {
//        return new ResponseEntity<>("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGeneric(Exception ex) {
//    	ex.printStackTrace(); 
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
    	
    	//Added exception logging to track errors with Global Logging
        log.error("Unexpected error occurred", ex);

        ErrorResponse error = new ErrorResponse("Something went wrong", 500);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
}