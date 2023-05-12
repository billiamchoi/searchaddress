package com.dkbmc.searchadress.repository;

import com.dkbmc.searchadress.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
