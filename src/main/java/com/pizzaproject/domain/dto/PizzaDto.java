package com.pizzaproject.domain.dto;

import lombok.Data;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class PizzaDto {

    @NotEmpty(message = "Name : This field is required")
    private String name;

    @NotEmpty(message = "Slug : This field is required")
    private String slug;

    @NotNull(message = "Size : This field is required")
    private Integer size;

    @DecimalMin(value="0.01", message = "Price : Minimal price is 0.01")
    @NotNull(message = "Price : This field is required")
    private Double price;

    //private LocalDateTime date;
    private Instant date;
}
