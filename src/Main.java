import java.io.File;
import java.util.Scanner;


public class Main {
    public static void main (String[] args){
        int count = 0;
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите путь к файлу:");
            String path = scanner.nextLine();

            File file = new File(path);
            boolean fileExists = file.exists();
            if (!fileExists || file.isDirectory()) {
                System.out.println("Указанный файл не существует или путь ведет к папке");
                continue;
            }
            count++;
            System.out.println("Путь указан верно");
            System.out.println("Этот файл номер " + count);
        }
    }
}