package com.exampleApp.purchaseService.controllers;

import com.exampleApp.purchaseService.entities.Product;
import com.exampleApp.purchaseService.entities.PurchaseOrder;
import com.exampleApp.purchaseService.exceptions.ProductNotFoundException;
import com.exampleApp.purchaseService.repositories.PurchaseOrderRepository;
import com.exampleApp.purchaseService.services.ProductService;
import com.exampleApp.purchaseService.services.PurchaseOrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/purchaseOrders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAll() {
        return new ResponseEntity<>(
            purchaseOrderService.getAll(),
            HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(
        @RequestBody PurchaseOrder purchaseOrder
    ) {
        Optional<Product> productOptional = productService.getByid(
            purchaseOrder.getProductId()
        );

        if (productOptional.isEmpty()) throw new ProductNotFoundException(
            String.format(
                "Product with id %s not found",
                purchaseOrder.getProductId()
            )
        );

        Product product = productOptional.get();
        if (product.getStock() - purchaseOrder.getQuantity() < 0) {
            throw new StockNotEnoughException(
                String.format(
                    "Product %s does not have %s items available: %s left",
                    product.getName(),
                    purchaseOrder.getQuantity(),
                    product.getStock()
                )
            );
        }

        product.setStock(product.getStock() - purchaseOrder.getQuantity());

        productService.updateProduct(product);

        return new ResponseEntity<>(
            purchaseOrderService.createPurchaseOrder(purchaseOrder),
            HttpStatus.CREATED
        );
    }
}
