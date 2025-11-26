import java.util.Arrays;
import java.util.Random;

public class SortingV19 {

    // Пузырьковая сортировка (простая)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // Сортировка перемешиванием (шейкерная сортировка)
    public static void shakerSort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        boolean swapped = true;
        
        while (swapped) {
            swapped = false;
            
            // Проход слева направо
            for (int i = left; i < right; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
            right--;
            
            // Проход справа налево
            for (int i = right; i > left; i--) {
                if (arr[i] < arr[i - 1]) {
                    swap(arr, i, i - 1);
                    swapped = true;
                }
            }
            left++;
        }
    }

    // Пирамидальная сортировка (сортировка кучей)
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // Построение max-кучи
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Извлечение элементов из кучи
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }
    
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Генерация тестовых массивов
    public static int[] generateRandomArray(int size, int maxValue) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(maxValue);
        }
        return arr;
    }
    
    public static int[] generatePartiallySortedArray(int size, int maxValue, double sortedPercentage) {
        int[] arr = generateRandomArray(size, maxValue);
        Arrays.sort(arr);
        
        Random rand = new Random();
        int elementsToShuffle = (int)(size * (1 - sortedPercentage));
        for (int i = 0; i < elementsToShuffle; i++) {
            int idx1 = rand.nextInt(size);
            int idx2 = rand.nextInt(size);
            swap(arr, idx1, idx2);
        }
        return arr;
    }
    
    public static int[] generateReverseSortedArray(int size, int maxValue) {
        int[] arr = generateRandomArray(size, maxValue);
        Arrays.sort(arr);
        // Разворачиваем массив
        for (int i = 0; i < size / 2; i++) {
            swap(arr, i, size - 1 - i);
        }
        return arr;
    }
    
    public static int[] generateArrayWithDuplicates(int size, double uniquePercentage) {
        Random rand = new Random();
        int[] arr = new int[size];
        int uniqueValues = (int)(size * uniquePercentage);
        
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(uniqueValues);
        }
        return arr;
    }
    
    public static int[] generateAlmostSortedArray(int size, int maxValue, double sortedPercentage) {
        return generatePartiallySortedArray(size, maxValue, sortedPercentage);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        
        // Размеры массивов
        int smallSize = 100;
        int mediumSize = 1_000;
        int largeSize = 10_000;
        
        // Тестирование на разных типах массивов
        testSortingAlgorithms("Случайный массив", smallSize, mediumSize, largeSize, 1000, 10000, 100000);
        testSortingAlgorithms("Частично отсортированный (75%)", smallSize, mediumSize, largeSize, 1000, 10000, 100000);
        testSortingAlgorithms("Обратно отсортированный", smallSize, mediumSize, largeSize, 1000, 10000, 100000);
        testSortingAlgorithms("Много дубликатов (10% уникальных)", smallSize, mediumSize, largeSize, 100, 1000, 10000);
        testSortingAlgorithms("Почти отсортированный (90%)", smallSize, mediumSize, largeSize, 1000, 10000, 100000);
    }
    
    public static void testSortingAlgorithms(String testName, int smallSize, int mediumSize, int largeSize, 
                                           int smallMax, int mediumMax, int largeMax) {
        System.out.println("=== " + testName + " ===");
        
        // Генерация массивов в зависимости от типа теста
        int[] smallArray1, smallArray2, smallArray3;
        int[] mediumArray1, mediumArray2, mediumArray3;
        int[] largeArray1, largeArray2, largeArray3;
        
        switch(testName) {
            case "Случайный массив":
                smallArray1 = generateRandomArray(smallSize, smallMax);
                smallArray2 = Arrays.copyOf(smallArray1, smallArray1.length);
                smallArray3 = Arrays.copyOf(smallArray1, smallArray1.length);
                
                mediumArray1 = generateRandomArray(mediumSize, mediumMax);
                mediumArray2 = Arrays.copyOf(mediumArray1, mediumArray1.length);
                mediumArray3 = Arrays.copyOf(mediumArray1, mediumArray1.length);
                
                largeArray1 = generateRandomArray(largeSize, largeMax);
                largeArray2 = Arrays.copyOf(largeArray1, largeArray1.length);
                largeArray3 = Arrays.copyOf(largeArray1, largeArray1.length);
                break;
                
            case "Частично отсортированный (75%)":
                smallArray1 = generatePartiallySortedArray(smallSize, smallMax, 0.75);
                smallArray2 = Arrays.copyOf(smallArray1, smallArray1.length);
                smallArray3 = Arrays.copyOf(smallArray1, smallArray1.length);
                
                mediumArray1 = generatePartiallySortedArray(mediumSize, mediumMax, 0.75);
                mediumArray2 = Arrays.copyOf(mediumArray1, mediumArray1.length);
                mediumArray3 = Arrays.copyOf(mediumArray1, mediumArray1.length);
                
                largeArray1 = generatePartiallySortedArray(largeSize, largeMax, 0.75);
                largeArray2 = Arrays.copyOf(largeArray1, largeArray1.length);
                largeArray3 = Arrays.copyOf(largeArray1, largeArray1.length);
                break;
                
            case "Обратно отсортированный":
                smallArray1 = generateReverseSortedArray(smallSize, smallMax);
                smallArray2 = Arrays.copyOf(smallArray1, smallArray1.length);
                smallArray3 = Arrays.copyOf(smallArray1, smallArray1.length);
                
                mediumArray1 = generateReverseSortedArray(mediumSize, mediumMax);
                mediumArray2 = Arrays.copyOf(mediumArray1, mediumArray1.length);
                mediumArray3 = Arrays.copyOf(mediumArray1, mediumArray1.length);
                
                largeArray1 = generateReverseSortedArray(largeSize, largeMax);
                largeArray2 = Arrays.copyOf(largeArray1, largeArray1.length);
                largeArray3 = Arrays.copyOf(largeArray1, largeArray1.length);
                break;
                
            case "Много дубликатов (10% уникальных)":
                smallArray1 = generateArrayWithDuplicates(smallSize, 0.1);
                smallArray2 = Arrays.copyOf(smallArray1, smallArray1.length);
                smallArray3 = Arrays.copyOf(smallArray1, smallArray1.length);
                
                mediumArray1 = generateArrayWithDuplicates(mediumSize, 0.1);
                mediumArray2 = Arrays.copyOf(mediumArray1, mediumArray1.length);
                mediumArray3 = Arrays.copyOf(mediumArray1, mediumArray1.length);
                
                largeArray1 = generateArrayWithDuplicates(largeSize, 0.1);
                largeArray2 = Arrays.copyOf(largeArray1, largeArray1.length);
                largeArray3 = Arrays.copyOf(largeArray1, largeArray1.length);
                break;
                
            case "Почти отсортированный (90%)":
                smallArray1 = generateAlmostSortedArray(smallSize, smallMax, 0.9);
                smallArray2 = Arrays.copyOf(smallArray1, smallArray1.length);
                smallArray3 = Arrays.copyOf(smallArray1, smallArray1.length);
                
                mediumArray1 = generateAlmostSortedArray(mediumSize, mediumMax, 0.9);
                mediumArray2 = Arrays.copyOf(mediumArray1, mediumArray1.length);
                mediumArray3 = Arrays.copyOf(mediumArray1, mediumArray1.length);
                
                largeArray1 = generateAlmostSortedArray(largeSize, largeMax, 0.9);
                largeArray2 = Arrays.copyOf(largeArray1, largeArray1.length);
                largeArray3 = Arrays.copyOf(largeArray1, largeArray1.length);
                break;
                
            default:
                return;
        }
        
        // Тестирование на малых массивах
        System.out.println("\n--- Малые массивы (" + smallSize + " элементов) ---");
        testAlgorithmsOnArray(smallArray1, smallArray2, smallArray3, "Малый");
        
        // Тестирование на средних массивах
        System.out.println("\n--- Средние массивы (" + mediumSize + " элементов) ---");
        testAlgorithmsOnArray(mediumArray1, mediumArray2, mediumArray3, "Средний");
        
        // Тестирование на больших массивах
        System.out.println("\n--- Большие массивы (" + largeSize + " элементов) ---");
        testAlgorithmsOnArray(largeArray1, largeArray2, largeArray3, "Большой");
        
        System.out.println("\n" + "=".repeat(50) + "\n");
    }
    
    public static void testAlgorithmsOnArray(int[] arr1, int[] arr2, int[] arr3, String sizeName) {
        double time1, time2, time3;
        
        // Пузырьковая сортировка
        int[] copy1 = Arrays.copyOf(arr1, arr1.length);
        double start = System.nanoTime();
        bubbleSort(copy1);
        double end = System.nanoTime();
        time1 = (end - start) / 1_000_000.0; // в миллисекундах
        System.out.printf("Пузырьковая: %.3f мс\n", time1);
        
        // Сортировка перемешиванием
        int[] copy2 = Arrays.copyOf(arr2, arr2.length);
        start = System.nanoTime();
        shakerSort(copy2);
        end = System.nanoTime();
        time2 = (end - start) / 1_000_000.0;
        System.out.printf("Перемешиванием: %.3f мс\n", time2);
        
        // Пирамидальная сортировка
        int[] copy3 = Arrays.copyOf(arr3, arr3.length);
        start = System.nanoTime();
        heapSort(copy3);
        end = System.nanoTime();
        time3 = (end - start) / 1_000_000.0;
        System.out.printf("Пирамидальная: %.3f мс\n", time3);
        
        // Определение самого быстрого
        double fastest = Math.min(time1, Math.min(time2, time3));
        String fastestName = "";
        if (fastest == time1) fastestName = "Пузырьковая";
        else if (fastest == time2) fastestName = "Перемешиванием";
        else fastestName = "Пирамидальная";
        
        System.out.printf("✓ Самый быстрый: %s (%.3f мс)\n", fastestName, fastest);
        
        // Проверка корректности сортировки
        boolean correct1 = isSorted(copy1);
        boolean correct2 = isSorted(copy2);
        boolean correct3 = isSorted(copy3);
        System.out.printf("Корректность: пузырьковая=%b, перемешиванием=%b, пирамидальная=%b\n", 
                         correct1, correct2, correct3);
    }
    
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}