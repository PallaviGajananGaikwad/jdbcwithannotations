package Annotation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderDemo {

        String env = "dev";
        // Define a data provider method
        @DataProvider(name = "loginData")
        public Object[][] testData() {
            return new Object[][] {
                    {"user1", "password1"},
                    {"user2", "password2"},
                    {"user3", "password3"}
            };
        }

        // Test method that uses the data provider
        @Test(dataProvider = "loginData")
        public void loginTest(String username, String password) {
            System.out.println("Logging in with username: "
                    + username + " and password: " + password + " in "+this.env +" environment.");
            // Add your test logic here
        }

}
