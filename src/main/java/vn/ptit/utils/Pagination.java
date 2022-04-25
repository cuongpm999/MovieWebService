package vn.ptit.utils;

import vn.ptit.dtos.MovieDTO;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    public static List<MovieDTO> paging(List<MovieDTO> list, int current, int limit) {
        List<MovieDTO> res = new ArrayList<>();
        int totalCount = list.size();

        int start = (current - 1) * limit > totalCount ? totalCount : (current - 1) * limit;
        int end = current * limit > totalCount ? totalCount : current * limit;

        res = list.subList(start, end);
        return res;
    }
}
