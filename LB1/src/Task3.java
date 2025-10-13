import java.util.InputMismatchException;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Complex z1 = new Complex(0f, 0f);
        z1.read();
        Complex z2 = new Complex(0f,0f);
        z2.read();
        
        System.out.println("z1 = " + z1);
        System.out.println("z2 = " + z2);

        Complex sum = z1.add(z2);
        System.out.println("z1 + z2 = " + sum);

        System.out.println("|z1| = " + z1.modulus());
    }
}

class Complex {
    Scanner scanner = new Scanner(System.in);
    private float real;
    private float imaginary;

    public Complex(float real, float imaginary) {
        __init__(real, imaginary);
    }

    public void __init__(float real, float imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }
    
    public void read(){
        boolean isValid = false;

        while (!isValid) {
            try{
                this.real = scanner.nextFloat();
                this.imaginary = scanner.nextFloat();
                isValid = true;
            }catch(InputMismatchException e){System.out.println("Допущена ошибка при вводе вещественного числа, попробуйте еще раз:"); scanner.next();}
        }
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public double modulus() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public Complex add(Complex other) {
        float newReal = this.real + other.real;
        float newImaginary = this.imaginary + other.imaginary;
        return new Complex(newReal, newImaginary);
    }

    public String toString() {
        if (imaginary >= 0) {
            return real + " + " + imaginary + "i";
        } else {
            return real + " - " + (-imaginary) + "i";
        }
    }
}
