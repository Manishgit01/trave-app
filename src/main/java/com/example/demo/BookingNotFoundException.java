package com.example.demo;

//FOR EXECPTION HANDLING
public class BookingNotFoundException extends RuntimeException 
{

    public BookingNotFoundException(String message) 
    {
        super(message);
    }

}