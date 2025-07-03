package com.fazbear.backend.controller;

import com.fazbear.backend.model.Product;
import com.fazbear.backend.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    private final ProductRepository productRepository;

    // Inyección por constructor (más limpia que @Autowired)
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // GET /api/products - Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            logger.info("🍕 Obteniendo {} productos de Fazbear's Pizza", products.size());
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("💥 Error al obtener productos: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /api/products/{id} - Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if (product.isPresent()) {
                logger.info("🎯 Producto encontrado: {}", product.get().getName());
                return ResponseEntity.ok(product.get());
            } else {
                logger.warn("⚠️ Producto con ID {} no encontrado", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("💥 Error al buscar producto con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /api/products/category/{category} - Obtener productos por categoría
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        try {
            List<Product> products = productRepository.findAll().stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .toList();
            logger.info("📂 Encontrados {} productos en la categoría: {}", products.size(), category);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("💥 Error al buscar productos por categoría {}: {}", category, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // POST /api/products - Crear nuevo producto
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productRepository.save(product);
            logger.info("✅ Nuevo producto creado: {} - ${}", savedProduct.getName(), savedProduct.getPrice());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        } catch (Exception e) {
            logger.error("💥 Error al crear producto: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // PUT /api/products/{id} - Actualizar producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setName(productDetails.getName());
                product.setDescription(productDetails.getDescription());
                product.setPrice(productDetails.getPrice());
                product.setCategory(productDetails.getCategory());
                
                Product updatedProduct = productRepository.save(product);
                logger.info("🔄 Producto actualizado: {}", updatedProduct.getName());
                return ResponseEntity.ok(updatedProduct);
            } else {
                logger.warn("⚠️ No se pudo actualizar, producto con ID {} no encontrado", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("💥 Error al actualizar producto con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE /api/products/{id} - Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
                logger.info("🗑️ Producto con ID {} eliminado", id);
                return ResponseEntity.noContent().build();
            } else {
                logger.warn("⚠️ No se pudo eliminar, producto con ID {} no encontrado", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("💥 Error al eliminar producto con ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // GET /api/products/count - Obtener cantidad total de productos
    @GetMapping("/count")
    public ResponseEntity<Long> getProductCount() {
        try {
            long count = productRepository.count();
            logger.info("📊 Total de productos en Fazbear's Pizza: {}", count);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            logger.error("💥 Error al contar productos: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}