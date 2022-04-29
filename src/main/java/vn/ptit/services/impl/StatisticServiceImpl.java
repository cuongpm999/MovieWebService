package vn.ptit.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.ptit.dtos.ChartDTO;
import vn.ptit.entities.Movie;
import vn.ptit.entities.Order;
import vn.ptit.repositories.*;
import vn.ptit.services.StatisticService;
import vn.ptit.utils.RevenuePerMonthUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CountViewRepository countViewRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PaymentRepository paymentRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ChartDTO statisticIncomeLast5Month() {
        List<Order> orders = orderRepository.findAll();
        String label[] = new String[5];
        double data[] = new double[5];

        label[0] = RevenuePerMonthUtil.getStringMonth4();
        label[1] = RevenuePerMonthUtil.getStringMonth3();
        label[2] = RevenuePerMonthUtil.getStringMonth2();
        label[3] = RevenuePerMonthUtil.getStringMonth1();
        label[4] = RevenuePerMonthUtil.getStringMonth();

        data[0] = RevenuePerMonthUtil.getTotalMoneyMonth4(orders);
        data[1] = RevenuePerMonthUtil.getTotalMoneyMonth3(orders);
        data[2] = RevenuePerMonthUtil.getTotalMoneyMonth2(orders);
        data[3] = RevenuePerMonthUtil.getTotalMoneyMonth1(orders);
        data[4] = RevenuePerMonthUtil.getTotalMoneyMonth(orders);

        return new ChartDTO(label, data);
    }

    @Override
    public ChartDTO statisticUserTotalMoney() {
        String sql = "SELECT users.fullName, A.SoTienMua FROM users,(SELECT SUM(payments.totalMoney) AS SoTienMua, orders.userId FROM orders,payments WHERE orders.paymentId = payments.id GROUP BY orders.userId) AS A WHERE users.id = A.userId";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> records = query.getResultList();

        String label[] = new String[5];
        double data[] = new double[5];

        Map<String, Double> map = new HashMap<>();

        for (int i = 0; i < records.size(); i++) {
            map.put(records.get(i)[0].toString(), Double.parseDouble(records.get(i)[1].toString()));
        }

        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return Double.compare(o2.getValue(), o1.getValue());
            }
        });

        int i = 0;
        for (Map.Entry<String, Double> m : list) {
            if (i == 5) break;
            label[i] = m.getKey();
            data[i] = m.getValue();
            i++;
        }

        return new ChartDTO(label, data);
    }

    @Override
    public ChartDTO statisticMovieViewCount() {
        List<Movie> movies = movieRepository.findByStatusTrue();

        String label[] = new String[5];
        double data[] = new double[5];

        Map<String, Double> map = new HashMap<>();

        movies.forEach(m -> {
            double countView = countViewRepository.countCountViewByMovie_Id(m.getId());
            map.put(m.getName(), countView);
        });

        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return Double.compare(o2.getValue(), o1.getValue());
            }
        });

        int i = 0;
        for (Map.Entry<String, Double> m : list) {
            if (i == 5) break;
            label[i] = m.getKey();
            data[i] = m.getValue();
            i++;
        }

        return new ChartDTO(label, data);
    }

    @Override
    public long totalOrder() {
        return orderRepository.count();
    }

    @Override
    public long totalUser() {
        return userRepository.count();
    }

    @Override
    public double totalMoney() {
        return paymentRepository.totalMoney();
    }

    @Override
    public long totalView() {
        return countViewRepository.count();
    }
}
