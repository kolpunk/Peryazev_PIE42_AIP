import java.util.Arrays;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите элементы массива через пробел: ");
        String input = scanner.nextLine().trim();
        
        String[] elements = input.split("\\s+");
        int[] arrayA = new int[elements.length];
        
        for (int i = 0; i < elements.length; i++) {
            while (true) {
                try {
                    arrayA[i] = Integer.parseInt(elements[i]);
                    break;
                } catch (NumberFormatException e) {
                    System.out.print("Некорректный ввод для элемента " + (i + 1) + 
                                   ". Введите целое число заново: ");
                    elements[i] = scanner.nextLine().trim();
                }
            }
        }
        
        int[] arrayB = calculateWaitingTimes(arrayA);
        int totalTime = arrayB.length > 0 ? arrayB[arrayB.length - 1] : 0;
        
        System.out.println("\nРезультаты:");
        System.out.println("Массив A: " + Arrays.toString(arrayA));
        System.out.println("Массив B: " + Arrays.toString(arrayB));
        System.out.println("Общее время обслуживания: " + totalTime);
    }
    
    private static int[] calculateWaitingTimes(int[] serviceTimes) {
        int[] waitingTimes = new int[serviceTimes.length];
        if (serviceTimes.length == 0) return waitingTimes;
        
        waitingTimes[0] = serviceTimes[0];
        for (int i = 1; i < serviceTimes.length; i++) {
            waitingTimes[i] = waitingTimes[i - 1] + serviceTimes[i];
        }
        
        return waitingTimes;
    }
}
