package com.rb.rbassignment.service;

import com.rb.rbassignment.domain.ChatRoom;
import com.rb.rbassignment.domain.Message;
import com.rb.rbassignment.repository.ChatRoomRedisRepository;
import com.rb.rbassignment.repository.MessageRedisRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {

    private final ChatRoomRedisRepository chatRoomRedisRepository;
    private final MessageRedisRepository messageRedisRepository;

    public ChatService(ChatRoomRedisRepository chatRoomRedisRepository,
                            MessageRedisRepository messageRedisRepository) {
        this.chatRoomRedisRepository = chatRoomRedisRepository;
        this.messageRedisRepository = messageRedisRepository;
    }

    @Transactional
    public void createChatRoom(String title) {
        ChatRoom chatRoom = ChatRoom.builder()
                .title(title)
                    .build();
        chatRoom = chatRoomRedisRepository.save(chatRoom);
        Message message = Message.builder()
            .chatroom_id(chatRoom.getId())
            .build();
        messageRedisRepository.save(message);
    }

    public List<ChatRoom> getChatRoomList() {
        return (List<ChatRoom>) chatRoomRedisRepository.findAll();
    }

    public Optional<List<Message>> getMessageList(String id) {
        //TODO No property chatroom found for type Message! 해결
        return Optional.of(messageRedisRepository.findAllByChatroom_id(id));
    }
}
