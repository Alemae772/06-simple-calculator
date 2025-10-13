package com.danhramtsov.java.essentials;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTaskTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
        Locale.setDefault(Locale.US);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private String runWithInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        assertDoesNotThrow(() -> SimpleCalculatorTask.main(new String[]{}),
                "Программа не должна выбрасывать исключений при вводе: " + input);
        return outContent.toString(StandardCharsets.UTF_8).trim();
    }

    @Test
    void testAddition() {
        String output = runWithInput("33\n+\n77\n");
        assertEquals("Результат: 110.00", output, "Ожидается результат 110");
    }

    @Test
    void testSubtraction() {
        String output = runWithInput("50\n-\n20\n");
        assertEquals("Результат: 30.00", output, "Ожидается результат 30");
    }

    @Test
    void testMultiplication() {
        String output = runWithInput("20\n*\n15\n");
        assertEquals("Результат: 300.00", output, "Ожидается результат 300.00");
    }

    @Test
    void testDivision() {
        String output = runWithInput("10\n/\n3\n");
        assertEquals("Результат: 3.33", output, "Ожидается результат 3.33");
    }

    @Test
    void testDivisionByZero() {
        String output = runWithInput("150\n/\n0\n");
        assertEquals("Ошибка!", output, "При делении на ноль должна выводиться 'Ошибка!'");
    }

    @Test
    void testUnknownOperator() {
        String output = runWithInput("10\n%\n3\n");
        assertEquals("Ошибка!", output, "При неизвестной операции должно быть 'Ошибка!'");
    }

    @Test
    void testNegativeNumbers() {
        String output = runWithInput("-5\n*\n3\n");
        assertEquals("Результат: -15.00", output, "Ожидается результат -15.00");
    }

    @Test
    void testDecimalRounding() {
        String output = runWithInput("5\n/\n2\n");
        assertEquals("Результат: 2.50", output, "Ожидается результат 2.50");
    }

    @Test
    void testDoubleOperands() {
        String output = runWithInput("5.5\n+\n2.2\n");
        assertEquals("Результат: 7.70", output,
                "Программа должна корректно складывать дробные числа");
    }

    @Test
    void testDoubleDivision() {
        String output = runWithInput("7.5\n/\n2.5\n");
        assertEquals("Результат: 3.00", output,
                "Программа должна поддерживать деление дробных чисел без ошибок");
    }

    @Test
    void testOutputFormat() {
        String output = runWithInput("7\n*\n7\n");
        assertTrue(output.matches("(?s).*Результат:\\s*-?\\d+(\\.\\d{2})?.*"),
                "Вывод должен содержать 'Результат: ' и число с двумя знаками после запятой");
    }

    @Test
    void testNotUsingStringForNumbers() {
        String output = runWithInput("10.1\n+\n0.9\n");
        assertTrue(output.contains("Результат: 11.00") || output.contains("Результат: 11"),
                "Если программа обрабатывает '10.1' + '0.9' корректно, значит числа не String");
    }
}
