package com.tutorial.tutorial_webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;  // ESTA CLASE ES DE R2DBC  // jakarta.persistence.Id ---> La contraparte es JPA
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

// R2DBC NO UTILIZA LA ANOTACION ENTITY

@Table
@Data // GENERA LOS METODOS GETTERS Y SETTERS, EL METODO TOSTRING Y UN CONSTRUCTOR QUE INCLUYE SOLO LAS VARIALBLES DE TIPO FINAL
//@RequiredArgsConstructor   // GENERA UN CONSTRUCTOR QUE INCLUYE SOLO LAS VARIALBLES DE TIPO FINAL Y LOS CAMPOS MARCADOS COMO @NonNull
//@AllArgsConstructor        // GENERA UN CONSTRUCTOR QUE INCLUYE TODAS LAS VARIABLES DE LA CLASE
//@NoArgsConstructor         // GENERA UN CONSTRUCTOR VACIO
                             // SI USAS LAS 3 ANOTACIONES SE GENERAN 3 CONSTRUCTORES
public class Product {

    public Product(int id, String name, float price ) {
        this.price = price;
        this.name = name;
        this.id = id;
    }

    @Id
    private int id;

    private String name;

    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
