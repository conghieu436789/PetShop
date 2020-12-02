package controller.admin;

import dao.DBConnection;
import dao.admin.LoginDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/administrator/login")
public class LoginServlet extends HttpServlet {
    private final DBConnection dbConn = new DBConnection();
    private final LoginDao loginDAO = new LoginDao(dbConn);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (loginDAO.checkLogin(username, password)) {
            session.setAttribute("username", username);
            resp.sendRedirect("/");
        } else {
            session.setAttribute("msg", "<div class=\"alert alert-danger\">Invalid username or password</div>");
            resp.sendRedirect("/administrator/login");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello World!");
        req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
    }
}