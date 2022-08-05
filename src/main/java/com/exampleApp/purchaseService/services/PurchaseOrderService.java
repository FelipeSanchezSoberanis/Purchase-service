package com.exampleApp.purchaseService.services;

import com.exampleApp.purchaseService.entities.Product;
import com.exampleApp.purchaseService.entities.PurchaseOrder;
import com.exampleApp.purchaseService.repositories.PurchaseOrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }
}
