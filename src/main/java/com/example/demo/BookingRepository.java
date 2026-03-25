package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findBySource(String source);
    List<Booking> findByDestination(String destination);
    
    Page<Booking> findBySourceContainingAndDestinationContaining (String source, String destination, Pageable pageable);

}
