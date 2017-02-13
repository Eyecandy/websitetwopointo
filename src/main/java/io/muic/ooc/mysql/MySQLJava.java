package io.muic.ooc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLJava {

    enum TestTableColumns {
        id, TEXT;
    }

    private final String jdbcDriverStr;
    private final String jdbcURL;

    private Connection connection;
    private Connection connection2;
    private Statement statement;
    private Statement statement2;
    private ResultSet resultSet;
    private ResultSet resultSet2;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;
    String sql;

    public MySQLJava(String jdbcDriverStr, String jdbcURL) {
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;

    }

    public void readData() throws Exception {
        try {
            //Class.forName(this.jdbcDriverStr);
//            connection = DriverManager.getConnection("jdbc:mysql//localhost/ok?user=root&password=horde299");

            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ok","root","horde299");
            connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","horde299");

            //statement = connection.createStatement();
            statement2 = connection2.createStatement();
            resultSet2 = statement2.executeQuery("select * from users");
            //resultSet = statement.executeQuery("select * from HelloWorld;");
            //removeUser("peter");
            //addUser("peter","hollow");

            //addSession("peter");
            //displayUsers();

            //preparedStatement2 = connection2.prepareStatement("insert into test.users values ('admin','password')");
            //preparedStatement = connection.prepareStatement("insert into ok.HelloWorld values (13,19)");
            //preparedStatement2.executeUpdate();
            //getResultSet(resultSet2);
//            while (resultSet2.next()) {
//                //Integer email = resultSet2.getInt("Email");
//                String email2 = resultSet2.getString("username");
//                System.out.println(email2);
//            }
            
            //System.out.println("Done");
        } finally {
            close();
        }
    }


    public int validateLogin(String username,String password) throws  Exception {
        readData();
        while (resultSet2.next()) {
            String existingUserName = resultSet2.getString("username");
            String existingPassword = resultSet2.getString("password");

            if (existingUserName.equals(username) && existingPassword.equals(password)) {
                return 0;
            }
        }
        return -1;
    };

    public void addSession(String username) throws  Exception {
        String sql = "UPDATE test.users set session=1 where username="+"'"+username+"'";
        System.out.println(sql);
        preparedStatement2 = connection2.prepareStatement(sql);
        preparedStatement2.executeUpdate();
    }
    public void removeSession(String username) throws  Exception {
        String sql = "UPDATE test.users set session=0 where username="+"'"+username+"'";
        preparedStatement2 = connection2.prepareStatement(sql);
        preparedStatement2.executeUpdate();


    }
    public void addUser(String username, String password) throws Exception {
        String sql = "insert into test.users values ('"+username+"','"+password+"','0')";
        System.out.println(sql);
        preparedStatement2 = connection2.prepareStatement(sql);
        preparedStatement2.executeUpdate();
    }

    public void removeUser(String username) throws Exception{
        sql = "DELETE FROM test.users where username=" +"'"+username+"'";
        preparedStatement2 = connection2.prepareStatement(sql);
        preparedStatement2.executeUpdate();
    }

    public void displayUsers()  throws Exception{

        while (resultSet2.next()) {
            String existingUserName = resultSet2.getString("username");
            String existingPassword = resultSet2.getString("password");

        }
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }
}
