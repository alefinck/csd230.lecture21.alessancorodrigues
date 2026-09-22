package csd230.lecture_2_1.AlessancoRodrigues;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final ProductRepository productRepository;

    public Application(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        System.out.println("Saving fake data to database...");

        // SAVE operations using Faker
        for (int i = 0; i < 5; i++) {
            Product p = new Product();
            p.setName(faker.commerce().productName());
            p.setDescription(faker.commerce().material());
            p.setPrice(Double.valueOf(faker.commerce().price()));
            productRepository.save(p);
        }

        // Saving specific items to test our custom queries
        Product laptop1 = new Product();
        laptop1.setName("Gaming Laptop");
        laptop1.setDescription("High performance");
        laptop1.setPrice(2000.00);
        productRepository.save(laptop1);

        Product laptop2 = new Product();
        laptop2.setName("Gaming Laptop");
        laptop2.setDescription("Medium performance");
        laptop2.setPrice(1500.00);
        productRepository.save(laptop2);

        // FIND operations
        System.out.println("Testing findFirstByName:");
        Product firstLaptop = productRepository.findFirstByName("Gaming Laptop");
        if (firstLaptop != null) {
            System.out.println("Found first: " + firstLaptop.getName() + " costs " + firstLaptop.getPrice());
        }

        System.out.println("Testing findAllByName:");
        List<Product> allLaptops = productRepository.findAllByName("Gaming Laptop");
        for (Product prod : allLaptops) {
            System.out.println("List item: " + prod.getName() + " with ID " + prod.getId());
        }

        // DELETE operation
        System.out.println("Testing delete operation:");
        if (firstLaptop != null) {
            productRepository.delete(firstLaptop);
            System.out.println("Deleted product with ID: " + firstLaptop.getId());
        }
    }
}