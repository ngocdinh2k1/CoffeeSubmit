package com.dev.product.Coffee.dto;

import com.dev.product.Coffee.entity.ProductImagesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode()
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImagesDTO {

    private String fileName;
    private String fileType;
    private String id;
    private Date createdDate;
    private Date updatedDate;

    public static ProductImagesDTO from(ProductImagesEntity productImagesEntity){
        ProductImagesDTO img = new ProductImagesDTO();
        img.setFileName(productImagesEntity.getFileName());
        img.setId(productImagesEntity.getId());
        img.setFileType(productImagesEntity.getFileType());
        img.setCreatedDate(productImagesEntity.getCreatedDate());
        img.setUpdatedDate(productImagesEntity.getUpdatedDate());
        return img;
    }
}