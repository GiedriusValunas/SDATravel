package pro.sdacademy.travel.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "itinerary")
public class Itinerary implements DbEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Trip trip;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Destination destinationFrom;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Destination destinationTo;

    @Column(name = "travel_date")
    private LocalDateTime travelDateTime;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Destination getDestinationFrom() {
        return destinationFrom;
    }

    public void setDestinationFrom(Destination destinationFrom) {
        this.destinationFrom = destinationFrom;
    }

    public Destination getDestinationTo() {
        return destinationTo;
    }

    public void setDestinationTo(Destination destinationTo) {
        this.destinationTo = destinationTo;
    }

    public LocalDateTime getTravelDateTime() {
        return travelDateTime;
    }

    public void setTravelDateTime(LocalDateTime travelDateTime) {
        this.travelDateTime = travelDateTime;
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "id=" + id +
                ", trip=" + trip +
                ", destinationFrom=" + destinationFrom +
                ", destinationTo=" + destinationTo +
                ", travelDateTime=" + travelDateTime +
                '}';
    }
}
