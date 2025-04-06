package com.example.bibishop.controller.dashboard;


import com.example.bibishop.dto.HDCTSearchRequest;
import com.example.bibishop.dto.dashboard.RevenueData;
import com.example.bibishop.dto.dashboard.RevenueDataReq;
import com.example.bibishop.service.DashboardService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/dashboard")
public class DashBoardController {

  @Autowired
  DashboardService dashboardService;

  @GetMapping("")
  public String getDashDetails(Model model) {
    /**
     * Populate company revenue data
     */
    model.addAttribute("cr", dashboardService.getTodayRevenueDash());

    /**
     * Populate Employee info
     */
    model.addAttribute("ei",
        dashboardService.hdctSerchResponse(new HDCTSearchRequest(null, null, null),
            Pageable.ofSize(5)).getContent().stream().limit(5).collect(Collectors.toList()));
    /**
     * Populate Product Category data
     */
    model.addAttribute("bc", dashboardService.getBestCategory());

    /**
     * Populate Order Received data
     */
    model.addAttribute("or", dashboardService.getAllOrderReceived());

    /**
     * Populate Order Status data
     */
    model.addAttribute("os", dashboardService.getOrderCollection());

    return "admin/dashboard/index";
  }

  @PostMapping("chart")
  public List<RevenueData> getRevenueDate(@RequestBody RevenueDataReq revenueDataReq) {
    List<RevenueData> revenueData = new ArrayList<>();
    RevenueData revenueData1 = new RevenueData(LocalDate.now(), 1000);
    RevenueData revenueData2 = new RevenueData(LocalDate.now(), 1000);
    RevenueData revenueData3 = new RevenueData(LocalDate.now(), 1000);
    RevenueData revenueData4 = new RevenueData(LocalDate.now(), 1000);
    RevenueData revenueData5 = new RevenueData(LocalDate.now(), 1000);
    RevenueData revenueData6 = new RevenueData(LocalDate.now(), 1000);
    revenueData.addAll(
        Arrays.asList(revenueData1, revenueData2, revenueData3, revenueData4, revenueData5,
            revenueData6));
    return revenueData;
  }
}
