package br.com.beautique.ms_sync.listeners.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.listeners.ListenerConfig;
import br.com.beautique.ms_sync.utils.SyncLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQListerConfig implements ListenerConfig {

    private final ObjectMapper objectMapper;

    public RabbitMQListerConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "customerQueue")
    @Override
    public void listenToCustomerQueue(String message) {
        try{
            CustomerDTO customer = objectMapper.readValue(message, CustomerDTO.class);
            SyncLogger.info("Message received from queue customerQueue: " + customer.toString());
        } catch (Exception e) {
            SyncLogger.error("Erro listen customer queue: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "appointmentQueue")
    @Override
    public void listenToAppointmentQueue(String message) {
        try {
            FullAppointmentDTO appointments = objectMapper.readValue(message, FullAppointmentDTO.class);
            SyncLogger.info("Message received from queue appointmentQueue: " + appointments.toString());
        } catch (Exception e) {
            SyncLogger.error("Error listen appointment queue: " + e.getMessage());
        }
    }

    @RabbitListener(queues = "beautyProcedureQueue")
    @Override
    public void listenToBeautyProcedureQueue(String message) {
        try{
            BeautyProcedureDTO beautyProcedures = objectMapper.readValue(message, BeautyProcedureDTO.class);
            SyncLogger.info("Message received from queue beautyProcedureQueue: " + beautyProcedures.toString());
        } catch (Exception e) {
            SyncLogger.info("Error listen beauty procedure queue: " + e.getMessage());
        }
    }
}
