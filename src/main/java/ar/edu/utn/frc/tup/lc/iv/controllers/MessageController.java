package ar.edu.utn.frc.tup.lc.iv.controllers;

import ar.edu.utn.frc.tup.lc.iv.dtos.OrderMessageDTO;
import ar.edu.utn.frc.tup.lc.iv.model.Invoice;
import ar.edu.utn.frc.tup.lc.iv.dtos.MessageDTO;
import ar.edu.utn.frc.tup.lc.iv.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private BillingService billingService;

    @PostMapping()
    public ResponseEntity<Invoice> getOrderMessage(@RequestBody MessageDTO<OrderMessageDTO> message) {
        return ResponseEntity.ok(billingService.billOrder(message.getPayload().getOrderId()));
    }

}
