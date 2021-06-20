package pro.sdacademy.travel.test;

import pro.sdacademy.travel.SDATravelException;
import pro.sdacademy.travel.entity.TripOrder;
import pro.sdacademy.travel.repository.TripOrderRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TripOrdersTestCase extends BaseTestCase<TripOrder, TripOrderRepository> {

    private final ClientTestCase clientTestCase;
    private final TripTestCase tripTestCase;

    public TripOrdersTestCase(
            TripOrderRepository repository,
            ClientTestCase clientTestCase,
            TripTestCase tripTestCase
    ) {
        super(repository);
        this.clientTestCase = clientTestCase;
        this.tripTestCase = tripTestCase;
    }

    @Override
    public TripOrder testCreate() {
        TripOrder order = new TripOrder();
        order.setClient(clientTestCase.testCreate());
        order.setTrip(tripTestCase.testCreate());
        order.setTripDate(LocalDate.now().plus(6, ChronoUnit.MONTHS));
        repository.create(order);
        return repository.findAll().stream().findFirst().orElseThrow(SDATravelException::new);
    }

    @Override
    public void testUpdate() {
        TripOrder o = repository.findAll().stream()
                .skip(1)
                .findFirst()
                .orElseThrow(SDATravelException::new);
        o.setTripDate(LocalDate.now().plus(9, ChronoUnit.MONTHS));
        repository.save(o);
    }

    @Override
    public void testDelete() {
        TripOrder o = repository.findAll().stream()
                .skip(2)
                .findFirst()
                .orElseThrow(SDATravelException::new);
        repository.delete(o);
    }
}
