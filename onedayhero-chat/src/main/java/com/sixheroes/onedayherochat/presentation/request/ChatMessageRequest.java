package com.sixheroes.onedayherochat.presentation.request;

import com.sixheroes.onedayheromongo.chat.ChatMessageType;
import com.sixheroes.onedayheromongo.chat.request.MongoChatMessage;
import lombok.Builder;

@Builder
public record ChatMessageRequest(
        Long chatRoomId,
        Long senderId,
        ChatMessageType messageType,
        String senderNickName,
        String message
) {

    public MongoChatMessage toMongo() {
        return MongoChatMessage.builder()
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .senderNickName(senderNickName)
                .message(message)
                .build();
    }

    public static ChatMessageRequest createLeaveMessage(
            ChatMessageRequest request,
            String message
    ) {
        return ChatMessageRequest.builder()
                .chatRoomId(request.chatRoomId)
                .senderId(request.senderId)
                .messageType(request.messageType)
                .senderNickName(request.senderNickName)
                .message(message)
                .build();
    }
}
