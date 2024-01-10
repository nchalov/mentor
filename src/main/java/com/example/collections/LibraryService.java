package com.example.collections;

import java.util.List;

public final class LibraryService {

    private LibraryService() {}

    public static List<Book> getAllBooks(LibraryData data) {

        if (data.getBookInfo().values().isEmpty()) {
            throw new RuntimeException("В библиотеке нет книг!");
        }

        return data.getBookInfo().values().stream().toList();
    }

    public static List<Book> getAllAvailableBooks(LibraryData data) {

        List<Book> availableBooks = data.getBookInfo().values().stream().filter(Book::isAvailable).toList();

        if (availableBooks.isEmpty()) {
            throw new RuntimeException("Нет доступных книг!");
        }

        return availableBooks;
    }

    public static List<Book> getUserBooks(Long userId, LibraryData data) {

        if (!data.getUserInfo().containsKey(userId)) {
            throw new IllegalArgumentException("Пользователя не существует!");
        }

        if (data.getUserInfo().get(userId).getTakenBooks().isEmpty()) {
            throw new RuntimeException("У пользователя нет книг!");
        }

        return data.getUserInfo().get(userId).getTakenBooks().values().stream().toList();
    }

    public static boolean takeBook(Long userId, Long bookId, LibraryData data) {

        if (!data.getUserInfo().containsKey(userId)) {
            throw new IllegalArgumentException("Пользователя не существует!");
        }

        if (!data.getBookInfo().containsKey(bookId)) {
            throw new IllegalArgumentException("Книги не существует!");
        }

        if (!data.getBookInfo().get(bookId).isAvailable()) {
            return false;
        }

        data.getUserInfo().get(userId).getTakenBooks().put(bookId, data.getBookInfo().get(bookId));
        data.getBookInfo().get(bookId).setAvailable(false);

        return true;
    }

    public static boolean returnBook(Long userId, Long bookId, LibraryData data) {

        if (!data.getUserInfo().containsKey(userId)) {
            throw new IllegalArgumentException("Пользователя не существует!");
        }

        if (!data.getBookInfo().containsKey(bookId)) {
            throw new IllegalArgumentException("Книги не существует!");
        }

        if (!data.getUserInfo().get(userId).getTakenBooks().containsKey(bookId)) {
            return false;
        }

        data.getUserInfo().get(userId).getTakenBooks().remove(bookId);
        data.getBookInfo().get(bookId).setAvailable(true);

        return true;
    }
}
