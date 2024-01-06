package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.net.URL;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address>findByLocationUrl(URL locationUrl);
    Optional<Address>findByCode(String code);
    @Query(value = "SELECT MAX(CAST(SUBSTRING(code, 12) AS SIGNED)) FROM Address WHERE SUBSTRING(code, 1, 4) = :year", nativeQuery = true)
    Long findLatestSequenceNumber(@Param("year") String year);
}
