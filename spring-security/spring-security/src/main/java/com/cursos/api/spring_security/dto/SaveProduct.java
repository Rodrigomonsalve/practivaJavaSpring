package com.cursos.api.spring_security.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaveProduct implements Serializable {

    /*{

        "name": "Celular",
            "price": 0.0,
            "categoryId":1
    }*/

    @NotBlank
    private String name;

    @DecimalMin(value="0.01", message="El precio debe ser mayor a cero")
    private BigDecimal price;


    /*
    SI FALLAN LAS VALIDACIONES DARA EL SIGUIENTE ERROR, SI NO HAY MANEJO DE EXCEPCIONES:
    {
        "timestamp": "2025-01-25T10:01:40.752+00:00",
            "status": 400,
            "error": "Bad Request",
            "path": "/api/v1/products"
    }*/

    /*SI FALLAN LAS VALIDACIONES DARA EL SIGUIENTE ERROR, CON NUESTRO MANEJO DE EXCEPCIONES:
    {
        "backendMessage": "T",
            "message": "Error en la peticion enviada",
            "timestamp": null,
            "url": "/api/v1/products",
            "method": "POST"
    }*/

    @Min(value = 1)
    private Long categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
