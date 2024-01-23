package com.example.streamapi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // ТОВАРЫ

        Product product1 = new Product(1L, "Мастер и Маргарита", "Books",
                new BigDecimal("25"));
        Product product2 = new Product(2L, "1984", "Books",
                new BigDecimal("65"));
        Product product3 = new Product(3L, "Юла", "Toys",
                new BigDecimal("350"));
        Product product4 = new Product(4L, "Робот", "Toys",
                new BigDecimal("250"));
        Product product5 = new Product(5L, "Памперсы", "Children's products",
                new BigDecimal("95"));
        Product product6 = new Product(6L, "Агуша", "Children's products",
                new BigDecimal("150"));

        // ЗАКАЗЫ

        Order order1 = new Order(1L, LocalDate.of(2021, 2, 15),
                LocalDate.of(2021, 2, 25), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product4);
                }}
        );

        Order order2 = new Order(2L, LocalDate.of(2021, 4, 5),
                LocalDate.of(2021, 4, 15), "Доставлен",
                new HashSet<>() {{
                    add(product2);
                    add(product3);
                }}
        );

        Order order3 = new Order(3L, LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23), "Доставлен",
                new HashSet<>() {{
                    add(product5);
                    add(product6);
                }}
        );

        Order order4 = new Order(4L, LocalDate.of(2021, 3, 9),
                LocalDate.of(2021, 3, 19), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product4);
                }}
        );

        Order order5 = new Order(5L, LocalDate.of(2021, 5, 1),
                LocalDate.of(2021, 5, 11), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product6);
                }}
        );

        Order order6 = new Order(6L, LocalDate.of(2021, 4, 16),
                LocalDate.of(2021, 4, 26), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product5);
                }}
        );

        Order order7 = new Order(7L, LocalDate.of(2021, 3, 9),
                LocalDate.of(2021, 3, 19), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product4);
                }}
        );

        Order order8 = new Order(8L, LocalDate.of(2021, 2, 25),
                LocalDate.of(2021, 3, 5), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product3);
                }}
        );

        Order order9 = new Order(9L, LocalDate.of(2021, 3, 18),
                LocalDate.of(2021, 3, 28), "Доставлен",
                new HashSet<>() {{
                    add(product2);
                    add(product5);
                }}
        );

        Order order10 = new Order(10L, LocalDate.of(2021, 3, 18),
                LocalDate.of(2021, 3, 28), "Доставлен",
                new HashSet<>() {{
                    add(product3);
                    add(product2);
                }}
        );

        Order order11 = new Order(11L, LocalDate.of(2021, 2, 11),
                LocalDate.of(2021, 2, 21), "Доставлен",
                new HashSet<>() {{
                    add(product2);
                    add(product1);
                }}
        );

        Order order12 = new Order(12L, LocalDate.of(2021, 3, 5),
                LocalDate.of(2021, 3, 15), "Доставлен",
                new HashSet<>() {{
                    add(product3);
                    add(product2);
                }}
        );

        Order order13 = new Order(13L, LocalDate.of(2021, 2, 12),
                LocalDate.of(2021, 2, 22), "Доставлен",
                new HashSet<>() {{
                    add(product5);
                    add(product3);
                }}
        );

        Order order14 = new Order(14L, LocalDate.of(2021, 3, 18),
                LocalDate.of(2021, 3, 28), "Доставлен",
                new HashSet<>() {{
                    add(product3);
                    add(product2);
                }}
        );

        Order order15 = new Order(15L, LocalDate.of(2021, 4, 13),
                LocalDate.of(2021, 4, 23), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product6);
                }}
        );

        Order order16 = new Order(16L, LocalDate.of(2021, 4, 12),
                LocalDate.of(2021, 4, 22), "Доставлен",
                new HashSet<>() {{
                    add(product2);
                    add(product6);
                }}
        );

        Order order17 = new Order(17L, LocalDate.of(2021, 3, 11),
                LocalDate.of(2021, 3, 21), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product6);
                }}
        );

        Order order18 = new Order(18L, LocalDate.of(2021, 2, 13),
                LocalDate.of(2021, 2, 23), "Доставлен",
                new HashSet<>() {{
                    add(product3);
                    add(product5);
                }}
        );

        Order order19 = new Order(19L, LocalDate.of(2021, 4, 23),
                LocalDate.of(2021, 5, 3), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product6);
                }}
        );

        Order order20 = new Order(20L, LocalDate.of(2021, 3, 16),
                LocalDate.of(2021, 3, 26), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product6);
                }}
        );

        Order order21 = new Order(21L, LocalDate.of(2021, 3, 13),
                LocalDate.of(2021, 3, 23), "Доставлен",
                new HashSet<>() {{
                    add(product2);
                    add(product6);
                }}
        );

        Order order22 = new Order(22L, LocalDate.of(2021, 2, 13),
                LocalDate.of(2021, 2, 23), "Доставлен",
                new HashSet<>() {{
                    add(product1);
                    add(product6);
                }}
        );

        Order order23 = new Order(23L, LocalDate.of(2021, 4, 3),
                LocalDate.of(2021, 4, 13), "Доставлен",
                new HashSet<>() {{
                    add(product2);
                    add(product3);
                }}
        );

        Order order24 = new Order(24L, LocalDate.of(2021, 2, 11),
                LocalDate.of(2021, 2, 21), "Доставлен",
                new HashSet<>() {{
                    add(product5);
                    add(product6);
                }}
        );

        Order order25 = new Order(25L, LocalDate.of(2021, 3, 15),
                LocalDate.of(2021, 4, 20), "Доставлен",
                new HashSet<>() {{
                    add(product4);
                    add(product2);
                }}
        );

        // Список пользователей

        List<Customer> customers = new ArrayList<>() {{
            add(new Customer(1L, "Олег", 5L, new HashSet<>() {{
                add(order1);
                add(order2);
                add(order3);
                add(order4);
                add(order5);
            }}));

            add(new Customer(2L, "Игорь", 1L, new HashSet<>() {{
                add(order6);
                add(order7);
                add(order8);
                add(order9);
                add(order10);
            }}));

            add(new Customer(3L, "Степан", 5L, new HashSet<>() {{
                add(order11);
                add(order12);
                add(order13);
                add(order14);
                add(order15);
            }}));

            add(new Customer(4L, "Валерия", 6L, new HashSet<>() {{
                add(order16);
                add(order17);
                add(order18);
                add(order19);
                add(order20);
            }}));

            add(new Customer(5L, "Григорий", 2L, new HashSet<>() {{
                add(order21);
                add(order22);
                add(order23);
                add(order24);
                add(order25);
            }}));
        }};

        // Список продуктов

        List<Product> products = Arrays.asList(product1, product2, product3, product4, product5, product6);

        // Список заказов

        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5, order6, order7, order8,
                order9, order10, order11, order12, order13, order14, order15, order16, order17, order18,
                order19, order20, order21, order22, order23, order24, order25);


        // ЗАДАЧА 1

        List<Product> products100price = products.stream()
                .filter(price -> price.getPrice().intValue() > 100).toList();


        // ЗАДАЧА 2

        List<Product> childrenProducts = products.stream()
                .filter(category -> category.getCategory().equals("Children's products")).toList();


        // ЗАДАЧА 3

        double discount = products.stream().filter(category -> category.getCategory().equals("Toys"))
                .mapToDouble(discounts -> discounts.getPrice().doubleValue() - (discounts.getPrice().doubleValue() * 0.1)).sum();

        // ЗАДАЧА 4

        List<Product> productsInDate = customers.stream().filter(customer -> customer.getLevel() == 2L).flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().getMonthValue() >= 2 && order.getOrderDate().getMonthValue() < 4)
                .flatMap(product -> product.getProducts().stream()).toList();

        // ЗАДАЧА 5

        List<Product> relevant = products.stream().filter(category -> category.getCategory().equals("Books"))
                .sorted(Comparator.comparing(Product::getCategory)).limit(2).toList();

        // ЗАДАЧА 6

        List<Order> lastOrders = orders.stream().sorted((a, b) -> b.getOrderDate()
                .compareTo(a.getOrderDate())).limit(3).toList();

        // ЗАДАЧА 7

        List<Product> orderInDate = orders.stream().filter(date -> date.getOrderDate()
                        .isEqual(LocalDate.of(2021, 3, 15))).peek(id -> System.out.println(id.getId()))
                .flatMap(order -> order.getProducts().stream()).toList();


        // ЗАДАЧА 8

        int sumInDate = orders.stream().filter(date -> date.getOrderDate().getMonthValue() == 2 && date.getOrderDate().getYear() == 2021)
                .flatMap(order -> order.getProducts().stream()).mapToInt(price -> price.getPrice().intValue()).sum();

        // ЗАДАЧА 9

        double averageInDate = orders.stream().filter(date -> date.getOrderDate()
                        .isEqual(LocalDate.of(2021, 3, 15)))
                .flatMap(product -> product.getProducts().stream())
                .mapToDouble(price -> price.getPrice().doubleValue()).average().getAsDouble();

        // ЗАДАЧА 10

        int sum = products.stream().filter(product -> product.getCategory().equals("Books"))
                .mapToInt(price -> price.getPrice().intValue()).sum();
        int max = products.stream().filter(product -> product.getCategory().equals("Books"))
                .mapToInt(price -> price.getPrice().intValue()).max().getAsInt();
        int min = products.stream().filter(product -> product.getCategory().equals("Books"))
                .mapToInt(price -> price.getPrice().intValue()).min().getAsInt();
        double average = products.stream().filter(product -> product.getCategory().equals("Books"))
                .mapToDouble(price -> price.getPrice().doubleValue()).average().getAsDouble();
        long count = products.stream().filter(product -> product.getCategory().equals("Books")).count();

        // ЗАДАЧА 11

        Map<Long, Integer> mapProductCount = orders.stream()
                .collect(Collectors.toMap(Order::getId, value -> value.getProducts().size()));

        // ЗАДАЧА 12

        Map<Customer, List<Order>> mapOrdersList = customers.stream()
                .collect(Collectors.toMap(customer -> customer,
                        customer -> customer.getOrders().stream().toList()));

        // ЗАДАЧА 13

        Map<Order, Double> mapProductSum = orders.stream()
                .collect(Collectors.toMap(order -> order,
                        product -> product.getProducts().stream()
                                .mapToDouble(price -> price.getPrice().doubleValue()).sum()));

        // ЗАДАЧА 14

        Map<String, List<String>> mapProductName = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.mapping(Product::getName, Collectors.toList())));

        // ЗАДАЧА 15

        Map<String, Product> map5 = products.stream()
                .collect(Collectors.toMap(
                        Product::getCategory,
                        Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(Product::getPrice))));
    }
}
