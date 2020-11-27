/*В приведенных ниже заданиях необходимо вывести внизу фамилию разработчика, дату и время получения задания,
а также дату и время сдачи задания. Добавить комментарии в программы в виде /* комментарий /,
сгенерировать html-файл документации. В заданиях на числа объект можно создавать в виде массива символов.

Ввести n чисел с консоли.
1. Найти самое короткое и самое длинное число. Вывести найденные числа и их длину.
2. Упорядочить и вывести числа в порядке возрастания (убывания) значений их длины.
3. Вывести на консоль те числа, длина которых меньше (больше) средней, а также длину.
4. Найти число, в котором число различных цифр минимально. Если таких чисел несколько, найти первое из них.
5. Найти количество чисел, содержащих только четные цифры, а среди оставшихся — количество чисел с равным числом четных и нечетных цифр.
6. Найти число, цифры в котором идут в строгом порядке возрастания. Если таких чисел несколько, найти первое из них.
7. Найти число, состоящее только из различных цифр. Если таких чисел несколько, найти первое из них.
8. Среди чисел найти число-палиндром. Если таких чисел больше одного, найти второе.*/

import java.util.*;

public class Task3 {
    public static Scanner scanner = new Scanner(System.in);
    public static int[] array;
    public static int n;
    static {
        try {
            System.out.println("Предполагаемое количество чисел: ");
            n = scanner.nextInt();
            array = new int[n];
            System.out.println("Вводите числа: ");
            for (int i = 0; i < n; i++) {
                int makePositive = scanner.nextInt();
                if (makePositive < 0) {
                    makePositive *= -1;  //исправляем будущие ошибки при вводе отрицательных чисел;
                }
                array[i] = makePositive;
            }
            if (array.length == 0) {
                System.out.println("Задан пустой массив");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Вероятно совершена ошибка ввода");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        try {
            firstTask(array); //1
            ascendingOrder(array); //2
            longerThanAverage(array, n); //3
            minDifferences(array, n); //4
            evenNumbers(array); //5
            increase(array); //6
            differentDigits(array); //7
            palindrome(array); //8

        } catch (Exception e) {
            System.out.println("Похоже на ошибку ввода");
        }
        finally {
            cleanUp();
        }
        System.out.println("\n\nby student Rakhmangulov Kirill\nDate and time of receiving of the task: 21:00 25.11.2020" +
                "\nDate and time of finishing of the task: ");
    }

    public static void firstTask(int[] array) {
        System.out.println("\n1. Найти самое короткое и самое длинное число. Вывести найденные числа и их длину.");
        Arrays.sort(array);
        int min = Integer.toString(array[0]).length();
        int max = Integer.toString(array[array.length - 1]).length();
        System.out.println("Самое короткое число: " + array[0] + ", его длина " + min +
                ", самое длинное число: " + array[array.length - 1] + ", его длина " + max + ".");
    }

    public static void ascendingOrder(int[] array) {
        System.out.println("\n2. Упорядочить и вывести числа в порядке возрастания (убывания) значений их длины.");
        Arrays.sort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static void longerThanAverage(int[] array, int n) {
        System.out.println("\n3. Вывести на консоль те числа, длина которых меньше (больше) средней, а также длину.");
        int average;
        int count = 0;
        int sum = 0;
        int[] temArray = new int[n];
        int tmp;
        for (int i = 0; i < array.length; i++) {
            tmp = array[i];
            if (String.valueOf(tmp).length() == 1) {
                count++;
            } else {
                while (tmp > 0) {
                    if (tmp % 10 != 0) {
                        count++;
                    }
                    tmp = tmp / 10;
                }
            }
            temArray[i] = count;  //tmpArray[i] содержит количество цифр которые есть в числе array[i];
            sum += count; //количество всех цифр во всех числах;
            count = 0;
        }
        average = sum / n;
        System.out.println("sum: " + sum);
        System.out.println("n: " + n);
        System.out.println("Средняя длина числа: " + average + "\nЧисла, длина которых меньше средней: ");
        for (int i = 0; i < n; i++) {
            if (temArray[i] < average) {
                if (array[i] == 0) {
                    System.out.println(array[i] + " - длина 1");
                } else
                    System.out.println(array[i] + " - длина " + temArray[i]);
            }
        }
        System.out.println("\nЧисла, длина которых больше средней: ");
        for (int i = 0; i < n; i++) {
            if (temArray[i] > average) {
                if (array[i] == 0) {
                    System.out.println(array[i] + " - длина 1");
                } else
                    System.out.println(array[i] + " - длина " + temArray[i]);
            }
        }
    }

    public static void minDifferences(int[] array, int n) {
        System.out.println("\n4. Найти число, в котором число различных цифр минимально. Если таких чисел несколько, найти первое из них.");
        int[] freqValue = new int[n];
        int tmp;
        HashSet<Integer> set = new HashSet<>(); //не добавляет повторяющиеся элементы;
        for (int i = 0; i < array.length; i++) {
            tmp = array[i];
            while (tmp > 0) {
                set.add(tmp % 10);
                tmp /= 10;
            }
            freqValue[i] = set.size();
            set.clear();
        }
        int min = freqValue[0];
        int minNumber = array[0];
        for (int i = 0; i < array.length; i++) {
            if (freqValue[i] < min) {
                min = freqValue[i];
                minNumber = array[i];
            }
        }
        System.out.println("Число, в котором количество различных цифр минимально - " + minNumber);
    }

    public static void evenNumbers(int[] array) {
        System.out.println("\n5. Найти количество чисел, содержащих только четные цифры, " +
                "а среди оставшихся — количество чисел с равным числом четных и нечетных цифр");
        int count = 0; //количество четных цифр в числе;
        int tmp = 0;
        int even = 0;
        int oddAndEven = 0;
        int oneDigit = 0;
        for (int i = 0; i < array.length; i++) {
            tmp = array[i];
            if (tmp == 0) {
                count++;
            } else {
                while (tmp > 0) {
                    if (String.valueOf(tmp).length() > 1) {
                        oneDigit = tmp % 10;
                    } else {
                        oneDigit = tmp;
                    }
                    if (oneDigit % 2 == 0) {
                        count++;
                    }
                    tmp /= 10;
                }
                tmp = array[i];
            }
            if (count == String.valueOf(tmp).length()) {
                even++;
            }
            else if (count == String.valueOf(tmp).length() / 2) {
                oddAndEven++;
            }
            count = 0;
        }
        System.out.println("Количество чисел, содержащих только четные цифры равно " + even +
                ", количество чисел с равным числом четных и нечетных цифр равно " + oddAndEven);
    }

    public static void increase(int[] array) {
        System.out.println("\n6. Найти число, цифры в котором идут в строгом порядке " +
                "возрастания. Если таких чисел несколько, найти первое из них.");
        int solution = 0;
        int matches = 0;
        String tmp;
        for (int i = 0; i < array.length; i++) {
            if ((tmp = String.valueOf(array[i])).length() > 1) {
                char[] number = tmp.toCharArray();
                for (int j = 0; j < number.length - 1; j++) {
                    if (number[j] == number[j + 1] - 1) {
                        matches++;
                    }
                }
                if (matches == number.length - 1) {
                    solution = array[i];
                    System.out.println("Число, цифры в котором идут в строгом порядке возрастания: " + solution);
                    break;
                }
                matches = 0;
            }
        }
        if (solution == 0) {
            System.out.println("Нет таких чисел");
        }
    }

    public static void differentDigits(int[] array) {
        System.out.println("\n7. Найти число, состоящее только из различных цифр. " +
                "Если таких чисел несколько, найти первое из них.");
        int solution = 0;
        int discrepancy = 0; // кол-во несовпадений;
        String tmp;
        for (int i = 0; i < array.length; i++) {
            if ((tmp = String.valueOf(array[i])).length() > 1) {
                char[] number = tmp.toCharArray();
                for (int j = 0; j < number.length; j++) {
                    for (int k = 0; k < number.length; k++) {
                        if (number[j] != number[k]) {
                            discrepancy++;
                        }
                    }
                }
                if (discrepancy == number.length * (number.length - 1)) {
                    /* Каждый символ сравнивается то кол-во раз, сколько символов в числе (в массиве number).
                    Считаем несовпадения, их должно быть меньше на одно чем символов в числе (сам с собой сравниается тоже).
                    Итого кол-во несовпадений в числе с разными цифрами - длина массива умножить на (длина массива - 1)*/
                    solution = array[i];
                    System.out.println("Число, состоящее только из различных цифр: " + solution);
                    break;
                }
                discrepancy = 0;
            }
        }
        if (solution == 0) {
            System.out.println("Нет таких чисел");
        }
    }

    public static void palindrome(int[] array) {
        System.out.println("\n8. Среди чисел найти число-палиндром. Если таких чисел больше одного, найти второе.");
        ArrayList<Integer> palindromes = new ArrayList<>();
        for (int i : array) {
            StringBuilder sb = new StringBuilder(String.valueOf(i));
            sb.reverse();
            if (String.valueOf(i).equals(sb.toString())) {
                if (String.valueOf(i).length() > 1) {
                    palindromes.add(i);
                }
            }
        }
        if (palindromes.size() == 0) {
            System.out.println("Нету таких чисел");
        }
        if (palindromes.size() == 1) {
            System.out.println("Число-палиндром: " + palindromes.get(0));
        } else if (palindromes.size() > 1) {
            System.out.println("Число-палиндром: " + palindromes.get(1));
        }
    }

    public static void cleanUp() {
        scanner.close();
    }

}
