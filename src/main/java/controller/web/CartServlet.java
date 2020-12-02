package controller.web;

import dao.OrderDao;
import dao.ProductDao;
import model.entities.Customer;
import model.entities.Order;
import model.entities.OrderDetail;
import model.entities.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private ProductDao productDao= new ProductDao();
    private OrderDao orderDao = new OrderDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action==null) {
            action="";
        }
        switch (action) {
            case "add":
                doAdd(request, response);
                break;
            case "payment":
                doInsertOrder(request, response);
                break;
            case "view":

                break;
            default:
                goPage(request, response);
        }

    }

    protected void goPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        if (page.equals("product")) {
            request.getRequestDispatcher("/products").forward(request, response);
        }
    }

    protected void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id")!=null) {
            int id = Integer.parseInt(request.getParameter("id"));
            int quantity=1;
            Product product = productDao.selectProduct(id);
            if (product!=null) {
                if (request.getParameter("quantity")!=null) {
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }
            }
            HttpSession session = request.getSession();
            if (session.getAttribute("order")==null) {
                double sum=0;
                Order order = new Order();
                List<OrderDetail> listOrderDetails = new ArrayList<>();
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(quantity);
                orderDetail.setPrice(product.getRealPrice());
                orderDetail.setTotal(product.getRealPrice() * quantity);
                listOrderDetails.add(orderDetail);
                for (OrderDetail orderDetail1:listOrderDetails) {
                    sum+=orderDetail1.getTotal();
                }
                order.setTotalOrder(sum);
                order.setList(listOrderDetails);
                session.setAttribute("order", order);
            } else {
                double sum=0;
                Order order = (Order) session.getAttribute("order");
                List<OrderDetail> listOrderDetails = order.getList();
                boolean checkExist =false;
                for (OrderDetail orderDetail:listOrderDetails) {
                    if(orderDetail.getProduct().getId() == product.getId()) {
                        orderDetail.setQuantity(orderDetail.getQuantity()+1);
                        checkExist=true;
                    }
                }
                if (checkExist=false) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setQuantity(quantity);
                    orderDetail.setProduct(product);
                    orderDetail.setPrice(product.getRealPrice());
                    orderDetail.setTotal(product.getRealPrice() * quantity);
                    listOrderDetails.add(orderDetail);
                    for (OrderDetail orderDetail1:listOrderDetails) {
                        sum+=orderDetail1.getTotal();
                    }
                    order.setTotalOrder(sum);
                    order.setList(listOrderDetails);
                }
                session.setAttribute("order", order);
            }
        }
        goPage(request,response);
    }
    protected void doInsertOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Order order = (Order) session.getAttribute("order");
        Customer customer= (Customer) session.getAttribute("user");
        if (customer!=null && order!=null) {
            order.setCustomer_id(customer.getId());
            try {
                orderDao.insertOrder(order);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        session.removeAttribute("order");
        request.getRequestDispatcher("/home").forward(request, response);
    }
}
