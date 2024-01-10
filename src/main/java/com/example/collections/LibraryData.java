package com.example.collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class LibraryData {

    private final Map<Long, User> userInfo = new HashMap<>();
    private final Map<Long, Book> bookInfo = new HashMap<>();

    public Map<Long, User> getUserInfo() {
        return userInfo;
    }

    public Map<Long, Book> getBookInfo() {
        return bookInfo;
    }

    public void addBook(Long bookId, Book book) {
        bookInfo.put(bookId, book);
    }

    public void addUser(Long userId, User user) {
        userInfo.put(userId, user);
    }
}


