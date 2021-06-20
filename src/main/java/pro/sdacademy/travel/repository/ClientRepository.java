package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.SDATravelException;
import pro.sdacademy.travel.entity.Client;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements CRUDRepository<Integer, Client> {

    private final Connection connection;

    public ClientRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Client client) {
        String sql = "INSERT INTO clients (name, surname, birthdate) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setDate(3, Date.valueOf(client.getBirthdate()));
            stmt.execute();
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    @Override
    public Optional<Client> find(Integer id) {
        String sql = "SELECT id, name, surname, birthdate FROM clients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(resultSetToClient(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    @Override
    public List<Client> findAll() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "SELECT id, name, surname, birthdate FROM clients";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                List<Client> results = new ArrayList<>();
                while (rs.next()) {
                    results.add(resultSetToClient(rs));
                }
                return results;
            }
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    @Override
    public void update(Client entity) {

    }

    @Override
    public void delete(Client entity) {
        String sql = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    private static Client resultSetToClient(ResultSet resultSet) {
        try {
            Client client = new Client();
            client.setId(resultSet.getInt("id"));
            client.setName(resultSet.getString("name"));
            client.setSurname(resultSet.getString("surname"));
            client.setBirthdate(Instant.ofEpochMilli(resultSet.getDate("birthdate").getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            return client;
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }
}
