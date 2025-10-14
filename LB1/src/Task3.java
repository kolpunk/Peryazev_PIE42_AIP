import java.util.InputMismatchException;
import java.util.Scanner;

class Clothing {
    private double costPerUnit;
    private double countAmount;

    public Clothing() {
        __init__(costPerUnit, countAmount);
    }

    public void __init__(double costPerUnit, double countAmount){
        this.costPerUnit = 0;
        this.countAmount = 0;
    }

    public void read() {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        while (!isValid) {
            try{
                System.out.print("Введите стоимость единицы длины ткани: ");
                this.costPerUnit = scanner.nextDouble();
                System.out.print("Введите количество ткани для пошива изделия: ");
                this.countAmount = scanner.nextDouble();
                isValid = true;
            }catch (InputMismatchException e){
                System.out.println("Данные были введены некоректно, попробуйте еще раз"); scanner.next();
            }
        }
    }

    public void display() {
        System.out.printf("Стоимость ткани за единицу: %.2f\n", costPerUnit);
        System.out.printf("Количество ткани: %.2f\n", countAmount);
        System.out.printf("Стоимость изделия: %.2f\n", calculateCost());
    }

    public double calculateCost() {
        return costPerUnit * countAmount;
    }

    public double getCostPerUnit() {
        return costPerUnit;
    }

    public double getCountAmount() {
        return countAmount;
    }
}

class Atelier {
    private String name;
    private Clothing clot1; private Clothing clot2; private Clothing clot3;
    private int quan1; private int quan2; private int quan3;
    private double accesCost;

    public Atelier() {
        __init__();
    }

    public void __init__(){
        this.name = "";
        this.clot1 = new Clothing();
        this.clot2 = new Clothing();
        this.clot3 = new Clothing();
        this.quan1 = 0;
        this.quan2 = 0;
        this.quan3 = 0;
        this.accesCost = 0.0;
    }

    public void read() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название ателье: ");
        this.name = scanner.nextLine();
        boolean isValid = false;

        System.out.println("Введите данные для первого типа одежды:");
        clot1.read();
        System.out.print("Введите количество первого типа: ");
        while (!isValid) {
            try{
                quan1 = scanner.nextInt(); isValid = true;
            }catch (InputMismatchException e){
                System.out.println("Некоректный ввод данных, попробуйте еще раз"); scanner.next();
            }
        }

        System.out.println("Введите данные для второго типа одежды:");
        clot2.read();
        System.out.print("Введите количество второго типа: ");
        isValid = false;
        while (!isValid) {
            try{
                quan2 = scanner.nextInt(); isValid = true;
            }catch (InputMismatchException e){
                System.out.println("Некоректный ввод данных, попробуйте еще раз"); scanner.next();
            }
        }

        System.out.println("Введите данные для третьего типа одежды:");
        clot3.read();
        System.out.print("Введите количество третьего типа: ");
        isValid = false;
        while (!isValid) {
            try{
                quan3 = scanner.nextInt(); isValid = true;
            }catch (InputMismatchException e){
                System.out.println("Некоректный ввод данных, попробуйте еще раз"); scanner.next();
            }
        }

        System.out.print("Введите общую стоимость дополнительных аксессуаров: ");
        isValid = false;
        while (!isValid) {
            try{
                accesCost = scanner.nextDouble(); isValid = true;
            }catch (InputMismatchException e){
                System.out.println("Некоректный ввод данных, попробуйте еще раз"); scanner.next();
            }
        }
    }

    public void display() {
        System.out.println("Ателье: " + name);

        System.out.println("1ый тип одежды:");
        clot1.display();
        System.out.println("Количество: " + quan1);

        System.out.println("\n2ой тип одежды:");
        clot2.display();
        System.out.println("Количество: " + quan2);

        System.out.println("\n3ий тип одежды:");
        clot3.display();
        System.out.println("Количество: " + quan3);

        System.out.printf("\nСтоимость доп аксессуаров: %.2f\n", accesCost);
        System.out.printf("Общая стоимость всего в ателье: %.2f\n", TotalCost());
        System.out.println("Самая дорогая одежда:");
        findMostExpensive().display();
    }

    public double TotalCost() {
        double total = accesCost;
        total += clot1.calculateCost() * quan1;
        total += clot2.calculateCost() * quan2;
        total += clot3.calculateCost() * quan3;
        return total;
    }

    public Clothing findMostExpensive() {
        double cost1 = clot1.calculateCost();
        double cost2 = clot2.calculateCost();
        double cost3 = clot3.calculateCost();

        if (cost1 >= cost2 && cost1 >= cost3) {
            return clot1;
        } else if (cost2 >= cost1 && cost2 >= cost3) {
            return clot2;
        } else {
            return clot3;
        }
    }
}

public class Task3 {
    public static void main(String[] args) {
        Atelier atelier = new Atelier();
        atelier.read();
        System.out.println("\n\n\n");
        atelier.display();
    }
}