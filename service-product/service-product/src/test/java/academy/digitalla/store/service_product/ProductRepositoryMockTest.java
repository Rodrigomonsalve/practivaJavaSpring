package academy.digitalla.store.service_product;

import academy.digitalla.store.service_product.entity.Category;
import academy.digitalla.store.service_product.entity.Product;
import academy.digitalla.store.service_product.repository.ProductRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;  // org.junit.jupiter.api. ES LA LIBRERÍA PRINCIPAL DE JUNIT5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

// LA ANOTACION @DataJpaTest  SIRVE PARA CONFIGURAR UN ENTORNO DE PRUEBA
// SIRVE PARA REALIZAR PRUEBAS A RESPOSITORIOS E INTERACCIONES A BASES DE DATOS.
// POR LO TANTO, SÓLO CARGA COMPONENTES NECESARIOS DE JPA, COMO Entidades, EntityManager, Repositorios. NO CARGA CONTROLADORES, NI SERVICIOS
// UNA VEZ EJECUTADAS LAS PRUEBAS, LAS TRANSACCIONES REALIZADAS SE DESHACEN
@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;


//LA ANOTACION @Test SIRVE PARA CREAR METODOS DE PRUEBA.
// SIN ESTA ANOTACION NO SE PUEDEN EJECUTAR PRUEBAS
    @Test
    public void whenFindCategory_thenReturnListProduct(){  // SI NOS FIJAMOS, EL NOMBRE DE LOS METODOS DEBE SER MUY DESCRIPTIVO.

        /*Product product01= Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("1240.99"))
                .status("Created")
                .createAt(new Date()).build();*/

        // EL BUILDER ANTERIOR SUSTITUYE A LA SIGUIENTE FORMA DE  CREAR UN OBJETO
        // SE DEBE USAR LA ANOTACION @Builder EN LA ENTIDAD.
        // SIRVE PARA CREAR INSTANCIAS EN CLASES DE PRUEBA
        Product product01=new Product(1L,"computer","", 1.0, 1240.99, "Created", new Date(), new Category(1L, "Electronic"));



        productRepository.save(product01);
        List<Product> found =productRepository.findByCategory(product01.getCategory());

        // ESTA ES LA LINEA QUE SE VA A PROBAR
        // ASSERTIONS ES UNA CLASE QUE SE USA EN PRUEBAS UNITARIAS PARA SABER SI UNA CONDICION SE CUMPLE.
        //AL EJECUTAR LA PRUEBA TE PUEDE DAR:
        //1) Test passed
        //2) Test failed - Puede que tu metodo de prueba sea erroneo, que tu condicion sea erronea o, efectivamente, que tu codigo este mal por errores en tiempo de ejecuciòn no comprobadas, o errores en su logica que arrojen un resultado no esperado.
        //3) Te arroje excepciones porque haya errores de compilacion, ya sea, por errores en el metodo de prueba o errores en alguna parte de tu còdigo. - No permite ni siquiera ejecutar las pruebas.
        Assertions.assertThat(found.size()).isEqualTo(2);

    }
}
