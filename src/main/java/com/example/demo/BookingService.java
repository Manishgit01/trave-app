package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class BookingService 
{

    @Autowired
    private BookingRepository repo;
    
    
    private Booking convertToEntity(BookingDTO dto) 
    {
        Booking booking = new Booking();
        booking.setName(dto.getName());
        booking.setSource(dto.getSource());
        booking.setDestination(dto.getDestination());
        return booking;
    }

    public Booking saveBooking(BookingDTO dto) 
    {
    	Booking booking = convertToEntity(dto);
        return repo.save(booking);
    }

    public List<Booking> getAllBookings() 
    {
        return repo.findAll();
    }

    public List<Booking> getBySource(String source) 
    {
        return repo.findBySource(source);
    }

    public List<Booking> getByDestination(String destination)
    {
        return repo.findByDestination(destination);
    }

    public String deleteBooking(int id) 
    {
        if(!repo.existsById(id)) 
        {
        	//FOR EXECPTION HANDLING
        	throw new BookingNotFoundException("Booking not found with id:" + id);
        }
        repo.deleteById(id);
        return "Booking Deleted Successfully";
    }
    
    public Page<Booking> getAllBookings(Pageable pageable) 
    {
        return repo.findAll(pageable);
    }
    
    public Page<Booking> searchBookings(String source, String destination, Pageable pageable) 
    {

        // Handle null → convert to empty string (so "Containing" works)
        String src = (source != null) ? source : "";
        String dest = (destination != null) ? destination : "";

        return repo.findBySourceContainingAndDestinationContaining(src, dest, pageable);
    }
    
}