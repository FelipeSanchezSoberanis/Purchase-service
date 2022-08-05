package com.exampleApp.purchaseService.repositories;

import com.exampleApp.purchaseService.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository
    extends JpaRepository<PurchaseOrder, Long> {}
