package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.SDATravelException;
import pro.sdacademy.travel.entity.Client;
import pro.sdacademy.travel.entity.Trip;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripRepository implements CRUDRepository<Integer, Trip> {

    private final Connection connection;

    public TripRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Trip trip) {
        String sql = "INSERT INTO trips (destination, price) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, trip.getDestination());
            stmt.setDouble(2, trip.getPrice());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    @Override
    public Optional<Trip> find(Integer id) {
        String sql = "SELECT id, destination, price FROM trips WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(resultSetToTrip(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    @Override
    public List<Trip> findAll() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "SELECT id, destination, price FROM trips";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                List<Trip> results = new ArrayList<>();
                while (rs.next()) {
                    results.add(resultSetToTrip(rs));
                }
                return results;
            }
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    @Override
    public void update(Trip entity) {
        String sql = "UPDATE trips SET destination=?, price=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getDestination());
            stmt.setDouble(2, entity.getPrice());
            stmt.setInt(3, entity.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    @Override
    public void delete(Trip entity) {
        String sql = "DELETE FROM trips WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    private static Trip resultSetToTrip(ResultSet resultSet) {
        try {
            Trip trip = new Trip();
            trip.setId(resultSet.getInt("id"));
            trip.setDestination(resultSet.getString("destination"));
            trip.setPrice(resultSet.getDouble("price"));
            return trip;
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }
}
