package pro.sdacademy.travel.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
public class Bill implements DbEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Client client;

    @OneToOne
    private Trip trip;

    private LocalDateTime created = LocalDateTime.now();

    private LocalDateTime cleared;


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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getCleared() {
        return cleared;
    }

    public void setCleared(LocalDateTime cleared) {
        this.cleared = cleared;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", client=" + client +
                ", trip=" + trip +
                ", created=" + created +
                ", cleared=" + cleared +
                '}';
    }
}
