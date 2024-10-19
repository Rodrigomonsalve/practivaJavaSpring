package academy.digitalla.store.service_product.repository;

import academy.digitalla.store.service_product.entity.Product;
import academy.digitalla.store.service_product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // HAY QUE RECORDAR QUE ESTO ES UN METODO DERIVADO, DE JPA.
    public List<Product> findByCategory(Category category);
}
