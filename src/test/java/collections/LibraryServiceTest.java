package collections;

import com.example.collections.Book;
import com.example.collections.LibraryService;
import com.example.collections.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class LibraryServiceTest {

    private LibraryService libraryServiceWithBooksAndUsers;
    private LibraryService libraryServiceWithoutBooksAndUsers;
    private List<User> userListNotEmpty;
    private List<Book> bookListNotEmpty;
    private List<User> userListEmpty;
    private List<Book> bookListEmpty;

    @BeforeEach
    void setUp() {
        userListNotEmpty = new ArrayList<>() {{
            add(new User("Олег", 25, 1L));
            add(new User("Иван", 44, 2L));
            add(new User("Игорь", 15, 3L));
            add(new User("Степан", 26, 4L));
            add(new User("Николай", 53, 5L));
        }};
        bookListNotEmpty = new ArrayList<>() {{
            add(new Book("Мастер и Маргарита", "Михаил Булгаков", 1967, 1L));
            add(new Book("Финансист", "Теодор Драйзер", 1912, 2L));
            add(new Book("Атлант расправил плечи", "Айн Рэнд", 1957, 3L));
            add(new Book("1984", "Джордж Оруэлл", 1949, 4L));
            add(new Book("Скотный двор", "Джордж Оруэлл", 1945, 5L));
        }};

        userListEmpty = new ArrayList<>();
        bookListEmpty = new ArrayList<>();

        libraryServiceWithBooksAndUsers = new LibraryService(bookListNotEmpty, userListNotEmpty);
        libraryServiceWithoutBooksAndUsers = new LibraryService(bookListEmpty, userListEmpty);
    }

    @Test
    void getAllBooksAfterLibraryServiceInitIfNotEmpty() {
        List<Book> expectedBooks = bookListNotEmpty;
        List<Book> actualBooks = libraryServiceWithBooksAndUsers.getAllBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void getAllUsersAfterLibraryServiceInitIfNotEmpty() {
        List<User> expectedUsers = userListNotEmpty;
        List<User> actualUsers = libraryServiceWithBooksAndUsers.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void getAllBooksAfterLibraryServiceInitIfEmpty() {
        List<Book> expectedBooks = bookListEmpty;
        List<Book> actualBooks = libraryServiceWithoutBooksAndUsers.getAllBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void getAllUsersAfterLibraryServiceInitIfEmpty() {
        List<User> expectedUsers = userListEmpty;
        List<User> actualUsers = libraryServiceWithoutBooksAndUsers.getAllUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void getAllAvailableBooksAfterUserTakeSomeBooks() {
        libraryServiceWithBooksAndUsers.takeBook(1L, 1L);
        libraryServiceWithBooksAndUsers.takeBook(1L, 2L);

        List<Book> expectedBooks = Arrays.asList(
                new Book("Атлант расправил плечи", "Айн Рэнд", 1957, 3L),
                new Book("1984", "Джордж Оруэлл", 1949, 4L),
                new Book("Скотный двор", "Джордж Оруэлл", 1945, 5L)
        );
        List<Book> actualBooks = libraryServiceWithBooksAndUsers.getAllAvailableBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void getAllAvailableBooksAfterUserReturnBooks() {
        libraryServiceWithBooksAndUsers.takeBook(1L, 1L);
        libraryServiceWithBooksAndUsers.takeBook(1L, 2L);
        libraryServiceWithBooksAndUsers.returnBook(1L, 1L);

        List<Book> expectedBooks = Arrays.asList(
                new Book("Мастер и Маргарита", "Михаил Булгаков", 1967, 1L),
                new Book("Атлант расправил плечи", "Айн Рэнд", 1957, 3L),
                new Book("1984", "Джордж Оруэлл", 1949, 4L),
                new Book("Скотный двор", "Джордж Оруэлл", 1945, 5L)
        );
        List<Book> actualBooks = libraryServiceWithBooksAndUsers.getAllAvailableBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    void getUserBooksAfterUserTakeSomeBooks() {
        libraryServiceWithBooksAndUsers.takeBook(1L, 1L);
        libraryServiceWithBooksAndUsers.takeBook(1L, 2L);
        libraryServiceWithBooksAndUsers.takeBook(2L, 3L);
        libraryServiceWithBooksAndUsers.takeBook(2L, 4L);

        List<Long> expectedUserId1BooksList = Arrays.asList(1L, 2L);
        List<Long> expectedUserId2BooksList = Arrays.asList(3L, 4L);

        List<Long> actualUserId1BooksList = libraryServiceWithBooksAndUsers.getUserBooks(1L);
        List<Long> actualUserId2BooksList = libraryServiceWithBooksAndUsers.getUserBooks(2L);

        assertEquals(expectedUserId1BooksList, actualUserId1BooksList);
        assertEquals(expectedUserId2BooksList, actualUserId2BooksList);
    }

    @Test
    void getUserBooksAfterUserReturnBooks() {
        libraryServiceWithBooksAndUsers.takeBook(1L, 1L);
        libraryServiceWithBooksAndUsers.takeBook(1L, 2L);
        libraryServiceWithBooksAndUsers.takeBook(2L, 3L);
        libraryServiceWithBooksAndUsers.takeBook(2L, 4L);
        libraryServiceWithBooksAndUsers.returnBook(1L, 1L);
        libraryServiceWithBooksAndUsers.returnBook(2L, 3L);

        List<Long> expectedUserId1BooksList = List.of(2L);
        List<Long> expectedUserId2BooksList = List.of(4L);

        List<Long> actualUserId1BooksList = libraryServiceWithBooksAndUsers.getUserBooks(1L);
        List<Long> actualUserId2BooksList = libraryServiceWithBooksAndUsers.getUserBooks(2L);

        assertEquals(expectedUserId1BooksList, actualUserId1BooksList);
        assertEquals(expectedUserId2BooksList, actualUserId2BooksList);
    }

    @Test
    void notExistUserErrorWhenToTryGetUserBook() {
        assertThrows(IllegalArgumentException.class, () -> libraryServiceWithBooksAndUsers.getUserBooks(10L));
    }

    @Test
    void notExistUserOrBookErrorWhenToTryTakeBooks() {
        assertThrows(IllegalArgumentException.class, () -> libraryServiceWithBooksAndUsers.takeBook(10L, 10L));
    }

    @Test
    void notExistUserOrBookErrorWhenToTryReturnBooks() {
        assertThrows(IllegalArgumentException.class, () -> libraryServiceWithBooksAndUsers.returnBook(10L, 10L));
    }

    @Test
    void userHasNotTakeBookErrorWhenToTryReturnBooks() {
        assertThrows(IllegalArgumentException.class, () -> libraryServiceWithBooksAndUsers.returnBook(1L, 10L));
    }
}
