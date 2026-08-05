package com.peyman.ticketing.dto.mapper;

import com.peyman.ticketing.dto.SubSystemRequest;
import com.peyman.ticketing.dto.SubSystemResponse;
import com.peyman.ticketing.model.SubSystem;
import com.peyman.ticketing.service.TicketingSystemService;

public class SubSystemMapper {
    public static SubSystem toEntity(SubSystemRequest subSystemRequest){
        SubSystem subSystem= new SubSystem();
        subSystem.setName(subSystemRequest.getName());
        subSystem.setPrefix(subSystemRequest.getPrefix());
        subSystem.setDescription(subSystemRequest.getDescription());
        return subSystem;
    }
    public static SubSystemResponse toResponse(SubSystem subSystem) {
        return new SubSystemResponse(
                subSystem.getId(),
                subSystem.getName(),
                subSystem.getDescription(),
                subSystem.getPrefix(),
                subSystem.getIsActive(),
                subSystem.getTicketingSystem().getId(),
                subSystem.getCreated()
        );
    }

}
