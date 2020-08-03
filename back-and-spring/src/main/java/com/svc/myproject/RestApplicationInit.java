package com.svc.myproject;

import com.svc.myproject.domain.entities.*;
import com.svc.myproject.repository.ImageRepository;
import com.svc.myproject.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RestApplicationInit implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    private static final List<Image> IMAGE_LIST = List.of(
            new Image("100-w1@evga",
                    "http://res.cloudinary.com/dr7iltbog/image/upload/v1596405692/s3ulckg1awe41qaocsut.jpg",
                    "s3ulckg1awe41qaocsut",
                    "100-w1"),
            new Image("100-w1@evga2",
                    "http://res.cloudinary.com/dr7iltbog/image/upload/v1596408066/k6y2bgcf1qgrdcahtlea.jpg",
                    "k6y2bgcf1qgrdcahtlea",
                    "100-w1")

    );
    private static final List<Product> PRODUCT_LIST = List.of(
            new Product("Dell",
                    "5750",
                    "In late 2019, a 17-inch Precision 5750 model was spotted on Geekbench with a Comet Lake-H CPU and a Quadro RTX 3000 GPU.",
                    2850,
                    3000,
                    5,
                    true,
                    true,
                    new Category(CategoryEnum.LAPTOP, new SubcategoryTwo(CategoryEnumTwo.GAME))),
            new Product("Asus",
                    "g531gt",
                    "ASUS STRIX G G531GV I7-9750 8Gx2 RAM 1TB + 512G PCIE RTX2060 6GD6",
                    1900,
                    1700,
                    11,
                    true,
                    true,
                    new Category(CategoryEnum.LAPTOP, new SubcategoryTwo(CategoryEnumTwo.GAME))),
            new Product("GeForce",
                    "rtx2080",
                    "ASUS STRIX G G531GV I7-9750 8Gx2 RAM 1TB + 512G PCIE RTX2060 6GD6",
                    1700,
                    1550,
                    9,
                    true,
                    true,
                    new Category(CategoryEnum.COMPONENTS, new SubcategoryTwo(CategoryEnumTwo.CPU))),
            new Product("Intel Core i7",
                    "9700k",
                    "ASUS STRIX G G531GV I7-9750 8Gx2 RAM 1TB + 512G PCIE RTX2060 6GD6",
                    1150,
                    880,
                    23,
                    true,
                    true,
                    new Category(CategoryEnum.COMPONENTS, new SubcategoryTwo(CategoryEnumTwo.GRAPHIC_CARD))),
            new Product("DeepCool",
                    "240",
                    "ASUS STRIX G G531GV I7-9750 8Gx2 RAM 1TB + 512G PCIE RTX2060 6GD6",
                    230,
                    210,
                    9,
                    true,
                    true,
                    new Category(CategoryEnum.COMPONENTS, new SubcategoryTwo(CategoryEnumTwo.MOTHERBOARD))),
            new Product("Gigabyte",
                    "x570",
                    "ASUS STRIX G G531GV I7-9750 8Gx2 RAM 1TB + 512G PCIE RTX2060 6GD6",
                    840,
                    800,
                    5,
                    true,
                    true,
                    new Category(CategoryEnum.COMPONENTS, new SubcategoryTwo(CategoryEnumTwo.MOTHERBOARD))),
            new Product("MSI",
                    "z390",
                    "ASUS STRIX G G531GV I7-9750 8Gx2 RAM 1TB + 512G PCIE RTX2060 6GD6",
                    910,
                    880,
                    3,
                    true,
                    true,
                    new Category(CategoryEnum.COMPONENTS, new SubcategoryTwo(CategoryEnumTwo.MOTHERBOARD))),
            new Product("Samsung",
                    "970evo",
                    "ASUS STRIX G G531GV I7-9750 8Gx2 RAM 1TB + 512G PCIE RTX2060 6GD6",
                    480,
                    450,
                    6,
                    true,
                    true,
                    new Category(CategoryEnum.COMPONENTS, new SubcategoryTwo(CategoryEnumTwo.SSD)))
    );

    public RestApplicationInit(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (imageRepository.count() == 0) {
            for (Image image : IMAGE_LIST) {
                imageRepository.saveAndFlush(image);
            }
        }
        if (productRepository.count() == 0) {
            for (Product product : PRODUCT_LIST) {
                productRepository.saveAndFlush(product);
            }
        }
    }
}
