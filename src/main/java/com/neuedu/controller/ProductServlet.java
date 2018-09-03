package com.neuedu.controller;

import com.neuedu.pojo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product p1 = new Product(1,"手机");
        Product p2 = new Product(2,"电脑");

        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p1);
        request.setAttribute("productlist",products);
        request.getRequestDispatcher("product.jsp").forward(request,response);
    }
}
