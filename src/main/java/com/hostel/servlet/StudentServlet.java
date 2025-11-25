package com.hostel.servlet;

import com.hostel.dao.StudentDAO;
import com.hostel.model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Student s = new Student();
        s.setName(req.getParameter("name"));
        s.setRoomNo(req.getParameter("room"));
        s.setCourse(req.getParameter("course"));
        s.setAddress(req.getParameter("address"));
        s.setPhone(req.getParameter("phone"));

        StudentDAO dao = new StudentDAO();

        if (dao.addStudent(s)) {
            resp.sendRedirect("success.jsp");
        } else {
            resp.getWriter().println("Error adding student");
        }
    }
}
