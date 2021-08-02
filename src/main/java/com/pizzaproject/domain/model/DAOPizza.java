package com.pizzaproject.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "pizzamenutable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DAOPizza {

    @Id
    @NotEmpty(message = "This field is required")
    @Column(unique = true)
    private String slug;

    @NotEmpty(message = "This field is required")
    private String name;

    @NotNull(message = "This field is required")
    private Integer size;

    @DecimalMin(value="0.01", message = "testMinValue")
    @NotNull(message = "This field is required")
    private double price;

    private Instant date;
}
