package academy.digitalla.store.service_product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "tbl_products")
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
//@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double stock;
    private Double price;
    private String status;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY) // fetch = FetchType.LAZY SIGNIFICA QUE Category SÓLO SE VA A CARGAR HASTA QUE SE NECESITE. NO LO VA A HACER DE FROMA INMEDIATA. OPTIMIZA RECURSOS. NUNCA ES OBLIGATORIO USARLO.
    @JoinColumn(name="category_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // SIEMPRE SE DEBE DE PONER SI HAY CARGA DIFERIDA. DE LO CONTRARIO ARROJA UNA EXCEPCION. ESTO SUCEDE PORQUE JACKSON AL QUERER SERIALIZAR ESTE OBJETO, SE ENCUETRA CON QUE CATEGORY ESTA VACIO, DEBIDO A LA CARGA DIFERIDA. SE INTENTA SERIALIZAR UN  OBJETO SIN QUE ESTE COMPLETO.
    private Category category;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public Product() {
	}

	public Product(Long id, String name, String description, Double stock, Double price, String status, Date createAt, Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.price = price;
		this.status = status;
		this.createAt = createAt;
		this.category = category;
	}
}
