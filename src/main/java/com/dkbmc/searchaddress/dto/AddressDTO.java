package com.dkbmc.searchaddress.dto;

import com.dkbmc.searchaddress.domain.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class AddressDTO {

    private Long id;
    private Integer zipCode;
    private String roadNameAddress;
    private String landLotNumberAddress;
    private String detailedAddress;

    @Getter
    @NoArgsConstructor
    public static class RequestSave{
        private Long id;
        private String zipCode;
        private String roadNameAddress;
        private String landLotNumberAddress;
        private String detailedAddress;

        @Builder
        public RequestSave(Long id, String zipCode, String roadNameAddress, String landLotNumberAddress, String detailedAddress){
            this.id = id;
            this.zipCode = zipCode;
            this.roadNameAddress = roadNameAddress;
            this.landLotNumberAddress = landLotNumberAddress;
            this.detailedAddress = detailedAddress;
        }

        public Address toEntity(){
            return Address.builder()
                    .id(id)
                    .zipCode(zipCode)
                    .roadNameAddress(roadNameAddress)
                    .landLotNumberAddress(landLotNumberAddress)
                    .detailedAddress(detailedAddress)
                    .build();
        }
    }
}
