package com.peyman.ticketing.dto.mapper;

import com.peyman.ticketing.dto.TicketingSystemRequest;
import com.peyman.ticketing.dto.TicketingSystemResponse;
import com.peyman.ticketing.model.TicketingSystem;

public class TicketingSystemMapper {
    public static TicketingSystem toEntity (TicketingSystemRequest ticketingSystemRequest){
        TicketingSystem ticketingSystem = new TicketingSystem();
              ticketingSystem.setName(ticketingSystemRequest.getName());
              return ticketingSystem;
    }
    public static TicketingSystemResponse mapperResponse (TicketingSystem ticketingSystem){
        return new TicketingSystemResponse(
                ticketingSystem.getId(),
                ticketingSystem.getName(),
                ticketingSystem.getApiKey(),
                ticketingSystem.getActive(),
                ticketingSystem.getCreated()
        );
    }

}
