package com.kafka.consumer.Configuration;

import com.kafka.consumer.Entity.Book;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfiguration {


    @Bean
    public ConsumerFactory<String,Book> consumerFactory(){
        Map<String,Object> configurations= new HashMap<>();
        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        configurations.put(ConsumerConfig.GROUP_ID_CONFIG,"group_id");
        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<String,Book>(configurations, new StringDeserializer(),new JsonDeserializer<>(Book.class));
    }



    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Book> kafkaListenerConatinerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Book> factory= new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
