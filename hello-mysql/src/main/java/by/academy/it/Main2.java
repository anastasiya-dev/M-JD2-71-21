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
            //Create
            ClientDto client = new ClientDto();
            int id = clientDao.getMaxId() + 1;
            client.setId(id);
            client.setName("Name");
            client.setSecondName("Second Name");
            client.setEmail("name@mail.ru");
            client.setDateOfBirth(Date.valueOf("1977-05-24"));
            client.setGender('n');
            log.info("Calling create: " + client);
            clientDao.create(client);

            //Read created
            ClientDto clientDtoCreated = clientDao.read(id);
            log.info("Read after created: " + clientDtoCreated);

            //Read max id
            log.info("Max id in database: " + clientDao.getMaxId());

            //Update created
            clientDtoCreated.setName("Name 101");
            clientDtoCreated.setSecondName("Second Name 100");
            clientDao.update(clientDtoCreated);

            //Read updated
            ClientDto clientDtoUpdated = clientDao.read(id);
            log.info("Read after update" + clientDtoUpdated);

            //Delete updated
            //clientDao.delete(clientDtoUpdated);

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
