package com.javaup.cdi;

import com.javaup.control.CustomerFacade;
import com.javaup.control.PurchaseOrderFacade;
import com.javaup.modelo.Customer;
import com.javaup.modelo.PurchaseOrder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import javax.ejb.EJB;

import javax.inject.Inject;

/**
 * The main view contains a simple label element and a template element.
 */
@Route("customers")
@HtmlImport("frontend://styles/shared-styles.html")
public class MainView extends VerticalLayout {

    @Inject
    private MessageBean messageBean;
    
    @EJB
    private CustomerFacade customerFacade;
    
    @EJB
    private PurchaseOrderFacade purchaseOrderFacade;
    
    
    private final Grid<PurchaseOrder> gridPurchaseOrder;    
    private final Grid<Customer> gridCustomer;

    public MainView() {
        this.gridCustomer = new Grid<>(Customer.class);
        this.gridPurchaseOrder = new Grid<>(PurchaseOrder.class);
        
        
        HorizontalLayout hl = new HorizontalLayout();
        
        hl.add(gridCustomer,gridPurchaseOrder);
        hl.setWidthFull();
        
        
        
        
        setupGridCustomers();
        setupGridPurchaseOrder();
        
        
        
        
        Button button = new Button("Cargar Datos",
                event ->  {
                              Notification.show(messageBean.getMessage());
                              actulizarGridCustomer();
                        });
        
        gridCustomer.addItemClickListener((t) -> {
            Notification.show("Cliente "+ t.getItem().getName() + " Seleccionado.!"  );
            //Actualizar Las Ordenes del Cliente
            gridPurchaseOrder.setItems(purchaseOrderFacade.findByIdCustomer(t.getItem().getCustomerId()));
        });
        
        
        add(button,hl);
    }

    public void setupGridCustomers() {
        gridCustomer.removeAllColumns();
        gridCustomer.addColumn(Customer::getCustomerId).setHeader("Código");
        gridCustomer.addColumn(Customer::getName).setHeader("Nombre");
        gridCustomer.addColumn("discountCode.rate").setHeader("Rango");
        gridCustomer.setHeight("50em");
    }

    private void actulizarGridCustomer() {
        gridCustomer.setItems(customerFacade.findAll());
    }

    private void setupGridPurchaseOrder() {
        gridPurchaseOrder.removeAllColumns();
        gridPurchaseOrder.addColumn(PurchaseOrder::getFreightCompany).setHeader("Transporte");
        gridPurchaseOrder.addColumn("productId.description").setHeader("Producto");
        gridPurchaseOrder.setHeight("50em");
    }

}
