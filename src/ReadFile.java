import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        String path = "C:/Users/ARLebedev/Downloads/access.log";
        int totalLines = 0;
        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;

        try (FileReader fileReader = new FileReader(path);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                int length = line.length();
                totalLines++;
                if (length > maxLength) {
                    maxLength = length;
                }
                if (length < minLength) {
                    minLength = length;
                }
                if (length > 1024) {
                    throw new LineTooLongException("Строка длиннее 1024 символов: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (LineTooLongException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Общее количество строк в файле: " + totalLines);
        System.out.println("Длина самой длинной строки в файле: " + maxLength);
        System.out.println("Длина самой короткой строки в файле: " + minLength);
    }

    static class LineTooLongException extends RuntimeException {
        public LineTooLongException(String message) {
            super(message);
        }
    }
}