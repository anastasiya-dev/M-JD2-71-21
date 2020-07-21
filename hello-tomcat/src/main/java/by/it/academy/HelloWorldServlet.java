package by.it.academy;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(value = "/echo", name = "hello-world")
public class HelloWorldServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger("HelloWorldServlet");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            session.setAttribute("test", new Test());
            session.getAttribute("test");

            String contextPath = req.getContextPath();
            log.info(() -> String.format("Context path: %s", contextPath) );

            PrintWriter writer = resp.getWriter();
            writer.println(contextPath + ": Hello from my servlet" + Calendar.getInstance());
            writer.println(req.getHeader("User-Agent"));
            resp.setHeader("My-Name", "Alexandr");

            Cookie myCookie = new Cookie("My-Last-Name", "Veramkovich");
            myCookie.setMaxAge(24*60*60);
            resp.addCookie(myCookie);

        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            PrintWriter writer = resp.getWriter();
            Enumeration<String> parameterNames = req.getParameterNames();
            resp.setContentType("text/html");
            while (parameterNames.hasMoreElements()) {
                String element = parameterNames.nextElement();
                writer.println("<h3>" + element + ": " + req.getParameter(element) + "</h3>");
            }
            writer.println("<a href='/hello-tomcat'>Back</a>");
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
