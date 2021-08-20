package com.rb.rbassignment.service;

import com.rb.rbassignment.domain.ChatRoom;
import com.rb.rbassignment.domain.Message;
import com.rb.rbassignment.repository.ChatRoomRedisRepository;
import com.rb.rbassignment.repository.MessageRedisRepository;

import java.time.LocalTime;
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
                .sendTime(LocalTime.now())
                .chatroomId(chatRoom.getId())
                .content("채팅방이 생성되었습니다.")
                .sender("관리자")
                .build();
        messageRedisRepository.save(message);
    }

    public List<ChatRoom> getChatRoomList() {
        return (List<ChatRoom>) chatRoomRedisRepository.findAll();
    }

    public List<Message> getMessageList(String id) {
        return messageRedisRepository.findAllByChatroomId(id);
    }

    public void insertMessage(String chatRoomId, String content) {
        Message message = Message.builder()
                .chatroomId(chatRoomId)
                .content(content)
                .sendTime(LocalTime.now())
                .sender("손님")
                .build();
        messageRedisRepository.save(message);
    }
}
