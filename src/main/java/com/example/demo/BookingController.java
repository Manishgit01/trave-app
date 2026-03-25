package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import jakarta.validation.Valid;

@RestController
public class BookingController
{

    @Autowired
    private BookingService service;
    
    // GET API
    @GetMapping("/bookings/source/{source}")
    public List<Booking> getBySource(@PathVariable String source) 
    {
        return service.getBySource(source);
    }
    
    @GetMapping("/bookings/destination/{destination}")
    public List<Booking> getByDestination(@PathVariable String destination)
    {
    	return service.getByDestination(destination);    	
    }
    
    @GetMapping("/bookings/search")
    public Page<Booking> searchBookings(
            @RequestParam(required = false) String source,
            @RequestParam(required = false) String destination,
            @PageableDefault(size = 5, sort = "id") Pageable pageable) 
    {
    	//Manual logging
    	log.info("Search request received: source={}, destination={}", source, destination);

        Page<Booking> result = service.searchBookings(source, destination, pageable);

        log.info("Search result size: {}", result.getNumberOfElements());

        return result;
    }
    
    // POST API
    @PostMapping("/book")
    public Booking createBooking(@RequestBody BookingDTO dto) 
    {
    	// Manual logging
    	log.info("Creating booking for name={}", dto.getName());

        return service.saveBooking(dto);
    }
    	
    @PutMapping("/book")
    public Booking updateBooking(@RequestBody BookingDTO dto) 
    {
    	return service.saveBooking(dto);    
    }
    
    @DeleteMapping("/book/{id}")
    public String deleteBooking(@PathVariable int id) 
    {
    	return service.deleteBooking(id);
    	//return "Booking Deleted Successfully";
    }
    
    // 👇 ADD THIS NEW API (Pagination + Sorting)
    @GetMapping("/bookings")
    public org.springframework.data.domain.Page<Booking> getAllBookings(
            org.springframework.data.domain.Pageable pageable) 
    {
        return service.getAllBookings(pageable);
    }
    
    // To work "http://localhost:8080/Manish"
    @GetMapping("/")
    public String home() 
    {
        return "Welcome Manish!";
    }
    
    //Creates logger object used to print (info, error, debug) from controller,
    //Creates a permanent logger for this class to print logs
    private static final Logger log = LoggerFactory.getLogger(BookingController.class);
}
