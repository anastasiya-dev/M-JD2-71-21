package by.academy.it;

import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main2 {

    private static Logger log = Logger.getLogger(Main2.class.getName());

    public static void main(String[] args) {
        try {
            ClientDao clientDao = ClientDaoFactory.getClientDao("mysql");

            ClientDto client = new ClientDto();
            client.setId(1);
            client.setName("Name");
            client.setSecondName("Second Name");
            client.setEmail("name@mail.ru");
            client.setDateOfBirth(Date.valueOf("1977-05-24"));
            client.setGender('n');
            log.info("Calling dao: " + clientDao);
            clientDao.create(client);

        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            System.exit(-1);
        }  catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            System.exit(-1);
        } finally {
            log.info("Finished successfully");
            System.exit(0);
        }
    }

}
