import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PrilName {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");
        String input = scanner.nextLine();

        try {
            String[] data = input.split(" ");
            PrilName.validateData(data);
            PrilName.saveToFile(data);
            System.out.println("Данные успешно сохранены");
        } catch (InputDataException | IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void validateData(String[] data) throws InputDataException {
        if (data.length != 6) {
            throw new InputDataException("Введеное значение некорректно"
            );
        }

        try {
            // Проверяем правильность ввода даты рождения 
            LocalDate.parse(data[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        } catch (Exception e) {
            throw new InputDataException("Введенный формат не соответствует dd.mm.yyyy");
        }

        try {
            // Проверяем номер телефона на корректность формата
            Long.parseLong(data[4]);
        } catch (NumberFormatException e) {
            throw new InputDataException("Введен неверный формат номера телефона");
        }

        // Проверяем пол
        if (!data[5].equalsIgnoreCase("m") && !data[5].equalsIgnoreCase("f")) {
            throw new InputDataException("Введен неверный формат пола. Выберите символ 'm' или 'f'.");
        }
    }

    public static void saveToFile(String[] data) throws IOException {
        String filename = data[0] + ".txt"; // Имя файла - фамилия
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(String.join(" ", data));
        }
    }
}