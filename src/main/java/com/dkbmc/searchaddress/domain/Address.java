package com.dkbmc.searchaddress.domain;

import lombok.Builder;
import lombok.Getter;
import javax.persistence.*;

@Getter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name= "road_name_address", nullable = false, length = 5000)
    private String roadNameAddress;

    @Column(name="land_lot_number_address", nullable = false, length = 5000)
    private String landLotNumberAddress;

    @Column(name="detailed_address", nullable = false, length = 5000)
    private String detailedAddress;

    @Builder
    public Address(Long id, String zipCode, String roadNameAddress, String landLotNumberAddress, String detailedAddress){
        this.id = id;
        this.zipCode = zipCode;
        this.roadNameAddress = roadNameAddress;
        this.landLotNumberAddress = landLotNumberAddress;
        this.detailedAddress = detailedAddress;
    }
}
