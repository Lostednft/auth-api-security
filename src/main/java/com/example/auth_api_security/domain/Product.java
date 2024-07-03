package com.example.auth_api_security.domain;
import com.example.auth_api_security.dtos.product.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer price;

    public Product(ProductRequestDTO productRequestDTO)
    {
        this.name = productRequestDTO.name();
        this.price = productRequestDTO.price();
    }

}
