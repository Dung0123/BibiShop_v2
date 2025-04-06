package com.example.bibishop.service;


import com.example.bibishop.dto.EmployeeInformation;
import com.example.bibishop.dto.HDCTSearchRequest;
import com.example.bibishop.dto.HDCTSearchResponse;
import com.example.bibishop.repository.HoaDonChiTietRepository;
import com.example.bibishop.repository.HoaDonRepository;
import com.example.bibishop.repository.custom.HoadonRepoCustom;
import com.example.bibishop.repository.custom.impl.HoadonRepoCustomImpl;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final HoaDonRepository hoaDonRepository;
    private final HoaDonChiTietRepository hoaDonChiTietRepository;

    @Override
    public HashMap<String, Object> getTodayRevenueDash() {
        HashMap<String, Object> companyRevenueMap = new HashMap<>();
        Map<String, String> revenueInYear = hoaDonRepository.revenueStatisticInYear();
        String labelValues = revenueInYear.get("crLabels").replace("\"","");
        companyRevenueMap.put("crLabels", labelValues);
        companyRevenueMap.put("crRevenue", revenueInYear.get("crRevenue"));

        return companyRevenueMap;
    }

//    @Override
//    public HashMap<String, Object> getBestCategory() {
//        HashMap<String, Object> bestProductMap = new HashMap<>();
//        bestProductMap.put("bcLabels", "[Electronics, Phone/Ipad, Purses, Jwellery]");
//        bestProductMap.put("bcPercents", "[30, 40, 20, 10]");
//        return bestProductMap;
//    }
    @Override
    public HashMap<String, Object> getBestCategory() {
        HashMap<String, Object> bestProductMap = new HashMap<>();
        Map<String, String> bestCategory = hoaDonRepository.bestCategory();
        bestProductMap.put("bcLabels", bestCategory.get("bcLabels"));
        bestProductMap.put("bcPercents", bestCategory.get("bcPercents"));
        return bestProductMap;
    }
    @Override
    public HashMap<String, Object> getAllOrderReceived() {
        HashMap<String, Object> orderReceivedMap = new HashMap<>();
        Map<String, String> ordersByMonth = hoaDonRepository.ordersByMonth();
        String orLabelValues = ordersByMonth.get("thang").replace("\"", "");
        orderReceivedMap.put("orLabels", orLabelValues);
        orderReceivedMap.put("orOrders", ordersByMonth.get("so_luong_order"));
        return orderReceivedMap;
    }

    @Override
    public HashMap<String, Object> getOrderCollection() {
        HashMap<String, Object> orderStatusMap = new HashMap<>();

        int totalNewOrders = hoaDonRepository.countByNgayTaoBetween(LocalDateTime.now().minusMonths(1),LocalDateTime.now());
        double totalRevenue = hoaDonRepository.totalRevenueByMonth();
        int totalShippedOrders = hoaDonRepository.countDeliveryOrder("DANG_GIAO_HANG");
        int totalReturnInitiatedOrders = hoaDonRepository.countDeliveryOrder("TRA_HANG");

        Locale locale = new Locale("vi", "VN");
        NumberFormat CurrencyFormatter = NumberFormat.getCurrencyInstance(locale);

        orderStatusMap.put("totalNewOrders", totalNewOrders);
        orderStatusMap.put("totalRevenue", CurrencyFormatter.format(totalRevenue));
        orderStatusMap.put("totalShippedOrders", totalShippedOrders);
        orderStatusMap.put("totalReturnInitiatedOrders", totalReturnInitiatedOrders);

        return orderStatusMap;
    }

    @Override
    public List<EmployeeInformation> getAllEmployee() {
        EmployeeInformation employeeInformation = EmployeeInformation.builder()
                .name("Cuong")
                .position("Admin")
                .age(25)
                .salary(1000)
                .startDate(new Date())
                .build();
        return Arrays.asList(employeeInformation);
    }

    @Override
    public Page<HDCTSearchResponse> hdctSerchResponse(HDCTSearchRequest request, Pageable pageable) {
        long count = hoaDonChiTietRepository.countSearch(request);
        List<HDCTSearchResponse> content = new ArrayList<>();
        if (count > 0) {
            content = hoaDonChiTietRepository.search(request, pageable);
        }
        return new PageImpl<>(content, pageable, count);
    }
}
