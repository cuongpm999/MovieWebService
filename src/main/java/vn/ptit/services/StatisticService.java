package vn.ptit.services;

import vn.ptit.dtos.ChartDTO;

public interface StatisticService {
    public ChartDTO statisticIncomeLast5Month();
    public ChartDTO statisticUserTotalMoney();
    public ChartDTO statisticMovieViewCount();
    public long totalOrder();
    public long totalUser();
    public double totalMoney();
    public long totalView();

}
