package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.models.ProductStock;
import com.shopallday.storage.domain.repository.products.ProductStockRepository;
import com.shopallday.storage.domain.repository.products.ProductsRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductStockData implements DataHelper {

    private final ProductsRepository productsRepository;
    private final ProductStockRepository productStockRepository;
    private final RepositoryManager repositoryManager;

    public ProductStockData(ProductsRepository productsRepository, ProductStockRepository productStockRepository, RepositoryManager repositoryManager) {
        this.productsRepository = productsRepository;
        this.productStockRepository = productStockRepository;
        this.repositoryManager = repositoryManager;
    }


    @Override
    public void create() throws Exception {

        List<Product> products = productsRepository.findAllProducts();

        List<ProductStock> productStocks = new ArrayList<>();
        // -- product_stock Q60C QLED 4K HDR
        productStocks.add(new ProductStock(
                null,
                products.get(0),
                10,
                "Small",
                "Black",
                1000f
        ));

        productStocks.add(new ProductStock(
                null, // productStockId will be auto-generated by the sequence
                products.get(0),
                10,
                "Large",
                "Black",
                1100f
        ));

        productStocks.add(new ProductStock(
                null, // productStockId will be auto-generated by the sequence
                products.get(0),
                10,
                "Large",
                "White",
                1100f
        ));

        // -- product_stock N85C Neo QLED 4K HDR
        productStocks.add(new ProductStock(
                null,
                products.get(1),
                10,
                "Small",
                "Black",
                1200f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(1),
                10,
                "Large",
                "Black",
                1300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(1),
                10,
                "XLarge",
                "White",
                1400f
        ));

        // -- product_stock 2023 QN90C Neo QLED 4K HDR Smart TV
        productStocks.add(new ProductStock(
                null,
                products.get(2),
                10,
                "Small",
                "Black",
                1200f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(2),
                10,
                "Large",
                "Black",
                1300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(2),
                10,
                "XLarge",
                "White",
                1400f
        ));

        // -- product_stock HP Slim S01-pF2012na Desktop PC
        productStocks.add(new ProductStock(
                null,
                products.get(3),
                10,
                "Small",
                "Black",
                1200f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(3),
                10,
                "Large",
                "Black",
                1300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(3),
                10,
                "XLarge",
                "White",
                2300f
        ));

        // -- product_stock HP Slim S01-pF2011na Desktop PC - Intel® Core™ i5
        productStocks.add(new ProductStock(
                null,
                products.get(4),
                10,
                "Small",
                "Black",
                1400f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(4),
                3,
                "Large",
                "Black",
                1500f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(4),
                3,
                "XLarge",
                "White",
                2400f
        ));

        // -- product_stock ACER Revo Box RN96 Desktop PC
        productStocks.add(new ProductStock(
                null,
                products.get(5),
                10,
                "Small",
                "Black",
                800f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(5),
                3,
                "Large",
                "Black",
                950f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(5),
                3,
                "XLarge",
                "White",
                981f
        ));

        // -- product_stock LENOVO IdeaPad 3i 15.6" Laptop - Intel® Core™ i3
        productStocks.add(new ProductStock(
                null,
                products.get(6), // Assuming products.get(6) corresponds to the product LENOVO IdeaPad 3i 15.6" Laptop - Intel® Core™ i3
                50,
                "Small",
                "Black",
                700f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(6), // Assuming products.get(6) corresponds to the product LENOVO IdeaPad 3i 15.6" Laptop - Intel® Core™ i3
                30,
                "Large",
                "Black",
                800f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(6), // Assuming products.get(6) corresponds to the product LENOVO IdeaPad 3i 15.6" Laptop - Intel® Core™ i3
                22,
                "XLarge",
                "White",
                1000f
        ));

        // -- product_stock APPLE MacBook Air 13.3" (2020) - M1
        productStocks.add(new ProductStock(
                null,
                products.get(7), // Assuming products.get(7) corresponds to the product APPLE MacBook Air 13.3" (2020) - M1
                50,
                "Small",
                "Black",
                700f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(7), // Assuming products.get(7) corresponds to the product APPLE MacBook Air 13.3" (2020) - M1
                30,
                "Large",
                "Black",
                800f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(7), // Assuming products.get(7) corresponds to the product APPLE MacBook Air 13.3" (2020) - M1
                22,
                "XLarge",
                "White",
                1000f
        ));

        // -- product_stock HP 15a-nb0502sa 15.6" Chromebook Plus - Intel® Core™ i3
        productStocks.add(new ProductStock(
                null,
                products.get(8), // Assuming products.get(8) corresponds to the product HP 15a-nb0502sa 15.6" Chromebook Plus - Intel® Core™ i3
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(8), // Assuming products.get(8) corresponds to the product HP 15a-nb0502sa 15.6" Chromebook Plus - Intel® Core™ i3
                30,
                "Large",
                "Black",
                400f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(8), // Assuming products.get(8) corresponds to the product HP 15a-nb0502sa 15.6" Chromebook Plus - Intel® Core™ i3
                22,
                "XLarge",
                "White",
                550f
        ));

        // -- product_stock SAMSUNG Galaxy S24 Ultra - 512 GB
        productStocks.add(new ProductStock(
                null,
                products.get(9), // Assuming products.get(9) corresponds to the product SAMSUNG Galaxy S24 Ultra - 512 GB
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(9), // Assuming products.get(9) corresponds to the product SAMSUNG Galaxy S24 Ultra - 512 GB
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(9), // Assuming products.get(9) corresponds to the product SAMSUNG Galaxy S24 Ultra - 512 GB
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(9), // Assuming products.get(9) corresponds to the product SAMSUNG Galaxy S24 Ultra - 512 GB
                22,
                "Large",
                "Blue",
                600f
        ));

        // -- product_stock APPLE iPhone 15
        productStocks.add(new ProductStock(
                null,
                products.get(10), // Assuming products.get(10) corresponds to the product APPLE iPhone 15
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(10), // Assuming products.get(10) corresponds to the product APPLE iPhone 15
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(10), // Assuming products.get(10) corresponds to the product APPLE iPhone 15
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(10), // Assuming products.get(10) corresponds to the product APPLE iPhone 15
                22,
                "Large",
                "Blue",
                600f
        ));


        // -- product_stock GOOGLE Pixel 7a
        productStocks.add(new ProductStock(
                null,
                products.get(11), // Assuming products.get(11) corresponds to the product GOOGLE Pixel 7a
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(11), // Assuming products.get(11) corresponds to the product GOOGLE Pixel 7a
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(11), // Assuming products.get(11) corresponds to the product GOOGLE Pixel 7a
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(11), // Assuming products.get(11) corresponds to the product GOOGLE Pixel 7a
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(11), // Assuming products.get(11) corresponds to the product GOOGLE Pixel 7a
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(11), // Assuming products.get(11) corresponds to the product GOOGLE Pixel 7a
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(11), // Assuming products.get(11) corresponds to the product GOOGLE Pixel 7a
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(11), // Assuming products.get(11) corresponds to the product GOOGLE Pixel 7a
                22,
                "XLarge",
                "White",
                900f
        ));

        // -- product_stock BOSCH Series 4 WGG04409GB
        productStocks.add(new ProductStock(
                null,
                products.get(12), // Assuming products.get(12) corresponds to the product BOSCH Series 4 WGG04409GB
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(12), // Assuming products.get(12) corresponds to the product BOSCH Series 4 WGG04409GB
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(12), // Assuming products.get(12) corresponds to the product BOSCH Series 4 WGG04409GB
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(12), // Assuming products.get(12) corresponds to the product BOSCH Series 4 WGG04409GB
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(12), // Assuming products.get(12) corresponds to the product BOSCH Series 4 WGG04409GB
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(12), // Assuming products.get(12) corresponds to the product BOSCH Series 4 WGG04409GB
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(12), // Assuming products.get(12) corresponds to the product BOSCH Series 4 WGG04409GB
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(12), // Assuming products.get(12) corresponds to the product BOSCH Series 4 WGG04409GB
                22,
                "XLarge",
                "White",
                900f
        ));

// -- product_stock PANASONIC NN-CT56JBBPQ
        productStocks.add(new ProductStock(
                null,
                products.get(13), // Assuming products.get(13) corresponds to the product PANASONIC NN-CT56JBBPQ
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(13), // Assuming products.get(13) corresponds to the product PANASONIC NN-CT56JBBPQ
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(13), // Assuming products.get(13) corresponds to the product PANASONIC NN-CT56JBBPQ
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(13), // Assuming products.get(13) corresponds to the product PANASONIC NN-CT56JBBPQ
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(13), // Assuming products.get(13) corresponds to the product PANASONIC NN-CT56JBBPQ
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(13), // Assuming products.get(13) corresponds to the product PANASONIC NN-CT56JBBPQ
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(13), // Assuming products.get(13) corresponds to the product PANASONIC NN-CT56JBBPQ
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(13), // Assuming products.get(13) corresponds to the product PANASONIC NN-CT56JBBPQ
                22,
                "XLarge",
                "White",
                900f
        ));

// -- product_stock BOSCH Series 2 HHF113BR0B
        productStocks.add(new ProductStock(
                null,
                products.get(14), // Assuming products.get(14) corresponds to the product BOSCH Series 2 HHF113BR0B
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(14), // Assuming products.get(14) corresponds to the product BOSCH Series 2 HHF113BR0B
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(14), // Assuming products.get(14) corresponds to the product BOSCH Series 2 HHF113BR0B
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(14), // Assuming products.get(14) corresponds to the product BOSCH Series 2 HHF113BR0B
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(14), // Assuming products.get(14) corresponds to the product BOSCH Series 2 HHF113BR0B
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(14), // Assuming products.get(14) corresponds to the product BOSCH Series 2 HHF113BR0B
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(14), // Assuming products.get(14) corresponds to the product BOSCH Series 2 HHF113BR0B
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(14), // Assuming products.get(14) corresponds to the product BOSCH Series 2 HHF113BR0B
                22,
                "XLarge",
                "White",
                900f
        ));

        // -- product_stock BOSCH Series 2 SMV2ITX18G
        productStocks.add(new ProductStock(
                null,
                products.get(15), // Assuming products.get(15) corresponds to the product BOSCH Series 2 SMV2ITX18G
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(15), // Assuming products.get(15) corresponds to the product BOSCH Series 2 SMV2ITX18G
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(15), // Assuming products.get(15) corresponds to the product BOSCH Series 2 SMV2ITX18G
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(15), // Assuming products.get(15) corresponds to the product BOSCH Series 2 SMV2ITX18G
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(15), // Assuming products.get(15) corresponds to the product BOSCH Series 2 SMV2ITX18G
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(15), // Assuming products.get(15) corresponds to the product BOSCH Series 2 SMV2ITX18G
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(15), // Assuming products.get(15) corresponds to the product BOSCH Series 2 SMV2ITX18G
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(15), // Assuming products.get(15) corresponds to the product BOSCH Series 2 SMV2ITX18G
                22,
                "XLarge",
                "White",
                900f
        ));

        // -- product_stock BOASTAD TV bench, black/oak veneer
        productStocks.add(new ProductStock(
                null,
                products.get(16), // Assuming products.get(16) corresponds to the product BOASTAD TV bench, black/oak veneer
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(16), // Assuming products.get(16) corresponds to the product BOASTAD TV bench, black/oak veneer
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(16), // Assuming products.get(16) corresponds to the product BOASTAD TV bench, black/oak veneer
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(16), // Assuming products.get(16) corresponds to the product BOASTAD TV bench, black/oak veneer
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(16), // Assuming products.get(16) corresponds to the product BOASTAD TV bench, black/oak veneer
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(16), // Assuming products.get(16) corresponds to the product BOASTAD TV bench, black/oak veneer
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(16), // Assuming products.get(16) corresponds to the product BOASTAD TV bench, black/oak veneer
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(16), // Assuming products.get(16) corresponds to the product BOASTAD TV bench, black/oak veneer
                22,
                "XLarge",
                "White",
                900f
        ));

        // -- product_stock HEMNES TV bench, black-brown/light brown
        productStocks.add(new ProductStock(
                null,
                products.get(17), // Assuming products.get(17) corresponds to the product HEMNES TV bench, black-brown/light brown
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(17), // Assuming products.get(17) corresponds to the product HEMNES TV bench, black-brown/light brown
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(17), // Assuming products.get(17) corresponds to the product HEMNES TV bench, black-brown/light brown
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(17), // Assuming products.get(17) corresponds to the product HEMNES TV bench, black-brown/light brown
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(17), // Assuming products.get(17) corresponds to the product HEMNES TV bench, black-brown/light brown
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(17), // Assuming products.get(17) corresponds to the product HEMNES TV bench, black-brown/light brown
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(17), // Assuming products.get(17) corresponds to the product HEMNES TV bench, black-brown/light brown
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(17), // Assuming products.get(17) corresponds to the product HEMNES TV bench, black-brown/light brown
                22,
                "XLarge",
                "White",
                900f
        ));

// -- product_stock LANEBERG Extendable table
        productStocks.add(new ProductStock(
                null,
                products.get(18), // Assuming products.get(18) corresponds to the product LANEBERG Extendable table
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(18), // Assuming products.get(18) corresponds to the product LANEBERG Extendable table
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(18), // Assuming products.get(18) corresponds to the product LANEBERG Extendable table
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(18), // Assuming products.get(18) corresponds to the product LANEBERG Extendable table
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(18), // Assuming products.get(18) corresponds to the product LANEBERG Extendable table
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(18), // Assuming products.get(18) corresponds to the product LANEBERG Extendable table
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(18), // Assuming products.get(18) corresponds to the product LANEBERG Extendable table
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(18), // Assuming products.get(18) corresponds to the product LANEBERG Extendable table
                22,
                "XLarge",
                "White",
                900f
        ));

        // -- product_stock LANEBERG Extendable table new
        productStocks.add(new ProductStock(
                null,
                products.get(19), // Assuming products.get(19) corresponds to the product LANEBERG Extendable table new
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(19), // Assuming products.get(19) corresponds to the product LANEBERG Extendable table new
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(19), // Assuming products.get(19) corresponds to the product LANEBERG Extendable table new
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(19), // Assuming products.get(19) corresponds to the product LANEBERG Extendable table new
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(19), // Assuming products.get(19) corresponds to the product LANEBERG Extendable table new
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(19), // Assuming products.get(19) corresponds to the product LANEBERG Extendable table new
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(19), // Assuming products.get(19) corresponds to the product LANEBERG Extendable table new
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(19), // Assuming products.get(19) corresponds to the product LANEBERG Extendable table new
                22,
                "XLarge",
                "White",
                900f
        ));

// -- product_stock LANEBERG Ceramic bath
        productStocks.add(new ProductStock(
                null,
                products.get(20), // Assuming products.get(20) corresponds to the product LANEBERG Ceramic bath
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(20), // Assuming products.get(20) corresponds to the product LANEBERG Ceramic bath
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(20), // Assuming products.get(20) corresponds to the product LANEBERG Ceramic bath
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(20), // Assuming products.get(20) corresponds to the product LANEBERG Ceramic bath
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(20), // Assuming products.get(20) corresponds to the product LANEBERG Ceramic bath
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(20), // Assuming products.get(20) corresponds to the product LANEBERG Ceramic bath
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(20), // Assuming products.get(20) corresponds to the product LANEBERG Ceramic bath
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(20), // Assuming products.get(20) corresponds to the product LANEBERG Ceramic bath
                22,
                "XLarge",
                "White",
                900f
        ));

        // -- product_stock LANEBERG Ceramic bath NEW
        productStocks.add(new ProductStock(
                null,
                products.get(21), // Assuming products.get(21) corresponds to the product LANEBERG Ceramic bath NEW
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(21), // Assuming products.get(21) corresponds to the product LANEBERG Ceramic bath NEW
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(21), // Assuming products.get(21) corresponds to the product LANEBERG Ceramic bath NEW
                50,
                "purple",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(21), // Assuming products.get(21) corresponds to the product LANEBERG Ceramic bath NEW
                30,
                "Large",
                "Green",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(21), // Assuming products.get(21) corresponds to the product LANEBERG Ceramic bath NEW
                22,
                "Large",
                "White",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(21), // Assuming products.get(21) corresponds to the product LANEBERG Ceramic bath NEW
                22,
                "Large",
                "Blue",
                600f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(21), // Assuming products.get(21) corresponds to the product LANEBERG Ceramic bath NEW
                22,
                "XLarge",
                "Blue",
                900f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(21), // Assuming products.get(21) corresponds to the product LANEBERG Ceramic bath NEW
                22,
                "XLarge",
                "White",
                900f
        ));

// -- product_stock LANEBERG Inset sink, 1 bowl
        productStocks.add(new ProductStock(
                null,
                products.get(22), // Assuming products.get(22) corresponds to the product LANEBERG Inset sink, 1 bowl
                50,
                "Small",
                "Black",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(22), // Assuming products.get(22) corresponds to the product LANEBERG Inset sink, 1 bowl
                50,
                "Small",
                "Red",
                300f
        ));

        productStocks.add(new ProductStock(
                null,
                products.get(22), // Assuming products.get(22) corresponds to the product LANEBERG Inset sink, 1 bowl
                50,
                "purple",
                "Red",
                300f
        ));
        productStockRepository.createProductStock(productStocks, repositoryManager);
    }

    @Override
    public void print() throws Exception {
        System.out.println("print GetAllProductStockUseCase called...");
        for(ProductStock productStock: productStockRepository.findAllProductStocks()) {
            System.out.println(productStock);
        }
        System.out.println("print GetAllProductStockUseCase finished");
    }
}
