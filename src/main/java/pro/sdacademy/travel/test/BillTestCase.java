package pro.sdacademy.travel.test;

import pro.sdacademy.travel.entity.Bill;
import pro.sdacademy.travel.repository.BillRepository;

import java.time.LocalDateTime;

public class BillTestCase extends BaseTestCase<Bill, BillRepository> {

    private final ClientTestCase clientTestCase;
    private final TripTestCase tripTestCase;

    public BillTestCase(BillRepository repository, ClientTestCase clientTestCase, TripTestCase tripTestCase) {
        super(repository);
        this.clientTestCase = clientTestCase;
        this.tripTestCase = tripTestCase;
    }

    @Override
    public Bill testCreate() {
        Bill bill = new Bill();
        bill.setClient(clientTestCase.testCreate());
        bill.setTrip(tripTestCase.testCreate());
        repository.save(bill);
        return bill;
    }

    @Override
    public void testUpdate(Bill bill) {
        bill.setCleared(LocalDateTime.now().plusDays(1));
        repository.save(bill);
    }
}
