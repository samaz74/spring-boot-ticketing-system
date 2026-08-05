package com.peyman.ticketing.dto.mapper;

import com.peyman.ticketing.dto.MessageRequest;
import com.peyman.ticketing.dto.MessageResponse;
import com.peyman.ticketing.model.Message;

public class MessageMapper {
    public static Message toEntity(MessageRequest messageRequest){
        Message message = new Message();
        message.setContent(messageRequest.getContent());
        return message;
    };
    public static MessageResponse mapMessageResponse(Message message){
        return new MessageResponse(
                message.getId(),
                message.getContent(),
                message.getCreatedAt(),
                message.getSentBy().getFirstName() + " " + message.getSentBy().getLastName()
        );
    }
}
