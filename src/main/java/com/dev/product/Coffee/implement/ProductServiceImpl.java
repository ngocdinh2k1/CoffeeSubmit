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
    public ProductEntity createProduct(ProductEntity product, MultipartFile productAvatar) throws Exception {
//        List<ImageEntity> img = new ArrayList<ImageEntity>();
//        ImageEntity imageEntity = null;
//        String downloadURI = "";
//        if(!productImagesService.isEmptyUploadFile(productAvatar)){
//            String pathToFile = UPLOAD_FOLDER_ROOT + "product/avatar/" + productAvatar.getOriginalFilename();
//            productAvatar.transferTo(new File(pathToFile));
//            imageEntity = imageService.saveImage(productAvatar);
//            downloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("/download/")
//                    .path(imageEntity.getId())
//                    .toUriString();
//            img.add(imageEntity);
//            product.setImageEntity(img);
//            product.setFileImage("product/avatar/" + productAvatar.getOriginalFilename());
//        }

//        if(!productImagesService.isEmptyUploadFile(productPictures)){
//
//            for (MultipartFile pic:
//                    productPictures) {
//                pic.transferTo(new File(UPLOAD_FOLDER_ROOT + "product/pictures" + pic.getOriginalFilename()));
//
//                ProductImagesEntity pi = new ProductImagesEntity();
//                pi.setDownloadURL("product/pictures/" + pic.getOriginalFilename());
//                pi.setPathName(pic.getOriginalFilename());
//
//
//                product.addProductImage(pi);
//            }
//
//        }

        product.setCreated_date(new Date());
        product.setSeo(new Slugify().slugify(product.getTitle()));
//        product.setC

        productRepository.save(product);

        return product;
    }

    @Override
    public ProductEntity create(ProductEntity productEntity, CategoriesEntity categoriesEntity) {
        productEntity.setCreated_date(new Date());
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
        ProductEntity productEntity = productRepository.findById(id).get();
        return productEntity;
    }

    @Override
    public boolean deleteProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        productRepository.delete(productEntity);
        return true;
    }

    @Override
    public ProductEntity updateProductById(Long id, ProductEntity product) {
        ProductEntity productEntity = productRepository.findById(id).get();
        productEntity.setTitle(product.getTitle());
        productEntity.setDetailsDescription(product.getDetailsDescription());
        productEntity.setSeo(product.getSeo());
        productEntity.setCreated_date(product.getCreated_date());
        productEntity.setPrice(product.getPrice());
        productEntity.setPriceSale(product.getPriceSale());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setShortDescription(product.getShortDescription());
        productEntity.setCreated_by(product.getCreated_by());
        productEntity.setUpdated_by(product.getUpdated_by());
        productEntity.setUpdated_date(new Date());
        productEntity.setStatus(product.getStatus());
        productEntity.setCategoriesEntity(productEntity.getCategoriesEntity());

        productRepository.save(productEntity);
        return productEntity;
    }


}
