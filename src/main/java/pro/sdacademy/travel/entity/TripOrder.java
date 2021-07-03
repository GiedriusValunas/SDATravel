package pro.sdacademy.travel.entity;

import java.time.LocalDate;

public class TripOrder implements DbEntity<Integer> {

    private Integer id;
    private Client client;
    private Trip trip;
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
                ", client=" + client +
                ", trip=" + trip +
                ", tripDate=" + tripDate +
                '}';
    }
}
