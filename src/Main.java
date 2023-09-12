import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       System.out.println("Введите первое число:");
       int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int secondNumber = new Scanner(System.in).nextInt();

        //сумма чисел
        int sum = firstNumber + secondNumber;
        System.out.println("Сумма чисел: "+ sum);

        //разность чисел
        int diff = firstNumber - secondNumber;
        System.out.println("Разность чисел: "+ diff);

        //произведение чисел
        int prod = firstNumber * secondNumber;
        System.out.println("Произведение чисел: "+ prod);

        //частное чисел
        double part = (double) firstNumber / secondNumber;
        System.out.println("Частное чисел : "+ part);

    }
}
