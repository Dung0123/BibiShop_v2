package com.example.bibishop.service;

import java.math.BigDecimal;
import java.util.Map;

public interface IStatisticService {
    Map<String, Map<String, BigDecimal>> statisticByMonth();
}
