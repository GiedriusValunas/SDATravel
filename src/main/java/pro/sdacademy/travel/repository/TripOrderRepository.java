package pro.sdacademy.travel.repository;

import pro.sdacademy.travel.SDATravelException;
import pro.sdacademy.travel.entity.TripOrder;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TripOrderRepository implements CRUDRepository<Integer, TripOrder> {

    private final Connection connection;
    private final TripRepository tripRepository;
    private final ClientRepository clientRepository;

    public TripOrderRepository(
            Connection connection,
            TripRepository tripRepository,
            ClientRepository clientRepository
    ) {
        this.connection = connection;
        this.tripRepository = tripRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(TripOrder order) {
        String sql = "INSERT INTO trip_orders (client_id, trip_id, trip_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getClient().getId());
            stmt.setInt(2, order.getTrip().getId());
            stmt.setDate(3, Date.valueOf(order.getTripDate()));
            stmt.execute();
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    @Override
    public Optional<TripOrder> find(Integer id) {
        String sql = "SELECT id, client_id, trip_id, trip_date FROM trip_orders WHERE id = ?";
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
    public List<TripOrder> findAll() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "SELECT id, client_id, trip_id, trip_date FROM trip_orders";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                List<TripOrder> results = new ArrayList<>();
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
    public void update(TripOrder order) {
        String sql = "UPDATE trip_orders SET client_id=?, trip_id=?, trip_date=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getClient().getId());
            stmt.setInt(2, order.getTrip().getId());
            stmt.setDate(3, Date.valueOf(order.getTripDate()));
            stmt.setInt(4, order.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    @Override
    public void delete(TripOrder order) {
        String sql = "DELETE FROM trip_orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }

    private TripOrder resultSetToClient(ResultSet resultSet) {
        try {
            TripOrder order = new TripOrder();
            order.setId(resultSet.getInt("id"));
            order.setClient(clientRepository.find(resultSet.getInt("client_id"))
                    .orElseThrow(SDATravelException::new));
            order.setTrip(tripRepository.find(resultSet.getInt("trip_id"))
                    .orElseThrow(SDATravelException::new));
            order.setTripDate(Instant.ofEpochMilli(resultSet.getDate("trip_date").getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
            return order;
        } catch (SQLException e) {
            throw new SDATravelException(e);
        }
    }
}
