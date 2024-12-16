package com.sync.telegramlistener.controller;

import com.sync.telegramlistener.service.TelegramClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TelegramController {


    private final TelegramClientService telegramClientService;

    @GetMapping("/start-listener")
    public String startListener() {
        try {
            telegramClientService.start();
            return "Telegram listener started successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error starting Telegram listener.";
        }
    }

    @GetMapping("/stop-listener")
    public String stopListener() {
        try {
            telegramClientService.stop();
            return "Telegram listener stopped successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error stopping Telegram listener.";
        }
    }
}
