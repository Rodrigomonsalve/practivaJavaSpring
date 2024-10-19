package academy.digitalla.store.service_product;

import academy.digitalla.store.service_product.entity.Category;
import academy.digitalla.store.service_product.entity.Product;
import academy.digitalla.store.service_product.repository.ProductRepository;
import academy.digitalla.store.service_product.service.ProductService;
import academy.digitalla.store.service_product.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

// LA ANOTACION @SpringBootTest  SIRVE PARA CONFIGURAR UN ENTORNO DE PRUEBA.
// SIRVE PARA REALIZAR PRUEBAS ATODO EL CODIGO , NO SOLO A RESPOSITORIOS E INTERACCIONES A BASES DE DATOS.
// POR LO TANTO, CARGA TODOS LOS COMPONENTES DE LA APLICACION Y NO SÓLO CARGA COMPONENTES NECESARIOS DE JPA, COMO Entidades, EntityManager, Repositorios. NO CARGA CONTROLADORES, NI SERVICIOS
@SpringBootTest
public class ProductServiceMockTest {

    // LA ANOTACION MOCK SE USA PARA CREAR OBJETOS SIMULADOS LLAMADOS MOCKS
    // UN MOCK SIRVE PARA CREAR OBJETOS AISLADOS DETODO EL COMPORTAMIENTO DE LA APP, PORQUE EL MOCK NO SE PRUEBA
    // POR EJEMPLO, NO VA A IMPORTAR SI HAY UNA BASE DE DATOS O NO, SI HAY DATOS EN ESA BASE, ETC. EL MOCK VA A SEGUIR FUNCIONANDO. NO ES NECESARIO TAMPOCO INICIALIZARLO.
    // ESTO, SIEMPRE Y CUANDO NO SE ESTE PROBANDO PRECISAMENTE ESE COMPORTAMIENTO. EN ESTE CASO, NO SE ESTAN PROBANDO CONEXIONES A BASES DE DATOS NI SU CONTENIDO. ESO SE HIZO EN ProductRepositoryMockTest
    // POR LO ANTERIOR, ANTES DE CREAR MOCKS, DEBEMOS IDENTIFICAR QUÉ SE ESTÁ PROBANDO EXACTAMENTE. EN NUESTRO CASO ES LA CLASE PRODUCTSERVICE
    // ¡LOS MOCKS NO SE ENCUENTRAN EN EL REPOSITORIO DE SPRING! IMPORTANTE TOMAR EN CUENTA PARA MAS ADELANTE.
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach // LA ANOTACION @BeforeEach SIRVE PARA QUE ESTE METODO SE EJECUTE SIEMPRE ANTES DE CUALQUIER PRUEBA(METODOS DECORADOS CON LA ANOTACION @Test)
    public void setUp(){

        MockitoAnnotations.initMocks(this); // SIRVE PARA INICIALIZAR UN MOCK

        // ENTENDER LA SIGUIENTE LINEA ES TRASCENDENTAL.
        // SI NOS FIJAMOS ESTAMOS PASANDO EL MOCK COMO PARÁMETRO. ESTO NO DEBERÌA SER NECESARIO SI INYECTAMOS COMO DEPENDENCIA (@Autowired) EN LA CLASE PRODUCTSERVICE LA CLASE PRODUCTREPOSITORY.
        // SIN EMBARGO, DEBEMOS HACER ESTO PORQUE LOS MOCKS NO SE ENCUENTRAN EN EL REPOSITORIO DE SPRING, POR LO QUE LA DEPENDENCIA INYECTADA NO LA ENCONTARIA.
        productService=new ProductServiceImpl(productRepository);

        /*Product computer= Product.builder()
                .name("computer")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("5"))
                .price(Double.parseDouble("12.5"))
                .status("Created")
                .createAt(new Date()).build();*/

        Product computer=new Product(1L,"computer","", 5.0, 12.5, "Created", new Date(), new Category(1L, "Electronic"));

        // LAS SIGUIENTES LINEAS SIRVEN PARA ORDENARLE A MOCKITO QUÉ OBJETO DEVOLVER CUANDO SE EJECUTE ALGUN METODO DE LOS MOCKS.
        // ESTO ES ASÍ, YA QUE, HAY QUE RECORDAR, LOS MOCKS SE ENCUETRAN AISLADOS, POR LO QUE HAY QUE ESPECIFICAR QUÈ DEVUELVEN.
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(computer)); // SE USA OPTIONAL PORQUE EL METODO findById, ORIGINALMENTE PUEDE NO DEVOLVER NADA.
        Mockito.when(productRepository.save(computer)).thenReturn(computer);

    }

    @Test
    public void whenValidGetID_ThenReturnProduct(){

        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }

    @Test
    public void whenValidUpdatesStock_ThenReturnProduct(){

        Product newStock = productService.updateStock(1L, Double.parseDouble("8"));
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    }


}
