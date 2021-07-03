package pro.sdacademy.travel.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trip_orders")
public class TripOrder implements DbEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    private Trip trip;

    @Column(name = "trip_date")
    private LocalDate tripDate;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }

    @Override
    public String toString() {
        return "TripOrder{" +
                "id=" + id +
                ", clientId=" + client.getId() +
                ", tripId=" + trip.getId() +
                ", tripDate=" + tripDate +
                '}';
    }
}
