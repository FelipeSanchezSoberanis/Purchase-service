package com.exampleApp.purchaseService.services;

import com.exampleApp.purchaseService.entities.Product;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ProductService {

    @Value("${services.productServiceURL}")
    private String productServiceURL;

    // public List<Product> getAll() {
    // return productRepository.findAll();
    // }

    public void updateProduct(Product product) {
        new RestTemplate()
            .exchange(
                String.format("%s/products", productServiceURL),
                HttpMethod.PUT,
                new HttpEntity<Product>(product),
                Product.class
            )
            .getBody();
    }

    public Optional<Product> getByid(Long id) {
        Product product;

        try {
            product =
                new RestTemplate()
                    .getForObject(
                        String.format("%s/products/%s", productServiceURL, id),
                        Product.class
                    );
        } catch (RestClientException e) {
            return Optional.empty();
        }

        return Optional.of(product);
    }
}
