package com.dkbmc.searchaddress.repository;

import com.dkbmc.searchaddress.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
