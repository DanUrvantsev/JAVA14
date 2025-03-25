package ru.netology.statistic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    @Test
    public void testRemoveExistingProduct() {
        ShopRepository repository = new ShopRepository();
        Product product = new Product(1, "Хлеб", 50);
        repository.add(product);
        repository.removeById(1);
        Product[] result = repository.findAll();
        assertEquals(0, result.length);
    }

    @Test
    public void testRemoveNonExistingProductThrowsException() {
        ShopRepository repository = new ShopRepository();
        repository.add(new Product(1, "Молоко", 80));
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> repository.removeById(2)
        );
        assertEquals("Element with id: 2 not found", exception.getMessage());
    }

    @Test
    public void testFindByIdExistingProduct() {
        ShopRepository repository = new ShopRepository();
        Product product = new Product(1, "Вода", 30);
        repository.add(product);
        Product found = repository.findById(1);
        assertEquals(product, found);
    }

    @Test
    public void testFindByIdNonExistingProduct() {
        ShopRepository repository = new ShopRepository();
        Product found = repository.findById(1);
        assertNull(found);
    }

    @Test
    void shouldAddProductSuccessfully() {

        ShopRepository repository = new ShopRepository();
        Product product = new Product(1, "Книга", 500);


        repository.add(product);


        Product[] products = repository.findAll();
        assertEquals(1, products.length);
        assertEquals(product, products[0]);
    }

    @Test
    void shouldThrowAlreadyExistsExceptionForDuplicateId() {

        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Ручка", 50);
        Product product2 = new Product(1, "Карандаш", 30);
        repository.add(product1);


        AlreadyExistsException exception = assertThrows(
                AlreadyExistsException.class,
                () -> repository.add(product2)
        );

        assertEquals(
                "Товар с ID 1 уже существует",
                exception.getMessage(),
                "Сообщение об ошибке не совпадает"
        );


        Product[] products = repository.findAll();
        assertEquals(1, products.length);
        assertEquals(product1, products[0]);
    }

}