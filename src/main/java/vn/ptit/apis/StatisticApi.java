package vn.ptit.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.ptit.dtos.ChartDTO;
import vn.ptit.services.StatisticService;

@RestController
@RequestMapping(path = "/api/statistic")
@CrossOrigin(origins = "*")
public class StatisticApi {
    @Autowired
    private StatisticService statisticService;

    @GetMapping(path = "/income-last-5-month", produces = "application/json")
    public ResponseEntity<ChartDTO> statisticIncomeLast5Month() {
        ChartDTO res = statisticService.statisticIncomeLast5Month();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/user-total-money", produces = "application/json")
    public ResponseEntity<ChartDTO> statisticUserTotalMoney() {
        ChartDTO res = statisticService.statisticUserTotalMoney();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/movie-view-count", produces = "application/json")
    public ResponseEntity<ChartDTO> statisticMovieViewCount() {
        ChartDTO res = statisticService.statisticMovieViewCount();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping(path = "/total-order", produces = "application/json")
    public ResponseEntity<Long> statisticTotalOrder() {
        return new ResponseEntity<>(statisticService.totalOrder(), HttpStatus.OK);
    }

    @GetMapping(path = "/total-user", produces = "application/json")
    public ResponseEntity<Long> statisticTotalUser() {
        return new ResponseEntity<>(statisticService.totalUser(), HttpStatus.OK);
    }

    @GetMapping(path = "/total-money", produces = "application/json")
    public ResponseEntity<Double> statisticTotalMoney() {
        return new ResponseEntity<>(statisticService.totalMoney(), HttpStatus.OK);
    }

    @GetMapping(path = "/total-view", produces = "application/json")
    public ResponseEntity<Long> statisticTotalView() {
        return new ResponseEntity<>(statisticService.totalView(), HttpStatus.OK);
    }

}
