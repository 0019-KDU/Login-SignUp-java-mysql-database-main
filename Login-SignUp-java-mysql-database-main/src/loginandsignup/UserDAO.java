package loginandsignup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {
    private static final String SUrl = "jdbc:MySQL://localhost:3306/userjavaauth";
    private static final String SUser = "root";
    private static final String SPass = "";

    public static boolean signUp(String fullName, String email, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            String query = "INSERT INTO user(full_name, email, password)" +
                           "VALUES('" + fullName + "', '" + email + "' , '" + password + "')";

            st.execute(query);

            return true;
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
            return false;
        }
    }

    public static String signIn(String email, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(SUrl, SUser, SPass);
            Statement st = con.createStatement();

            String query = "SELECT * FROM user WHERE email= '" + email + "'";

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String passDb = rs.getString("password");
                String fullName = rs.getString("full_name");

                if (password.equals(passDb)) {
                    return fullName;
                }
            }

            return null; // Incorrect email or password
        } catch (Exception e) {
            System.out.println("Error!" + e.getMessage());
            return null;
        }
    }
}
