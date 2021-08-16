package com.rb.rbassignment.repository;

import com.rb.rbassignment.domain.Message;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRedisRepository extends CrudRepository<Message, String> {
        List<Message> findAllByChatroom_id(String chatRoom_id);
}
