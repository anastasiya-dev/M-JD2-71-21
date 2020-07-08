package by.academy.it.mysql;

import by.academy.it.ClientDao;
import by.academy.it.ClientDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDaoImpl implements ClientDao {

    private static Logger log =  Logger.getLogger(ClientDaoImpl.class.getName());

    private Connection connection;

    public ClientDaoImpl() throws SQLException {
        this.connection = MySqlDataSource.getConnection();
    }

    @Override
    public int create(ClientDto clientDto) {
        log.info("Creating new client: " + clientDto);
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement("insert into client.clients " +
                                                   "values (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, clientDto.getId());
            preparedStatement.setString(2, clientDto.getName());
            preparedStatement.setString(3, clientDto.getSecondName());
            preparedStatement.setString(4, clientDto.getEmail());
            preparedStatement.setDate(5, clientDto.getDateOfBirth());
            preparedStatement.setString(6, String.valueOf(clientDto.getGender()));

            boolean result = preparedStatement.execute();
            if(result) return clientDto.getId();
        } catch (SQLException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
        return 0;
    }

    @Override
    public ClientDto read(int id) {
        return null;
    }

    @Override
    public List<ClientDto> readAll() {
        return null;
    }

    @Override
    public void update(ClientDto clientDto) {

    }

    @Override
    public boolean delete(ClientDto clientDto) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
