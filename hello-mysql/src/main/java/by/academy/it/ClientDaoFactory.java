package by.academy.it;

import by.academy.it.mysql.ClientDaoImpl;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public class ClientDaoFactory {

    private static ClientDaoImpl clientDao;

    public static ClientDao getClientDao(String database) throws SQLException {
        if ("mysql".equals(database)) {
            if (clientDao == null) {
                clientDao = new ClientDaoImpl();
            }
            return clientDao;
        }
        throw new InvalidParameterException("No such database implemented" + database);
    }

}
