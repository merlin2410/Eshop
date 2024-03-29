package com.Merlin.eshop.service.impl;

import com.Merlin.eshop.Enum.ProductStatus;
import com.Merlin.eshop.dto.RequestDto.ProductRequestDto;
import com.Merlin.eshop.dto.ResponseDto.ProductResponseDto;
import com.Merlin.eshop.exception.ProductOutOfStockException;
import com.Merlin.eshop.exception.SellerNotFoundException;
import com.Merlin.eshop.models.Item;
import com.Merlin.eshop.models.Product;
import com.Merlin.eshop.models.Seller;
import com.Merlin.eshop.repository.ProductRepository;
import com.Merlin.eshop.repository.SellerRepository;
import com.Merlin.eshop.service.ProductService;
import com.Merlin.eshop.transformers.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public String addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        Seller seller = sellerRepository.findById(productRequestDto.getSellerId()).get();
        if(seller==null){
            throw new SellerNotFoundException("Seller Not Found!");
        }
        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setProductStatus(ProductStatus.AVAILABLE);
        product.setSeller(seller);
        seller.getProductList().add(product);
        sellerRepository.save(seller);

        return "Product added successfully";
    }

    @Override
    public List<ProductResponseDto> getProductsOfGivenCategory(String category) {
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product: productList){
            category = category.toUpperCase();
            if(category.equals(product.getCategory().toString())){
                productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
            }
        }
        return productResponseDtoList;
    }

    @Override
    public void decreaseQuantity(Item item) throws Exception {
        Product product = item.getProduct();
        if(item.getRequiredQuantity()>product.getAvailableQuantity()){
            throw new ProductOutOfStockException("Product is Out of Stock!");
        }
        product.setAvailableQuantity(product.getAvailableQuantity()-item.getRequiredQuantity());
        if(product.getAvailableQuantity()==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }

    }

    @Override
    public List<ProductResponseDto> getProductsOfGivenCategoryLessThanPrice(String category, int price) {
        List<ProductResponseDto> productResponseDtoList = getProductsOfGivenCategory(category);
        List<ProductResponseDto> productResponseDtoLessThanPriceList = new ArrayList<>();
        for(ProductResponseDto productResponseDto: productResponseDtoList){
            if(productResponseDto.getPrice()<price){
                productResponseDtoLessThanPriceList.add(productResponseDto);
            }
        }
        return productResponseDtoLessThanPriceList;
    }

    @Override
    public List<ProductResponseDto> getAllProductsBySeller(String sellerEmail) throws Exception {
        Seller seller;
        try {
            seller = sellerRepository.findByEmail(sellerEmail);
        }
        catch (Exception e){
            throw new SellerNotFoundException("Invalid email/ Seller Not Found");
        }
        List<Product> productList = seller.getProductList();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Product product: productList){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtoList;
    }
}
