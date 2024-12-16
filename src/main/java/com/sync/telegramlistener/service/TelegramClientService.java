package com.sync.telegramlistener.service;

import it.tdlight.TelegramClient;
import it.tdlight.jni.TdApi;
import it.tdlight.ClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TelegramClientService {

    private TelegramClient client;
    private Thread clientThread;

    public void start()  {
        client = ClientFactory.create().createClient();

        clientThread = new Thread(() -> {
            client.initialize(this::onUpdate, this::onUpdateError, this::onError);
        });

        clientThread.start();
        log.info("Telegram client started.");
    }

    public void stop() {
        if (clientThread != null && clientThread.isAlive()) {
            clientThread.interrupt();
            log.info("Telegram client stopped.");
        } else {
            log.info("Telegram client is not running.");
        }
    }

    private void onUpdate(TdApi.Object update) {
        log.info("Received update: " + update);
    }

    private void onUpdateError(Throwable exception) {
        log.info("Error while processing update:");
        exception.printStackTrace();
    }

    private void onError(Throwable exception) {
        log.info("Error in Telegram client:");
        exception.printStackTrace();
    }
}
