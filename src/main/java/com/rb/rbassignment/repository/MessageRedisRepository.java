package com.rb.rbassignment.repository;

import com.rb.rbassignment.domain.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRedisRepository extends CrudRepository<Message, String> {
        List<Message> findAllByChatroomId(String chatRoom_id);
        Message findByChatroomId(String chatRoom_id);
}
