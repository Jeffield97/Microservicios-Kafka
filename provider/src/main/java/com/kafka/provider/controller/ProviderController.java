package com.kafka.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    private   KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    public ProviderController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    @GetMapping("/{id}")
    public String obtenerUsuario(@PathVariable Long id) {
        // Aquí puedes implementar la lógica para obtener un usuario con el ID especificado
        kafkaTemplate.send("ms-provider", id.toString());
        return "Mensaje enviado a Kafka: " + id;
    }
}
