package com.rb.rbassignment.dto;

import lombok.Builder;

public class ChatRoomDto {
    private String id;
    private String title;

    @Builder
    public ChatRoomDto(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
