package com.jkdev.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkdev.apirest.apirest.Entities.Product;
import com.jkdev.apirest.apirest.Repositories.ProductRepository;


/**
 * Endpoints
 */

@RestController
@RequestMapping("/products")
public class ProductController  {

    @Autowired    // Revisa e inyecta automaticamente el repository
    private ProductRepository productRepository;


    @GetMapping("/{id}")
    public Product obteneProductByID(@PathVariable Long id){
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("No se encontro el producto con el id: "+ id));
    }


    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    

    @PostMapping
    public Product createProduct(@RequestBody Product producto){
        return productRepository.save(producto);
    }


    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product detailProduct){

        Product producto = productRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontro el producto con el Id: " + id));

        producto.setNombre(detailProduct.getNombre());  // Los valores del formulario
        producto.setPrecio(detailProduct.getPrecio());

        return productRepository.save(producto);

    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){

        Product producto = productRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("No se encontro el producto con el Id: " + id));        
        productRepository.delete(producto);

        return "Producto eliminado";

    }



}
