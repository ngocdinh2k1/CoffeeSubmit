package com.dev.product.Coffee.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "tbl_product_images")
@NoArgsConstructor
@AllArgsConstructor
public class ProductImagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    @Column(name = "created_date", nullable = true)
    private Date created_date;

    @Column(name = "updated_date", nullable = true)
    private Date updated_date;

    public ProductImagesEntity(String fileName, String fileType, byte[] data, Date created_date, Date updated_date) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductImagesEntity that = (ProductImagesEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1978343741;
    }
}
