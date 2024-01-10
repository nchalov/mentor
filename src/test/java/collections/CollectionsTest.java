package collections;

import com.example.collections.Book;
import com.example.collections.LibraryData;
import com.example.collections.LibraryService;
import com.example.collections.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class CollectionsTest {

    private LibraryData data = new LibraryData();
    private LibraryData emptyData = new LibraryData();

    @BeforeEach
    void setUp() {
        data.addUser(1L, new User("Олег", 25));
        data.addUser(2L, new User("Иван", 44));
        data.addUser(3L, new User("Игорь", 15));
        data.addUser(4L, new User("Степан", 26));
        data.addUser(5L, new User("Николай", 53));

        data.addBook(1L, new Book("Мастер и Маргарита", "Михаил Булгаков", 1967));
        data.addBook(2L, new Book("Финансист", "Теодор Драйзер", 1912));
        data.addBook(3L, new Book("Атлант расправил плечи", "Айн Рэнд", 1957));
        data.addBook(4L, new Book("1984", "Джордж Оруэлл", 1949));
        data.addBook(5L, new Book("Скотный двор", "Джордж Оруэлл", 1945));
    }

    @Test
    void getAllBooks() {
        List<Book> expectedBooks = Arrays.asList(
                new Book("Мастер и Маргарита", "Михаил Булгаков", 1967),
                new Book("Финансист", "Теодор Драйзер", 1912),
                new Book("Атлант расправил плечи", "Айн Рэнд", 1957),
                new Book("1984", "Джордж Оруэлл", 1949),
                new Book("Скотный двор", "Джордж Оруэлл", 1945)
        );
        List<Book> actualBooks = LibraryService.getAllBooks(data);

        assertEquals(expectedBooks, actualBooks);
        assertThrows(RuntimeException.class, () -> LibraryService.getAllBooks(emptyData));
    }

    @Test
    void getAllAvailableBooks() {
        LibraryService.takeBook(1L, 1L, data);
        LibraryService.takeBook(1L, 2L, data);
        LibraryService.takeBook(1L, 3L, data);

        List<Book> expectedBooks = Arrays.asList(
                new Book("1984", "Джордж Оруэлл", 1949),
                new Book("Скотный двор", "Джордж Оруэлл", 1945)
        );
        List<Book> actualBooks = LibraryService.getAllAvailableBooks(data);

        assertEquals(expectedBooks, actualBooks);
        assertThrows(RuntimeException.class, () -> LibraryService.getAllAvailableBooks(emptyData));
    }

    @Test
    void getUserBooks() {
        LibraryService.takeBook(1L, 1L, data);
        LibraryService.takeBook(1L, 2L, data);
        LibraryService.takeBook(1L, 3L, data);
        LibraryService.takeBook(2L, 4L, data);
        LibraryService.takeBook(2L, 5L, data);

        List<Book> expectedBooks1 = Arrays.asList(
                new Book("Мастер и Маргарита", "Михаил Булгаков", 1967),
                new Book("Финансист", "Теодор Драйзер", 1912),
                new Book("Атлант расправил плечи", "Айн Рэнд", 1957)
        );
        List<Book> actualBooks1 = LibraryService.getUserBooks(1L, data);

        List<Book> expectedBooks2 = Arrays.asList(
                new Book("1984", "Джордж Оруэлл", 1949),
                new Book("Скотный двор", "Джордж Оруэлл", 1945)
        );
        List<Book> actualBooks2 = LibraryService.getUserBooks(2L, data);

        assertEquals(expectedBooks1, actualBooks1);
        assertEquals(expectedBooks2, actualBooks2);
        assertThrows(IllegalArgumentException.class, () -> LibraryService.getUserBooks(10L, data));
        assertThrows(RuntimeException.class, () -> LibraryService.getUserBooks(3L, data));
    }

    @Test
    void takeBook() {
        assertTrue(LibraryService.takeBook(1L, 1L, data));
        assertTrue(LibraryService.takeBook(2L, 3L, data));
        assertFalse(LibraryService.takeBook(2L, 3L, data));

        assertThrows(IllegalArgumentException.class, () -> LibraryService.takeBook(10L, 1L, data));
        assertThrows(IllegalArgumentException.class, () -> LibraryService.takeBook(1L, 15L, data));
    }

    @Test
    void returnBook() {
        LibraryService.takeBook(1L, 1L, data);

        assertTrue(LibraryService.returnBook(1L, 1L, data));
        assertFalse(LibraryService.returnBook(1L, 2L, data));

        assertThrows(IllegalArgumentException.class, () -> LibraryService.returnBook(10L, 1L, data));
        assertThrows(IllegalArgumentException.class, () -> LibraryService.returnBook(1L, 15L, data));
    }
}
