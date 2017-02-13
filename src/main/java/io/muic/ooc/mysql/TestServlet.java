package io.muic.ooc.mysql;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "TestServlet",
        urlPatterns = {"/test"}
)
public class TestServlet extends HttpServlet {
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://localhost/ooc_test?"
            + "user=ooc&password=oocpass";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        out.write("hello world".getBytes());
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("user");
        String password = req.getParameter("pass");
        System.out.println("{ User : " + username + "\n  Pass : " + password + "\n}");
        resp.setContentType("application/json");
        try {
            MySQLJava mySQLJava = new MySQLJava(MYSQL_DRIVER,MYSQL_URL);
            if (mySQLJava.validateLogin(username,password) == 0) {

                System.out.println("SUCCESS!");
            }
            else {
                System.out.println("INVALID LOGIN");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


// Get the printwriter object from response to write the required json object to the output stream
        String json_str = String.format("{\"name\":\"%s\",\"email_id\":\"%s\"}",username,password);
        PrintWriter out = resp.getWriter();
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(json_str);
// Assuming your json object is **jsonObject**, perform the following, it will return your json object
        out.print(element);
        out.flush();
    }
}