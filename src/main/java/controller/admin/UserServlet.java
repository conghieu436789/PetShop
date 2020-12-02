package controller.admin;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "controller.admin.UserServlet", urlPatterns = "/administrator/users")
public class UserServlet extends HttpServlet {
    private final UserDao userDAO = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession();
        if (session.getAttribute("username") == null) {
            resp.sendRedirect("/administrator/login");
        } else {
            String action = req.getParameter("act");
            if (action != null) {
                switch (action) {
                    case "create":
                        createUser(req, resp);
                        break;
                    case "store":
                        storeUser(req, resp);
                        break;
                    case "edit":
                        editUser(req, resp);
                        break;
                    case "update":
                        updateUser(req, resp);
                        break;
                    case "delete":
                        deleteUser(req, resp);
                        break;
                    case "search":
                        searchUser(req, resp);
                        break;
                    default:
                        showAllUser(req, resp);
                }
            } else {
                req.getRequestDispatcher("/admin/error-404.jsp").forward(req, resp);
            }
        }
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/user/create.jsp").forward(req, resp);
    }

    private void storeUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("username").isEmpty()) {
            String errors = "Username is requered!";
        }
        if (req.getParameter("password").isEmpty()) {
            String errors = "Password is requered!";
        }
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setPhone(req.getParameter("phone"));
        user.setGender(req.getParameter("gender"));
        if (userDAO.add(user)) {
            session.setAttribute("msg", "<div class=\"alert alert alert-success\">New user created successfully.</div>");
        } else {
            session.setAttribute("msg", "<div class=\"alert alert alert-danger\">INSERT fails.</div>");
        }
        resp.sendRedirect("/administrator/users?act=create");
    }

    private void editUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userDAO.findById(id);
        if (user == null) {
            session.setAttribute("msg", "<div class=\"alert alert alert-danger\">User not found.</div>");
            resp.sendRedirect("/administrator/users?act=index");
        } else {
            req.setAttribute("user", user);
            req.getRequestDispatcher("/admin/user/edit.jsp").forward(req, resp);
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        HttpSession session = req.getSession();
        int id = Integer.parseInt(req.getParameter("id"));
        if (req.getParameter("username").isEmpty()) {
            String errors = "Username is requered!";
        }
        if (req.getParameter("password").isEmpty()) {
            String errors = "Password is requered!";
        }
        User user = new User();
        user.setId(id);
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setPhone(req.getParameter("phone"));
        user.setGender(req.getParameter("gender"));
        if (userDAO.edit(user)) {
            session.setAttribute("msg", "<div class=\"alert alert alert-success\">Record updated successfully.</div>");
        } else {
            session.setAttribute("msg", "<div class=\"alert alert alert-danger\">UPDATE fails.</div>");
        }
        resp.sendRedirect("/administrator/users?act=edit&id=" + id);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        int id = Integer.parseInt(req.getParameter("id"));
        User user = userDAO.findById(id);
        if (user == null) {
            session.setAttribute("msg", "<div class=\"alert alert alert-danger\">User not found.</div>");
        } else {
            if (userDAO.remove(id)) {
                session.setAttribute("msg", "<div class=\"alert alert alert-success\">Record deleted successfully.</div>");
            } else {
                session.setAttribute("msg", "<div class=\"alert alert alert-danger\">DELETE fails.</div>");
            }
        }
        resp.sendRedirect("/administrator/users?act=index");
    }

    public void showAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentPage = (req.getParameter("currentPage") != null) ? Integer.parseInt(req.getParameter("currentPage")) : 1;
        int recordsPerPage = (req.getParameter("recordsPerPage") != null) ? Integer.parseInt(req.getParameter("recordsPerPage")) : 4;
        List<User> users = userDAO.getRecords(currentPage, recordsPerPage);
        req.setAttribute("users", users);
        int rows = userDAO.getNumberOfRows();
        int nOfPages = Math.round(rows / recordsPerPage);
        req.setAttribute("countUsers", rows);
        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.getRequestDispatcher("/admin/user/index.jsp").forward(req, resp);
    }

    private void searchUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("query");
        int currentPage = (req.getParameter("currentPage") != null) ? Integer.parseInt(req.getParameter("currentPage")) : 1;
        int recordsPerPage = (req.getParameter("recordsPerPage") != null) ? Integer.parseInt(req.getParameter("recordsPerPage")) : 4;
        List<User> users = userDAO.findByName(search, currentPage, recordsPerPage);
        req.setAttribute("users", users);
        int rows = userDAO.getNumberOfRows();
        int nOfPages = (int) Math.floor(rows / recordsPerPage);
        req.setAttribute("countUsers", rows);
        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);
        req.getRequestDispatcher("/admin/user/index.jsp").forward(req, resp);
    }
}
