package task5;

import java.util.*;

public class MostFrequentCharacters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите текст: ");
        String text = scanner.nextLine();

        System.out.print("Введите количество символов, которые нужно найти: ");
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Количество символов должно быть положительным числом.");
            return;
        }

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> sortedEntries = new ArrayList<>(frequencyMap.entrySet());
        sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        System.out.println("Наиболее частые символы:");
        for (int i = 0; i < Math.min(n, sortedEntries.size()); i++) {
            Map.Entry<Character, Integer> entry = sortedEntries.get(i);
            System.out.printf("Символ '%c' встречается %d раз(а)\n", entry.getKey(), entry.getValue());
        }
    }
}
