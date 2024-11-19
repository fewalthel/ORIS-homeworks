public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

    private String email;
    private String username;
    private String phoneNumber;

    public User(Long id, String firstName, String lastName, Integer age, String email, String username, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
