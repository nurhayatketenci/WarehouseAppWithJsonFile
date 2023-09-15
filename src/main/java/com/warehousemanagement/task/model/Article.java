package com.warehousemanagement.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="articles")
public class Article {
    @Id
    @JsonProperty("art_id")
    private Long artId;

    @Column(name = "amount_of")
    @JsonProperty("amount_of")
    private int amountOf;

    @ManyToMany(mappedBy = "articles")
    @JsonIgnore
    private List<Product> products;

}
