
// SuperArrayCalculator.java

import java.util.*;

public class SuperArrayCalculator {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Double> numbers = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("Вітаємо в Super Array Calculator!");
        inputNumbers();
        
        while (true) {
            showMenu();
            int choice = getInt("Оберіть опцію: ");
            if (choice == 0) break;
            performOperation(choice);
            }
        
        System.out.println("Роботу завершено. Дякуємо!");
        }
    
    private static void inputNumbers() {
        while (true) {
            System.out.print("Введіть числа через пробіл: ");
            try {
                numbers.clear();
                String[] tokens = scanner.nextLine().trim().split("\s+");
                for (String token : tokens) {
                    numbers.add(Double.parseDouble(token));
                    }
                break;
                } catch (NumberFormatException e) {
                    System.out.println("Помилка: введіть лише числа.");
                    }
                }
            }
        
        private static void showMenu() {
            System.out.println("\n=== Меню операцій ===");
            System.out.println(" 1. Кількість усіх чисел");
            System.out.println(" 2. Кількість додатних");
            System.out.println(" 3. Кількість від’ємних");
            System.out.println(" 4. Числа у зворотному порядку");
            System.out.println(" 5. Сортування за зростанням");
            System.out.println(" 6. Сортування за спаданням");
            System.out.println(" 7. Найменше число");
            System.out.println(" 8. Найбільше число");
            System.out.println(" 9. Найчастіше число (мода)");
            System.out.println("10. Видалити повтори");
            System.out.println("11. Видалити від’ємні");
            System.out.println("12. Видалити додатні");
            System.out.println("13. Парні індекси");
            System.out.println("14. Непарні індекси");
            System.out.println("15. Сума всіх");
            System.out.println("16. Сума додатних");
            System.out.println("17. Сума за парними індексами");
            System.out.println("18. Сума за непарними індексами");
            System.out.println("19. Добуток всіх");
            System.out.println("20. Середнє арифметичне");
            System.out.println("21. Середнє геометричне");
            System.out.println("22. Середнє гармонійне");
            System.out.println("23. Медіана");
            System.out.println("24. Перший квартиль");
            System.out.println("25. Третій квартиль");
            System.out.println("26. Дисперсія");
            System.out.println("27. Середньоквадратичне відхилення");
            System.out.println(" 0. Вийти");
            }
        
        private static int getInt(String prompt) {
            while (true) {
                System.out.print(prompt);
                try {
                    return Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Введіть коректне число.");
                        }
                    }
                }
            
            private static void performOperation(int op) {
                if (numbers.isEmpty()) {
                    System.out.println("Масив порожній.");
                    return;
                    }
                List<Double> copy = new ArrayList<>(numbers);
                switch (op) {
                    case 1 -> System.out.println("Кількість: " + copy.size());
                    case 2 -> System.out.println("Додатні: " + copy.stream().filter(n -> n > 0).count());
                    case 3 -> System.out.println("Від’ємні: " + copy.stream().filter(n -> n < 0).count());
                    case 4 -> {
                        Collections.reverse(copy);
                        System.out.println(copy);
                        }
                    case 5 -> {
                        Collections.sort(copy);
                        System.out.println(copy);
                        }
                    case 6 -> {
                        copy.sort(Collections.reverseOrder());
                        System.out.println(copy);
                        }
                    case 7 -> System.out.println("Мінімум: " + Collections.min(copy));
                    case 8 -> System.out.println("Максимум: " + Collections.max(copy));
                    case 9 -> {
                        Map<Double, Integer> freq = new HashMap<>();
                        for (double n : copy) freq.put(n, freq.getOrDefault(n, 0) + 1);
                        int maxFreq = Collections.max(freq.values());
                        System.out.println("Мода: ");
                        freq.entrySet().stream().filter(e -> e.getValue() == maxFreq).forEach(e -> System.out.print(e.getKey() + " "));
                        System.out.println();
                        }
                    case 10 -> System.out.println(new ArrayList<>(new LinkedHashSet<>(copy)));
                    case 11 -> System.out.println(copy.stream().filter(n -> n >= 0).toList());
                    case 12 -> System.out.println(copy.stream().filter(n -> n <= 0).toList());
                    case 13 -> printByIndex(copy, true);
                    case 14 -> printByIndex(copy, false);
                    case 15 -> System.out.println("Сума: " + copy.stream().mapToDouble(Double::doubleValue).sum());
                    case 16 -> System.out.println("Сума додатних: " + copy.stream().filter(n -> n > 0).mapToDouble(Double::doubleValue).sum());
                    case 17 -> sumByIndex(copy, true);
                    case 18 -> sumByIndex(copy, false);
                    case 19 -> {
                        double prod = 1;
                        for (double d : copy) prod *= d;
                        System.out.println("Добуток: " + prod);
                        }
                    case 20 -> System.out.println("Середнє арифм: " + copy.stream().mapToDouble(Double::doubleValue).average().orElse(0));
                    case 21 -> {
                        double product = 1;
                        for (double n : copy) product *= n;
                        System.out.println("Середнє геометр: " + Math.pow(product, 1.0 / copy.size()));
                        }
                    case 22 -> {
                        double denom = copy.stream().mapToDouble(n -> 1.0 / n).sum();
                        System.out.println("Середнє гармон.: " + (copy.size() / denom));
                        }
                    case 23 -> {
                        Collections.sort(copy);
                        double median = copy.size() % 2 == 0 ?
                        (copy.get(copy.size() / 2 - 1) + copy.get(copy.size() / 2)) / 2.0 :
                        copy.get(copy.size() / 2);
                        System.out.println("Медіана: " + median);
                        }
                    case 24 -> System.out.println("Q1: " + percentile(copy, 25));
                    case 25 -> System.out.println("Q3: " + percentile(copy, 75));
                    case 26 -> {
                        double mean = copy.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                        double variance = copy.stream().mapToDouble(n -> Math.pow(n - mean, 2)).sum() / copy.size();
                        System.out.println("Дисперсія: " + variance);
                        }
                    case 27 -> {
                        double mean = copy.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                        double variance = copy.stream().mapToDouble(n -> Math.pow(n - mean, 2)).sum() / copy.size();
                        System.out.println("СКВ: " + Math.sqrt(variance));
                        }
                    default -> System.out.println("Невірна опція.");
                    }
                }
            
            private static void printByIndex(List<Double> list, boolean even) {
                for (int i = 0; i < list.size(); i++) {
                    if (even && i % 2 == 0) System.out.print(list.get(i) + " ");
                    if (!even && i % 2 != 0) System.out.print(list.get(i) + " ");
                    }
                System.out.println();
                }
            
            private static void sumByIndex(List<Double> list, boolean even) {
                double sum = 0;
                for (int i = 0; i < list.size(); i++) {
                    if ((i % 2 == 0) == even) sum += list.get(i);
                    }
                System.out.println("Сума: " + sum);
                }
            
            private static double percentile(List<Double> list, double p) {
                Collections.sort(list);
                int index = (int) Math.ceil((p / 100) * list.size()) - 1;
                return list.get(Math.max(index, 0));
                }
            }