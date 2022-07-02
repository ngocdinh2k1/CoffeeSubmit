package com.dev.product.Coffee.entity;

import com.dev.product.Coffee.dto.SaleOrderProductsDTO;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tbl_saleorder_products")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrderProductsEntity extends BaseEntity {

    private String title;
    private Long quantity;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "saleOrder_id")
    private SaleOrderEntity saleOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    public static SaleOrderProductsEntity from(SaleOrderProductsDTO saleOrderProductsDTO) {
        SaleOrderProductsEntity saleOrderEntity = new SaleOrderProductsEntity();
        saleOrderEntity.setId(saleOrderProductsDTO.getId());
        saleOrderEntity.setTitle(saleOrderProductsDTO.getTitle());
        saleOrderEntity.setPrice(saleOrderProductsDTO.getPrice());
        saleOrderEntity.setQuantity(saleOrderProductsDTO.getQuantity());
        saleOrderEntity.setCreated_date(saleOrderProductsDTO.getCreated_date());
        saleOrderEntity.setCreated_by(saleOrderProductsDTO.getCreated_by());
        saleOrderEntity.setUpdated_date(saleOrderProductsDTO.getUpdated_date());
        saleOrderEntity.setUpdated_by(saleOrderProductsDTO.getCreated_by());

        return saleOrderEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SaleOrderProductsEntity that = (SaleOrderProductsEntity) o;

        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 945026533;
    }
}
