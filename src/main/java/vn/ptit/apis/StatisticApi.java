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
}
