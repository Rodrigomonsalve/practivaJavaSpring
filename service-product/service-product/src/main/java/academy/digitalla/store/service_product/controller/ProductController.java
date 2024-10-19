package academy.digitalla.store.service_product.controller;

import academy.digitalla.store.service_product.entity.Category;
import academy.digitalla.store.service_product.entity.Product;
import academy.digitalla.store.service_product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // localhost:8091/products?categoryId=1
    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId){

        List<Product> products=new ArrayList<>();

        if(null==categoryId){

            products=productService.listAllProduct();

            if(products.isEmpty()){

                return ResponseEntity.noContent().build();
            }

        }else{

            //products=productService.findByCategory(Category.builder().id(categoryId).build());

            products=productService.findByCategory(new Category(categoryId));

            if(products.isEmpty()){

                return ResponseEntity.notFound().build();
            }

        }

        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(products);
        }



    }

    //localhost:8091/products/1
    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){

        Product product = productService.getProduct(id);

        if(product==null){

            return ResponseEntity.notFound().build();
        } else{

            return ResponseEntity.ok(product);
        }



    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){

        Product productCreate=productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);
    }


    @PutMapping(value="/{id}")
    public ResponseEntity<Product>  updateProduct(@PathVariable("id") Long id, @RequestBody Product product){

        product.setId(id);

        Product productDB=productService.updateProduct(product);

        if(productDB==null){

            return ResponseEntity.notFound().build();
        }else {

            return ResponseEntity.ok(productDB);

        }

    }


    //localhost:8091/products/4
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){

        Product productDelete=productService.deleteProduct(id);

        if(productDelete==null){

            return ResponseEntity.notFound().build();

        } else{

            return ResponseEntity.ok(productDelete);

        }

    }

    //localhost:8091/3/stock?quantity=2
    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id, @RequestParam(name="quantity", required=true) Double quantity){

        Product product=productService.updateStock(id, quantity);

        if(product==null){

            return ResponseEntity.notFound().build();

        } else{

            return ResponseEntity.ok(product);
        }

    }

}
