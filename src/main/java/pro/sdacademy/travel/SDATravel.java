package pro.sdacademy.travel;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pro.sdacademy.travel.entity.*;
import pro.sdacademy.travel.repository.*;
import pro.sdacademy.travel.test.*;

import javax.persistence.EntityManager;

public class SDATravel implements AutoCloseable {

    private final EntityManager entityManager;
    private final BillRepository billRepository;
    private final TripRepository tripRepository;
    private final ClientRepository clientRepository;
    private final TransportRepository transportRepository;
    private final TripOrderRepository tripOrderRepository;

    public SDATravel() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Bill.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Destination.class)
                .addAnnotatedClass(Itinerary.class)
                .addAnnotatedClass(Transport.class)
                .addAnnotatedClass(Trip.class)
                .addAnnotatedClass(TripOrder.class)
                .buildSessionFactory();

        entityManager = sessionFactory.createEntityManager();
        billRepository = new BillRepository(entityManager);
        tripRepository = new TripRepository(entityManager);
        clientRepository = new ClientRepository(entityManager);
        transportRepository = new TransportRepository(entityManager);
        tripOrderRepository = new TripOrderRepository(entityManager);

    }

    public void run() {
        ClientTestCase clientTestCase = new ClientTestCase(clientRepository);
        TestRunner.runTests(clientTestCase);

        TripTestCase tripTestCase = new TripTestCase(tripRepository);
        TestRunner.runTests(tripTestCase);

        TripOrdersTestCase tripOrdersTestCase = new TripOrdersTestCase(tripOrderRepository, clientTestCase, tripTestCase);
        TestRunner.runTests(tripOrdersTestCase);

        BillTestCase billTestCase = new BillTestCase(billRepository, clientTestCase, tripTestCase);
        TestRunner.runTests(billTestCase);

        TransportTestCase transportTestCase = new TransportTestCase(transportRepository);
        TestRunner.runTests(transportTestCase);
    }

    @Override
    public void close() {
        entityManager.close();
    }

    public static void main(String[] args) {
        try (SDATravel travelApp = new SDATravel()) {
            travelApp.run();
        }
    }
}
