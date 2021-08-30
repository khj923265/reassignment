package com.rb.rbassignment.controller;

import com.rb.rbassignment.domain.ChatRoom;
import com.rb.rbassignment.domain.Message;
import com.rb.rbassignment.service.ChatService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/create")
    public String createChatRoom(String title) {
        chatService.createChatRoom(title);
        return "redirect:/chat";
    }

    @GetMapping("")
    public String chatPage(Model model) {
        List<ChatRoom> chatRoomList = chatService.getChatRoomList();
        model.addAttribute("chatRoom", chatRoomList);
        return "chat/chat";
    }

    @GetMapping("/detail")
    public String detailPage(@RequestParam("title") String title,
                                @RequestParam("id") String id, Model model) {
        List<Message> messageList = chatService.getMessageList(id);
        model.addAttribute("chatroomId", messageList.get(0).getChatroomId());
        model.addAttribute("message", messageList);
        model.addAttribute("title", title);

        return "chat/detail";
    }

    @PostMapping("/message")
    @ResponseBody
    public void insertMessage(@RequestBody Message message) {

        System.out.println("message : " + message);

    }

    @GetMapping("/test")
    public String test(Model model) {
        List<ChatRoom> chatRoomList = chatService.getChatRoomList();
        model.addAttribute("chatRoom", chatRoomList);
        return "membership/membership";
    }

}
