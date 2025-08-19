package br.com.beautique.ms_sync.services.impl;

import br.com.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.com.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.com.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.com.beautique.ms_sync.services.AppointmentService;
import br.com.beautique.ms_sync.services.BeautyProcedureService;
import br.com.beautique.ms_sync.services.CustomerService;
import br.com.beautique.ms_sync.services.SyncService;
import br.com.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SyncServiceImpl implements SyncService {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private BeautyProcedureService beautyProcedureService;

    @Autowired
    private CustomerService customerService;

    @Override
    public void syncCustomer(CustomerDTO customer) {

        try{
            SyncLogger.info("Saving customer: " + customer.getId());
            customerService.saveCustomer(customer);
            SyncLogger.info("Updating appointment customer: " + customer.getId());
            appointmentService.updateAppointmentCustomer(customer);

        } catch (Exception e) {
            SyncLogger.error("Error saving customer: " + customer.getId());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }

    }

    @Override
    public void syncAppointment(FullAppointmentDTO appointment) {
        try{
            SyncLogger.info("Saving appointment: " + appointment.getId());
            appointmentService.saveAppointment(appointment);

        } catch (Exception e) {
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncBeautyProcedures(BeautyProcedureDTO beautyProcedure) {
        try{
            SyncLogger.info("Saving beauty procedure: " + beautyProcedure.getId());
            beautyProcedureService.saveBeautyProcedure(beautyProcedure);
            SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedure.getId());
            appointmentService.updateAppointmentBeautyProcedures(beautyProcedure);

        } catch (Exception e) {
            SyncLogger.error("Error saving beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
