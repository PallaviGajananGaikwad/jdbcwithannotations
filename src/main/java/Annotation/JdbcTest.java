package Annotation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest {
    private String username;
    private String password;

    @Factory(dataProvider = "userDataProvider")
    public JdbcTest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @DataProvider(name = "userDataProvider")
    public static Object[][] provideUserData() {
        return new Object[][] {
                {"user1", "password1"},
                {"user2", "password2"},
                {"user3", "password3"}
        };
    }

    @Test
    public void testDatabaseInteraction() {
        String url = "jdbc:mysql://localhost:3306/company";
        String dbUsername = "root";
        String dbPassword = "1520";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try (Connection connection = DriverManager.getConnection(url, username, password);) {

            String selectQuery = "SELECT * FROM users";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("Username: " + username + ", Password: " + password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
