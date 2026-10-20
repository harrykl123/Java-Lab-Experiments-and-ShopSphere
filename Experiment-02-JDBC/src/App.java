import java.sql.*;

public class App {
    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded Successfully!");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Git",
                    "root",
                    "manangupta@123");

            System.out.println("Connected Successfully!");

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO student (id, name, branch) VALUES (?, ?, ?)");

            ps.setInt(1, 101);
            ps.setString(2, "Rahul");
            ps.setString(3, "CSE");

            ps.executeUpdate();

            System.out.println("Record Inserted");

            ps.close();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT * FROM student");

            System.out.println("\nStudent Records:");

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String branch = rs.getString("branch");

                System.out.println(id + " " + name + " " + branch);
            }
            rs.close();
            st.close();
            con.close();

            System.out.println("\nConnection Closed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}