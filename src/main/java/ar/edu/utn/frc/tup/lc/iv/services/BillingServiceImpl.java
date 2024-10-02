package ar.edu.utn.frc.tup.lc.iv.services;

import ar.edu.utn.frc.tup.lc.iv.dtos.OrderDTO;
import ar.edu.utn.frc.tup.lc.iv.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BillingServiceImpl implements BillingService {

    public Invoice billOrder(Long orderId) {
        OrderDTO order = getOrder(orderId);

        Customer customer = getCustomer(order.getCustomerId());
        Company company = getCompanyInfo();
        String letter = getLetter(customer);
        String invoiceNumber = getInvoiceNumber(letter);
        LocalDate invoiceDate = getInvoiceDate();
        LocalDate dueDate = getDueDate(invoiceDate, letter);
        List<Item> items = getInvoiceItems(order);
        BigDecimal subtotal = getSubtotal(items);
        Tax tax = getTaxes(items, customer);
        BigDecimal total = getTotal(subtotal, tax);
        InvoiceControl invoiceControl = getInvoiceControl(invoiceNumber, total);

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setCompany(company);
        invoice.setLetter(letter);
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setDate(invoiceDate);
        invoice.setDueDate(dueDate);
        invoice.setItems(items);
        invoice.setSubTotal(subtotal);
        invoice.setTax(tax);
        invoice.setTotal(total);
        invoice.setInvoiceControl(invoiceControl);

        return invoice;
    }

    @Override
    public Invoice getInvoiceByNumber(String invoiceNumber) {
        return null;
    }

    /**
     * Los datos de la orden de venta deben obtenerse a través de un servicio provisto por la aplicación existente.
     * @param orderId
     * @return
     */
    private OrderDTO getOrder(Long orderId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Los datos del cliente deben obtenerse a través de un servicio provisto por la aplicación existente.
     * @param customerId
     * @return
     */
    private Customer getCustomer(Long customerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Los datos de la empresa fueron provistos por la empresa contratante.
     * @return
     */
    private Company getCompanyInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * La letra de la factura debe ser A o B, dependiendo de la condición impositiva del cliente.
     * Si se trata de un cliente responsable inscripto, la factura debe ser A, en caso contrario
     * debe ser B.
     * @param customer
     * @return
     */
    private String getLetter(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * La empresa posee dos puntos de venta para generar numeros de facturas, uno para facturas
     * A y otro para facturas B. El punto de venta para facturas A es el 00001 y el punto de venta
     * para facturas B es el 00002.
     * Los números de facturas deben ser correlativos, es decir, si la ultima factura generada
     * para el punto de venta 00001 es la 00000001, la siguiente factura debe ser la 00000002.
     * @param letter
     * @return
     */
    private String getInvoiceNumber(String letter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * La fecha de la factura debe ser la fecha en la que se genera la factura, no la fecha de la orden de venta.
     * @return
     */
    private LocalDate getInvoiceDate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * En caso de tratarse de una factura A, la fecha de vencimiento debe ser de 10 días corridos
     * a partir de la fecha de la factura. En caso de tratarse de una factura B, la fecha de vencimiento
     * debe ser igual a la fecha de facturación.
     *
     * @param invoiceDate
     * @param letter
     * @return
     */
    private LocalDate getDueDate(LocalDate invoiceDate, String letter) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Los nombres y precios unitarios de los productos deben obtenerse a través de un servicio
     * provisto por la aplicación existente. La cantidad de productos a facturar vendrá en la orden de venta.
     * El precio total de cada producto debe calcularse como el precio unitario por la cantidad.
     * @param order
     * @return
     */
    private List<Item> getInvoiceItems(OrderDTO order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * El subtotal de los productos es la suma de los precios totales de cada producto.
     * @param items
     * @return
     */
    private BigDecimal getSubtotal(List<Item> items) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * El impuesto a aplicar es del 27% sobre el subtotal de los productos en caso de tratarse de una factura A.
     * En caso de tratarse de una factura B, debe aplicarse el 21% sobre el subtotal de los productos.
     * @param items
     * @return
     */
    private Tax getTaxes(List<Item> items, Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * El total de la factura es la suma del subtotal de los productos más los impuestos.
     *
     * @param subtotal
     * @param tax
     * @return
     */
    private BigDecimal getTotal(BigDecimal subtotal, Tax tax) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * La empresa ya esta integrada a los organismos de control para obtener el CAE (Código de Autorización Electrónico),
     * para esto ya se cuenta con un servicio de terceros que proveera el numero CAE en caso
     * de poder conectarse al organismo de control. Si por algún motivo no se puede obtener,
     * el sistemna debe utilizar el CAI (Código de Autorización de Impresión).
     * El número CAI provisto por la empresa es el 12345678901234567890.
     * @param invoiceNumber
     * @param total
     * @return
     */
    private InvoiceControl getInvoiceControl(String invoiceNumber, BigDecimal total) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
