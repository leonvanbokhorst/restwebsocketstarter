package nl.fontys.s3.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class simpleHandler extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

        resp.setStatus(HttpServletResponse.SC_OK);
        final PrintWriter respWriter = resp.getWriter();
        respWriter.println("<h1>Hello from " + simpleHandler.class.getName() + "</h1>");
    }
}
