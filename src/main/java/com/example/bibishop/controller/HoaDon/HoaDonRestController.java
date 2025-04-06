package com.example.bibishop.controller.HoaDon;

import com.example.bibishop.dto.HoaDonDTO;
import com.example.bibishop.service.server.HoaDon.HoaDonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class HoaDonRestController {

  @Autowired
  private HoaDonService hoaDonService;

  @ResponseBody
  @PostMapping("/admin/api/order")
  public HoaDonDTO orderHoaDon(@RequestBody HoaDonDTO hoaDonDTO) {
    log.info("Receive hoaDonDTO: {}", hoaDonDTO);
    return hoaDonService.createHoaDon(hoaDonDTO);
  }

}
