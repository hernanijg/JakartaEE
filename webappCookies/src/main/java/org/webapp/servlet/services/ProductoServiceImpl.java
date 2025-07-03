package org.webapp.servlet.services;

import org.webapp.servlet.models.Product;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImpl implements ProductService {

    @Override
    public List<Product> show() {
        return Arrays.asList(new Product(1L, "notebook", "computacion", 12334),
                new Product(2L, "notebook1", "computacion1", 1233124),
                new Product(3L, "notebook2", "computacion2", 1212134334));
    }
}
