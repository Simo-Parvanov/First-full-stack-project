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
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596405692/s3ulckg1awe41qaocsut.jpg",
                    "s3ulckg1awe41qaocsut",
                    "100-w1"),
            new Image("100-w1@evga2",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596408066/k6y2bgcf1qgrdcahtlea.jpg",
                    "k6y2bgcf1qgrdcahtlea",
                    "100-w1"),
            new Image("5750@dell",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596439878/q1tqtknzvczrjzeenkgg.png",
                    "q1tqtknzvczrjzeenkgg",
                    "5750"),
            new Image("5750@dell2",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596440062/t42wumpj5c6dcxwdmqll.jpg",
                    "t42wumpj5c6dcxwdmqll",
                    "5750"),
            new Image("240@deepcool",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596440314/lwmyvh3lb5yp2xacugqc.jpg",
                    "lwmyvh3lb5yp2xacugqc",
                    "240"),
            new Image("970evo@samsung",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596440482/x9e4mmqr104lre00zfed.jpg",
                    "x9e4mmqr104lre00zfed",
                    "970evo"),
            new Image("970evo@samsung2",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596440569/jelwysqjzcg3if2xvy47.jpg",
                    "jelwysqjzcg3if2xvy47",
                    "970evo"),
            new Image("9700k@i7",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596440655/wakqqxa7ewdprpby3t0k.jpg",
                    "wakqqxa7ewdprpby3t0k",
                    "9700k"),
            new Image("9700k@i7i",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596440720/nhgrbq2y5ylh9dyecz4k.jpg",
                    "nhgrbq2y5ylh9dyecz4k",
                    "9700k"),
            new Image("g531gt@asus2",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596440815/ifydnztgsvbx2oiwazb9.jpg",
                    "ifydnztgsvbx2oiwazb9",
                    "g531gt"),
            new Image("g531gt@asus3",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596440897/iaw0yehkiynfsedqel4m.jpg",
                    "iaw0yehkiynfsedqel4m",
                    "g531gt"),
            new Image("g531gt@asus",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596440930/jdzqsqlu7z9pe7ejrr06.jpg",
                    "jdzqsqlu7z9pe7ejrr06",
                    "g531gt"),
            new Image("rtx2080@geforce",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596440976/p0spb3hx8ojgq3alh9h0.jpg",
                    "p0spb3hx8ojgq3alh9h0",
                    "rtx2080"),
            new Image("rtx2080@geforce2",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596441044/ar9ij9njerdpgzkw46cg.jpg",
                    "ar9ij9njerdpgzkw46cg",
                    "rtx2080"),
            new Image("x570@gigabyte",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596441081/bqgkh8vs6ya8t5rfgome.png",
                    "bqgkh8vs6ya8t5rfgome",
                    "x570"),
            new Image("x570@gigabyte2",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596441146/vguhrjl87xofglmxnfob.jpg",
                    "vguhrjl87xofglmxnfob",
                    "x570"),
            new Image("z390@msi",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596441183/swjo2c8iosip5rzwafhr.jpg",
                    "swjo2c8iosip5rzwafhr",
                    "z390"),
            new Image("z390@msi2",
                    "https://res.cloudinary.com/dr7iltbog/image/upload/v1596441226/pll6kajkbufmzm2lkyk2.jpg",
                    "pll6kajkbufmzm2lkyk2",
                    "z390")
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
                    new Category(CategoryEnum.COMPONENTS, new SubcategoryTwo(CategoryEnumTwo.SSD))),
            new Product("Evga",
                    "100-w1",
                    "ASUS STRIX G G531GV I7-9750 8Gx2 RAM 1TB + 512G PCIE RTX2060 6GD6",
                    160,
                    150,
                    6,
                    true,
                    true,
                    new Category(CategoryEnum.COMPONENTS, new SubcategoryTwo(CategoryEnumTwo.POWER_SUPPLY)))
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
