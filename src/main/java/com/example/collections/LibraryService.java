package com.example.collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public final class LibraryService {

    private final Map<Long, User> userMap;
    private final Map<Long, Book> bookMap;
    private final Map<Long, Long> takenBooksMap;
    private final Map<Long, Set<Long>> userTakenBooksMap;

    public LibraryService(List<Book> bookList, List<User> userList) {
        userMap = userList.stream()
                .collect(Collectors.toMap(User::getId, user -> user));
        bookMap = bookList.stream()
                .collect(Collectors.toMap(Book::getId, book -> book));
        takenBooksMap = new HashMap<>();
        userTakenBooksMap = new HashMap<>();
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookMap.values());
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public List<Book> getAllAvailableBooks() {
        return bookMap.values().stream()
                .filter(book -> !takenBooksMap.containsKey(book.getId())).toList();
    }

    public List<Long> getUserBooks(Long userId) {

        if (!userMap.containsKey(userId)) {
            throw new IllegalArgumentException("Пользователя не существует");
        }

        return new ArrayList<>(userTakenBooksMap.get(userId));
    }

    public void takeBook(Long userId, Long bookId) {

        if (!userMap.containsKey(userId) || !bookMap.containsKey(bookId)) {
            throw new IllegalArgumentException("Пользователя или книги не существует");
        }

        takenBooksMap.put(bookId, userId);
        userTakenBooksMap.computeIfAbsent(userId, map -> new HashSet<>()).add(bookId);
    }

    public void returnBook(Long userId, Long bookId) {

        if (!userMap.containsKey(userId) || !bookMap.containsKey(bookId)) {
            throw new IllegalArgumentException("Пользователя или книги не существует");
        }

        if (!takenBooksMap.containsKey(bookId)) {
            throw new IllegalArgumentException("Пользователь не брал данную книгу");
        }

        takenBooksMap.remove(bookId);
        userTakenBooksMap.computeIfAbsent(userId, map -> new HashSet<>()).remove(bookId);
    }
}
