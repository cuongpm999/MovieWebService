package vn.ptit.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.ptit.dtos.ChartDTO;
import vn.ptit.entities.Order;
import vn.ptit.repositories.OrderRepository;
import vn.ptit.services.StatisticService;
import vn.ptit.utils.RevenuePerMonthUtil;

import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired private OrderRepository orderRepository;
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
}
