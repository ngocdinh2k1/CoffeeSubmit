package com.dev.product.Coffee.implement;

import com.dev.product.Coffee.entity.CategoriesEntity;
import com.dev.product.Coffee.entity.ImageEntity;
import com.dev.product.Coffee.entity.ProductEntity;
import com.dev.product.Coffee.repository.ImageRepository;
import com.dev.product.Coffee.repository.ProductRepository;
import com.dev.product.Coffee.service.ImageService;
import com.dev.product.Coffee.service.ProductImagesService;
import com.dev.product.Coffee.service.ProductService;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    
    public String UPLOAD_FOLDER_ROOT = "D:/apiImage/";
    
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final ImageRepository imageRepository;
    @Autowired
    private ProductImagesService productImagesService;
    @Autowired
    private ImageService imageService;
    
    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }
    
    
    @Override
    public ProductEntity createProduct(ProductEntity product, MultipartFile productAvatar, CategoriesEntity categoriesEntity) throws Exception {
        
        
        imageService.saveImage(productAvatar, product);
        product.setCategoriesEntity(categoriesEntity);
        product.setCreatedDate(new Date());
        product.setSeo(new Slugify().slugify(product.getTitle()));
        
        productRepository.save(product);
        
        return product;
    }
    
    @Override
    public ProductEntity create(ProductEntity productEntity, CategoriesEntity categoriesEntity) {
        productEntity.setCreatedDate(new Date());
        productEntity.setSeo(new Slugify().slugify(productEntity.getTitle()));
        productEntity.setCategoriesEntity(categoriesEntity);
        productRepository.save(productEntity);
        return productEntity;
    }
    
    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public ProductEntity getProductById(Long id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        ProductEntity productEntity = null;
        if (productEntityOptional.isPresent()) {
            productEntity = productEntityOptional.get();
        }
        return productEntity;
    }
    
    @Override
    public boolean deleteProduct(Long id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        ProductEntity productEntity = null;
        if (productEntityOptional.isPresent()) {
            productEntity = productEntityOptional.get();
            productRepository.delete(productEntity);
            return true;
        }
        
        return false;
    }
    
    @Override
    public ProductEntity updateProductById(Long id, ProductEntity product) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        ProductEntity productEntity = null;
        if (productEntityOptional.isPresent()) {
            productEntity = productEntityOptional.get();
            productEntity.setTitle(product.getTitle());
            productEntity.setDetailsDescription(product.getDetailsDescription());
            productEntity.setSeo(product.getSeo());
            productEntity.setCreatedDate(product.getCreatedDate());
            productEntity.setPrice(product.getPrice());
            productEntity.setPriceSale(product.getPriceSale());
            productEntity.setQuantity(product.getQuantity());
            productEntity.setShortDescription(product.getShortDescription());
            productEntity.setCreatedBy(product.getCreatedBy());
            productEntity.setUpdatedBy(product.getUpdatedBy());
            productEntity.setUpdatedDate(new Date());
            productEntity.setStatus(product.getStatus());
            productEntity.setCategoriesEntity(productEntity.getCategoriesEntity());
            productRepository.save(productEntity);
        }
        
        return productEntity;
    }
}
