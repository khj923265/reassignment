package com.rb.rbassignment.repository;

import com.rb.rbassignment.domain.ChatRoom;
import org.springframework.data.repository.CrudRepository;

public interface ChatRoomRedisRepository extends CrudRepository<ChatRoom, String> {

}
