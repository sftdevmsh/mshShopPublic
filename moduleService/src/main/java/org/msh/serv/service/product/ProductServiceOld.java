//package org.msh.service;
//
//import lombok.SneakyThrows;
//import org.msh.entity.oneToMany.Product;
//import org.msh.repository.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProductServiceOld {
//
//    private final ProductRepository productRepository;
//
//    @Autowired
//    public ProductServiceOld(ProductRepository productRepository)
//    {
//        this.productRepository = productRepository;
//    }
//
//    public List<Product> getAllSrvProduct()
//    {
//        return productRepository.getAllProduct();
//    }
//
//    public Product getByIdSrvProduct(Long id)
//    {
//        validationModelId(id);
//        return productRepository.getByIdProduct(id);
//    }
//
//    public void deleteSrvProduct(Long id)
//    {
//        validationModelId(id);
//        productRepository.deleteProduct(id);
//    }
//
//    public void addSrvProduct(Product product)
//    {
//        validationModelProduct(product);
//        productRepository.addProduct(product);
//    }
//
//    public void updateSrvProduct(Product product) {
//        validationModelId(product.getId());
//        validationModelProduct(product);
//        productRepository.updateProduct(product);
//    }
//
//
//
//    //region private methods
//    @SneakyThrows
//    private void validationModelId(Long id)
//    {
//        if(id == null || id<=0)
//            throw new Exception("Error! validationModelId _ wrong id");
//    }
//    @SneakyThrows
//    private void validationModelProduct(Product product)
//    {
//        if(product == null)
//            throw new Exception("Error! validationModelProduct _ null product");
//        if(product.getBrand() == null || product.getBrand().isEmpty())
//            throw new Exception("Error! validationModelProduct _ wrong product Brand");
//    }
//   //endregion
//}
