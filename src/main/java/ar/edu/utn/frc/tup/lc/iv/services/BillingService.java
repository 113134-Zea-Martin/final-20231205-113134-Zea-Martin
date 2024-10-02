package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.model.Invoice;
import org.springframework.stereotype.Service;

@Service
public interface BillingService {

    Invoice billOrder(Long orderId);

    Invoice getInvoiceByNumber(String invoiceNumber);
}
