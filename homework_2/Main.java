import java.sql.*;
import java.util.Scanner;

public class Main {
    public static final String URL = "jdbc:postgresql://localhost:5432/oris_practice";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";

    public static void main(String[] args) throws Exception {
        //вставка 6 пользователей единым запросом
        addUsers();

        //получение пользователей с определенными характеристиками
        getUsers();
    }

    public static void addUsers() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        String sqlInsertUser = "insert into driver(name, surname, age) values (?, ?, ?), (?, ?, ?), (?, ?, ?), (?, ?, ?), (?, ?, ?), (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);

        Scanner scanner = new Scanner(System.in);

        for (int i=0; i < 6; i++) {
            System.out.println("Enter info about the "+ (i+1) +" user you want to add:");
            System.out.print("Enter name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter secondName: ");
            String secondName = scanner.nextLine();
            System.out.print("Enter age: ");
            String age = scanner.nextLine();

            int paramIndex = i * 3 + 1;
            preparedStatement.setString(paramIndex, firstName);
            preparedStatement.setString(paramIndex + 1, secondName);
            preparedStatement.setString(paramIndex + 2, age);
        }

        int affectRows = preparedStatement.executeUpdate();

        System.out.println(affectRows+" lines were added.");
    }

    public static void getUsers() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from driver where cast(age as integer) >= 18");
        System.out.println("All users:");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") +". "+resultSet.getString("name"));
        }
    }
}