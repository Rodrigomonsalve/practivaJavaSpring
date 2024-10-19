package academy.digitalla.store.service_product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_categories")
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
 public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(Long id) {
		this.id = id;
	}

	public Category() {
	}
}

