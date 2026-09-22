package csd230.lecture_2_1.AlessancoRodrigues;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testDatabaseConnectionAndQueries() {
        Product testProduct = new Product();
        testProduct.setName("Unit Test Item");
        testProduct.setDescription("Testing the DB connection");
        testProduct.setPrice(10.99);

        // Test Save
        productRepository.save(testProduct);

        // Test Find
        Product foundProduct = productRepository.findFirstByName("Unit Test Item");

        assertNotNull(foundProduct);
        assertEquals("Unit Test Item", foundProduct.getName());

        // Clean up database after test
        productRepository.delete(foundProduct);
    }
}