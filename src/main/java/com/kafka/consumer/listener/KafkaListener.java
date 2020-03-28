package com.kafka.consumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.kafka.consumer.Entity.Book;

@Service
public class KafkaListener {


    private static final Logger log=LoggerFactory.getLogger(KafkaListener.class);

    @org.springframework.kafka.annotation.KafkaListener(topics = {"messageTopic","TestTopic"}, groupId = "group_json")
    public void consume(Book book){
        log.info("received Message - "+book);
    }

}
