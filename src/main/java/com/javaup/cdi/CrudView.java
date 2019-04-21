/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaup.cdi;

import com.javaup.control.CustomerFacade;
import com.javaup.modelo.Customer;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.Collection;
import javax.ejb.EJB;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.impl.GridCrud;

/**
 *
 * @author Ricardo
 */
@Route("crud")
@HtmlImport("frontend://styles/shared-styles.html")
public class CrudView extends VerticalLayout{

    @EJB
    private CustomerFacade customerFacade;
    
    private final GridCrud<Customer> gridCustomer;
    
    
    public CrudView() {
         this.gridCustomer = new GridCrud<>(Customer.class);
         
         gridCustomer.setHeight("50em");
         
         gridCustomer.setCrudListener(new CrudListener<Customer>() {
             @Override
             public Collection<Customer> findAll() {
                 return customerFacade.findAll();
             }

             @Override
             public Customer add(Customer customer) {
                  customerFacade.create(customer);
                  return customer;
             }

             @Override
             public Customer update(Customer customer) {
                  customerFacade.edit(customer);
                  return customer;
             }

             @Override
             public void delete(Customer customer) {
                 customerFacade.remove(customer);
             }
});
         
                  
         
         add(gridCustomer);
        
        
    }
    
    
    
}
