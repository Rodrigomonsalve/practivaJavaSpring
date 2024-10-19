package academy.digitalla.store.service_product.service;

import academy.digitalla.store.service_product.entity.Product;
import academy.digitalla.store.service_product.entity.Category;

import java.util.List;

public interface ProductService {

    public Product getProduct(Long id);

    public Product createProduct(Product product);

    public Product updateProduct(Product product);

    public Product deleteProduct(Long id);

    public List<Product> findByCategory(Category category);

    public Product updateStock(Long id, Double quantity);

    public List<Product> listAllProduct();


}
