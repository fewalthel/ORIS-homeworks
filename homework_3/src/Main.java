import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/oris_practice";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        UserRepository userRepository = new UserRepositoryJdbcImpl(connection);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Сколько пользователей хотите добавить: ");
        int count = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.print("Введите имя: ");
            String firstname = scanner.next();
            System.out.print("Введите фамилию: ");
            String lastname = scanner.next();
            System.out.print("Введите возраст: ");
            int age = scanner.nextInt();
            System.out.print("Введите email: ");
            String email = scanner.next();
            System.out.print("Введите username: ");
            String username = scanner.next();
            System.out.print("Введите номер телефона: ");
            String phonenumber = scanner.next();

            User user = new User(null, firstname, lastname, age, email, username, phonenumber);
            userRepository.save(user);
        }
    }
}
