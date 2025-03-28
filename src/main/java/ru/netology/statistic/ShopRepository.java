package ru.netology.statistic;

public class ShopRepository {
    private Product[] products = new Product[0];


    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        System.arraycopy(current, 0, tmp, 0, current.length);
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Товар с ID " + product.getId() + " уже существует");
        }
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void removeById(int id) {
        Product product = findById(id);
        if (product == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        Product[] tmp = new Product[products.length - 1];
        int index = 0;
        for (Product p : products) {
            if (p.getId() != id) {
                tmp[index] = p;
                index++;
            }
        }
        products = tmp;
    }
}