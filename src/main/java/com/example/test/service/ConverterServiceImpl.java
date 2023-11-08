package com.example.test.service;

import com.example.test.exeptions.ValidationExeption;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ConverterServiceImpl implements ConverterService {

    @Override
    public Map<Character, Long> getSortMap(String text) {
        if (text == null || text.isEmpty()) throw new ValidationExeption("Строка не может быть пустой");
        Map<Character, Long> collection = new LinkedHashMap<>();
        getMapFromString(text.toLowerCase()).entrySet()
                .stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .forEach(s -> collection.put(s.getKey(), s.getValue()));
        return collection;
    }

    private Map<Character, Long> getMapFromString(String text) {
        Pattern pattern = Pattern.compile("^[A-zА-яЁё]+$");
        List<Character> myListOfCharacters = IntStream
                .range(0, text.toCharArray().length)
                .mapToObj(i -> text.toCharArray()[i])
                .filter(s -> pattern.matcher(s.toString()).matches())
                .collect(Collectors.toList());
        return myListOfCharacters.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
    }
}
