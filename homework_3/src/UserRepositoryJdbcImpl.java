import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private Connection connection;

    private static final String SQL_SELECT_FROM_DRIVER = "select * from driver";

    public UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> result = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVER)) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        Integer.parseInt(resultSet.getString("age")),
                        resultSet.getString("email"),
                        resultSet.getString("username"),
                        resultSet.getString("phonenumber")
                );
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {
        String sql = "insert into driver (firstname, lastname, age, email, username, phonenumber) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getAge().toString());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getUsername());
            preparedStatement.setString(6, entity.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        String sql = "update driver set firstname = ?, lastname = ?, age = ?, email = ?, username = ?, phonenumber = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getAge().toString());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getUsername());
            preparedStatement.setString(7, entity.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User entity) {
        removeById(entity.getId());
    }

    @Override
    public void removeById(Long id) {
        String sql = "delete from driver where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        String sql = "select from driver where age = ?";
        List<User> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, age.toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getLong("id"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            Integer.parseInt(resultSet.getString("age")),
                            resultSet.getString("email"),
                            resultSet.getString("username"),
                            resultSet.getString("phonenumber")
                    );
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAllByEmail(String email) {
        String sql = "select from driver where email = ?";
        List<User> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getLong("id"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            Integer.parseInt(resultSet.getString("age")),
                            resultSet.getString("email"),
                            resultSet.getString("username"),
                            resultSet.getString("phonenumber")
                    );
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAllByUsername(String username) {
        String sql = "select from driver where username = ?";
        List<User> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getLong("id"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            Integer.parseInt(resultSet.getString("age")),
                            resultSet.getString("email"),
                            resultSet.getString("username"),
                            resultSet.getString("phonenumber")
                    );
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<User> findAllByPhoneNumber(String phoneNumber) {
        String sql = "select from driver where phonenumber = ?";
        List<User> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, phoneNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getLong("id"),
                            resultSet.getString("firstname"),
                            resultSet.getString("lastname"),
                            Integer.parseInt(resultSet.getString("age")),
                            resultSet.getString("email"),
                            resultSet.getString("username"),
                            resultSet.getString("phonenumber")
                    );
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}