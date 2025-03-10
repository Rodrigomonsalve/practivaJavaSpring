package academy.digitalla.store.service_product.service;

import academy.digitalla.store.service_product.entity.Product;
import academy.digitalla.store.service_product.repository.ProductRepository;
import academy.digitalla.store.service_product.entity.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{


   @Autowired
   private ProductRepository productRepository;

    //private final ProductRepository productRepository;  // SE DEBE DESCOMENTAR EN CASO DE QUE SE QUIERA PROBAR

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB=getProduct(product.getId());
        if(productDB== null){
            return null;
        }
        productDB.setName(product.getName());
        productDB.setDescription(product.getDescription());
        productDB.setCategory(product.getCategory());
        productDB.setPrice(product.getPrice());
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB=getProduct(id);
        if(productDB== null){
            return null;
        }
        productDB.setStatus("DELETED");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {

        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB=getProduct(id);
        if(productDB== null){
            return null;
        }
        Double stock=productDB.getStock() + quantity;
        productDB.setStock(stock);

        return productRepository.save(productDB);
    }

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }
}




















