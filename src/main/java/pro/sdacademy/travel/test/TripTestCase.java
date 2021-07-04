package pro.sdacademy.travel.test;

import pro.sdacademy.travel.entity.Destination;
import pro.sdacademy.travel.entity.Itinerary;
import pro.sdacademy.travel.entity.Trip;
import pro.sdacademy.travel.repository.TripRepository;

import java.time.LocalDateTime;

public class TripTestCase extends BaseTestCase<Trip, TripRepository> {

    public TripTestCase(TripRepository repository) {
        super(repository);
    }

    @Override
    public Trip testCreate() {
        Trip trip = new Trip();
        trip.setItinerary(createItinerary("LT", "UK"));
        trip.setPrice(999.99);
        repository.save(trip);
        return trip;
    }

    private Itinerary createItinerary(String from, String to) {
        Itinerary itinerary = new Itinerary();
        itinerary.setDestinationFrom(new Destination(from));
        itinerary.setDestinationTo(new Destination(to));
        itinerary.setTravelDateTime(LocalDateTime.now());
        return itinerary;
    }

    @Override
    public void testUpdate(Trip trip) {
        trip.setPrice(299.49);
        repository.save(trip);
    }
}
