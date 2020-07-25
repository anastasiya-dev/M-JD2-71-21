package by.it.academy.client;

import by.academy.it.ClientDao;
import by.academy.it.ClientDaoFactory;
import by.academy.it.ClientDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(ClientServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        log.info("Requested client id: " + id);

        int client_id;
        try {
            client_id = Integer.parseInt(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Client id is not correct");
        }
        log.info("Requested client_id: " + client_id);

        try {
            ClientDao clientDao = ClientDaoFactory.getClientDao("mysql");
            ClientDto clientDto = clientDao.read(client_id);
            resp.getWriter().println(clientDto);
        } catch (SQLException e) {
            throw new ServletException("Database connection failed", e);
        }

    }
}
