/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javaup.control;

import com.javaup.modelo.PurchaseOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ricardo
 */
@Stateless
public class PurchaseOrderFacade extends AbstractFacade<PurchaseOrder> {

    @PersistenceContext(unitName = "com.javaup_demo_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PurchaseOrderFacade() {
        super(PurchaseOrder.class);
    }
    
    
    
    public List<PurchaseOrder> findByIdCustomer(Integer IdCustomer) {
       Query q = em.createNamedQuery("PurchaseOrder.findByIdCustomer");
        
       q.setParameter("IdCustomer", IdCustomer);
       
       return q.getResultList();
        
    }
    
}
