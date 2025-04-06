package com.example.bibishop.controller.Magiamgia.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePercentageDiscountDTO {

  @NotNull(message = "Tên mã giảm giá không được để trống")
  @Size(min = 3, max = 255, message = "Tên mã giảm giá phải từ 3 đến 255 ký tự")
  private String ten;

  @Size(max = 20, message = "Mã giảm giá phải dưới 20 ký tự")
  private String ma; // Optional, will be randomized if null

  @NotNull(message = "Phần trăm giảm không được để trống")
  @Min(value = 1, message = "Phần trăm giảm phải lớn hơn 0")
  @Max(value = 100, message = "Phần trăm giảm không được vượt quá 100")
  private BigDecimal phanTramGiam;

  @NotNull(message = "Giá trị giảm tối đa không được để trống")
  @Min(value = 100, message = "Giá trị giảm tối đa phải lớn hơn 100")
  private BigDecimal giamToiDa;

  @NotNull(message = "Số lượng không được để trống")
  @Min(value = 1, message = "Số lượng phải lớn hơn 0")
  private Integer soLuong;

  @NotNull(message = "Ngày bắt đầu không được để trống")
  private Date ngayBatDau;

  @NotNull(message = "Ngày kết thúc không được để trống")
  private Date ngayKetThuc;
}