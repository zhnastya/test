package com.example.test;

import com.example.test.exeptions.ValidationExeption;
import com.example.test.service.ConverterService;
import com.example.test.service.ConverterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConvertTests {
    private ConverterService service;

    @BeforeEach
    void init() {
        this.service = new ConverterServiceImpl();
    }

    @Test
    @DisplayName("Если буквы в одном регистре")
    void shouldCheckSameRegex() {
        String text = "a".repeat(10) + "b".repeat(5);
        Map<Character, Long> map = service.getSortMap(text);

        assertEquals(2, map.size());
        assertEquals(map.keySet(), Set.of('a', 'b'));
        assertEquals(map.get('a'), 10);
        assertEquals(map.get('b'), 5);
    }

    @Test
    @DisplayName("Если буквы в разных регистрах")
    void shouldCheckOtherRegex() {
        String text = "A".repeat(10) + "b".repeat(5);
        Map<Character, Long> map = service.getSortMap(text);

        assertEquals(2, map.size());
        assertEquals(map.keySet(), Set.of('a', 'b'));
        assertEquals(map.get('a'), 10);
        assertEquals(map.get('b'), 5);
    }

    @Test
    @DisplayName("Если в строке не только буквы")
    void shouldCheckIfNotOnlyLetters() {
        String text = "A".repeat(10) + "b".repeat(5) +
                "#".repeat(3) + "2".repeat(10);
        Map<Character, Long> map = service.getSortMap(text);

        assertEquals(2, map.size());
        assertEquals(map.keySet(), Set.of('a', 'b'));
        assertEquals(map.get('a'), 10);
        assertEquals(map.get('b'), 5);
    }

    @Test
    @DisplayName("Если в строке есть пробелы")
    void shouldCheckIfHaveSpaces() {
        String text = "A".repeat(10) + "b".repeat(5) +
                " ".repeat(5);
        Map<Character, Long> map = service.getSortMap(text);

        assertEquals(2, map.size());
        assertEquals(map.keySet(), Set.of('a', 'b'));
        assertEquals(map.get('a'), 10);
        assertEquals(map.get('b'), 5);
    }

    @Test
    @DisplayName("Если строка пустая")
    void shouldCheckIfEmpty() {
        String text = "";

        assertThrows(ValidationExeption.class,
                () -> service.getSortMap(text), "Строка не может быть пустой");
    }
}
