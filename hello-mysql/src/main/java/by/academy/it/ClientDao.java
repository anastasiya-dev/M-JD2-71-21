package by.academy.it;

import java.util.List;

public interface ClientDao {

    int create(ClientDto clientDto);

    ClientDto read(int id);

    List<ClientDto> readAll();

    void update(ClientDto clientDto);

    boolean delete(ClientDto clientDto);

    boolean delete(int id);
}
