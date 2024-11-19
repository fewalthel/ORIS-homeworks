import java.util.List;

public interface UserRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);

    List<User> findAllByEmail(String email);
    List<User> findAllByUsername(String username);
    List<User> findAllByPhoneNumber(String phoneNumber);
}