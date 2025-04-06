package com.example.bibishop.service;


import com.example.bibishop.dto.EmployeeInformation;
import com.example.bibishop.dto.HDCTSearchRequest;
import com.example.bibishop.dto.HDCTSearchResponse;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DashboardService {

    HashMap<String, Object> getTodayRevenueDash();

    HashMap<String, Object> getBestCategory();

    HashMap<String, Object> getAllOrderReceived();

    HashMap<String, Object> getOrderCollection();

    List<EmployeeInformation> getAllEmployee();
    Page<HDCTSearchResponse> hdctSerchResponse(HDCTSearchRequest request, Pageable pageable);
}
