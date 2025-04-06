package com.example.bibishop.repository;

import com.example.bibishop.entity.HoaDon;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {

  Page<HoaDon> findAll(Pageable pageable);

  Optional<HoaDon> findByMaHoaDon(String maHoaDon);

  @Query("SELECT h FROM HoaDon h WHERE " +
      "(:maHoaDon IS NULL OR h.maHoaDon LIKE CONCAT('%', :maHoaDon, '%'))")
  Page<HoaDon> filterHoaDon(
      @Param("maHoaDon") String maHoaDon,
      Pageable pageable);

  @Query(value = "SELECT MONTH(o.ngay_tao) as month, YEAR(o.ngay_tao) as year, " +
      "SUM(o.tong_tien) as totalRevenue FROM hoa_don o GROUP BY MONTH(o.ngay_tao), YEAR(o.ngay_tao) ORDER BY MONTH(o.ngay_tao)", nativeQuery = true)
  List<Object[]> calculateMonthlyRevenue();

  int countByNgayTaoBetween(LocalDateTime fromDate, LocalDateTime toDate);

  @Query(value = """
      SELECT count(*)
      FROM HoaDon h
      where h.trangThai =:status
      """)
  int countDeliveryOrder(String status);

  @Query(value = """
      SELECT COALESCE(SUM(tong_tien), 0) AS doanh_thu_thang_nay
      FROM hoa_don
      WHERE MONTH(ngay_tao) = MONTH(CURRENT_DATE())
      AND YEAR(ngay_tao) = YEAR(CURRENT_DATE())
      """, nativeQuery = true)
  long totalRevenueByMonth();

  @Query(value = """

                 SELECT
          CONCAT('[',
                 GROUP_CONCAT(
                     CONCAT('"', month_name, '"')
                     ORDER BY month_num
                 ),
                 ']') AS crLabels,
          CONCAT('[',
                 GROUP_CONCAT(
                     IFNULL(monthly_revenue, 0)
                     ORDER BY month_num
                 ),
                 ']') AS crRevenue
      FROM (
          SELECT
              m.month_num,
              m.month_name,
              SUM(hd.tong_tien) AS monthly_revenue
          FROM (
              SELECT 1 AS month_num, 'Tháng 1' AS month_name UNION ALL
              SELECT 2, 'Tháng 2' UNION ALL SELECT 3, 'Tháng 3' UNION ALL
              SELECT 4, 'Tháng 4' UNION ALL SELECT 5, 'Tháng 5' UNION ALL
              SELECT 6, 'Tháng 6' UNION ALL SELECT 7, 'Tháng 7' UNION ALL
              SELECT 8, 'Tháng 8' UNION ALL SELECT 9, 'Tháng 9' UNION ALL
              SELECT 10, 'Tháng 10' UNION ALL SELECT 11, 'Tháng 11' UNION ALL
              SELECT 12, 'Tháng 12'
          ) m
          LEFT JOIN hoa_don hd ON MONTH(hd.ngay_tao) = m.month_num
                            AND YEAR(hd.ngay_tao) = YEAR(CURRENT_DATE())
                            AND hd.trang_thai = 'DA_THANH_TOAN'
          GROUP BY m.month_num, m.month_name
      ) AS monthly_data;
      """, nativeQuery = true)
  Map<String, String> revenueStatisticInYear();

  @Query(value = """

                 SELECT
          dm.ten_danh_muc AS bcLabels,
          ROUND(COUNT(hdct.id) * 100.0 / SUM(COUNT(hdct.id)) OVER (), 2) AS bcPercents
      FROM
          danh_muc dm
      LEFT JOIN
          san_pham_chi_tiet spct ON dm.id = spct.danh_muc_id
      LEFT JOIN
          hoa_don_chi_tiet hdct ON spct.id = hdct.id_sanpham_chitiet
      LEFT JOIN
          hoa_don hd ON hdct.id_hoadon = hd.id
      WHERE
          hd.trang_thai = 'DA_THANH_TOAN'
      GROUP BY
          dm.id, dm.ten_danh_muc
      """, nativeQuery = true)
  Map<String, String> bestCategory();

  @Query(value = """

                 SELECT 
                    GROUP_CONCAT(
                        CONCAT('"', 
                        CASE 
                            WHEN m.thang = 1 THEN 'Tháng 1'
                            WHEN m.thang = 2 THEN 'Tháng 2'
                            WHEN m.thang = 3 THEN 'Tháng 3'
                            WHEN m.thang = 4 THEN 'Tháng 4'
                            WHEN m.thang = 5 THEN 'Tháng 5'
                            WHEN m.thang = 6 THEN 'Tháng 6'
                            WHEN m.thang = 7 THEN 'Tháng 7'
                            WHEN m.thang = 8 THEN 'Tháng 8'
                            WHEN m.thang = 9 THEN 'Tháng 9'
                            WHEN m.thang = 10 THEN 'Tháng 10'
                            WHEN m.thang = 11 THEN 'Tháng 11'
                            WHEN m.thang = 12 THEN 'Tháng 12'
                        END, '"') 
                        ORDER BY m.thang
                    ) AS thang,
                    
                    GROUP_CONCAT(
                        IFNULL(so_luong_order, 0) 
                        ORDER BY m.thang
                    ) AS so_luong_order 
                FROM (
                    SELECT 1 AS thang UNION SELECT 2 UNION SELECT 3 UNION 
                    SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION 
                    SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION 
                    SELECT 10 UNION SELECT 11 UNION SELECT 12
                ) m
                LEFT JOIN (
                    SELECT 
                        MONTH(ngay_tao) AS thang,
                        COUNT(*) AS so_luong_order
                    FROM
                        hoa_don
                    WHERE
                        YEAR(ngay_tao) = YEAR(CURRENT_DATE())
                        AND trang_thai = 'DA_THANH_TOAN'
                    GROUP BY
                        MONTH(ngay_tao)
                ) stats ON m.thang = stats.thang;
      """, nativeQuery = true)
  Map<String, String> ordersByMonth();
}