package com.example.bibishop.controller.HoaDon;


import com.example.bibishop.entity.GioHang;
import com.example.bibishop.entity.HoaDon;
import com.example.bibishop.entity.HoaDonChiTiet;
import com.example.bibishop.repository.HoaDonChiTietRepository;
import com.example.bibishop.service.server.HoaDon.GioHangService;
import com.example.bibishop.service.server.HoaDon.HoaDonService;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/admin/")
public class HoaDonController {

  @Autowired
  private HoaDonService hoaDonService;

  @Autowired
  private GioHangService gioHangService;
  @Autowired
  private HoaDonChiTietRepository hoaDonChiTietRepository;

  // Danh sách hóa đơn
  @GetMapping("/bill-list")
  public String showHoaDonList(Model model,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String sort,
      @RequestParam(required = false) String maDinhDanh) {

    log.info("Filter: maDinhDanh={}", maDinhDanh);

    Sort sortObj = Sort.unsorted();
    if (sort != null && !sort.isEmpty()) {
      String[] sortParts = sort.split(",");
      String field = sortParts[0];
      Sort.Direction direction = Sort.Direction.fromString(sortParts[1]);
      sortObj = Sort.by(direction, field.equals("code") ? "maHoaDon" : field);
    }

    Pageable pageable = PageRequest.of(page, size, sortObj);
    Page<HoaDon> items;

    boolean isFilterEmpty = (maDinhDanh == null || maDinhDanh.isEmpty());
    if (isFilterEmpty) {
      items = hoaDonService.getAllHoaDon(pageable);
      log.info("Get all hoa don: {}", items.getContent());
    } else {
      items = hoaDonService.filterHoaDon(maDinhDanh, pageable);
      log.info("Filtered hoa don: {}", items.getContent());
    }

    log.info("Items found: {}, Total elements: {}, Total pages: {}",
        items.getContent(), items.getTotalElements(), items.getTotalPages());

    model.addAttribute("items", items);
    model.addAttribute("maDinhDanh", maDinhDanh);
    model.addAttribute("sortField", sort != null ? sort.split(",")[0] : null);
    model.addAttribute("sortDirection", sort != null ? sort.split(",")[1] : null);

    return "admin/hoadon/bill-list";
  }

  @GetMapping("/getbill-detail/{maHoaDon}")
  public String showHoaDonDetail(@PathVariable String maHoaDon, Model model) {
    HoaDon billdetail = hoaDonService.getHoaDonByMaHoaDon(maHoaDon);
    if (billdetail == null) {
      return "redirect:/admin/hoadon/bill-detail"; // Nếu không tìm thấy, quay lại danh sách
    }

    model.addAttribute("billdetail", billdetail);
    model.addAttribute("billDetailProduct", billdetail.getChiTietHoaDon());
    model.addAttribute("total", billdetail.getTongTien().subtract(billdetail.getGiamGia()));
    return "admin/hoadon/bill-detail";
  }

  @GetMapping("/update-bill-status/{maHoaDon}")
  public String updateBillStatus(@PathVariable String maHoaDon,
      @RequestParam String trangThaiDonHang) {
    hoaDonService.updateTrangThai(maHoaDon, trangThaiDonHang);
    return "redirect:/admin/bill-list";
  }

  @PostMapping("/update-bill-status/{maHoaDon}")
  public String updateBillStatus2(@PathVariable String maHoaDon, @RequestParam("trangThaiDonHang") String trangThai) {
    HoaDon hoaDon = hoaDonService.getHoaDonByMaHoaDon(maHoaDon);
    if (hoaDon != null && !"HOAN_THANH".equals(hoaDon.getTrangThai())) {
      hoaDonService.updateTrangThai(maHoaDon, trangThai);
    }
    return "redirect:/admin/getbill-detail/" + maHoaDon;
  }

  @PostMapping("HoaDon/create")
  public String createHoaDon(
      @RequestParam("idKhachHang") Integer idKhachHang,
      @RequestParam("tenNguoiNhan") String tenNguoiNhan,
      @RequestParam("sdt") String sdt,
      @RequestParam("diaChi") String diaChi,
      RedirectAttributes redirectAttributes) {
    // Lấy giỏ hàng từ idKhachHang (giả định có GioHangService)
    GioHang gioHang = gioHangService.getOrCreateGioHang(idKhachHang);
    HoaDon hoaDon = hoaDonService.createHoaDon(gioHang, tenNguoiNhan, sdt, diaChi);
    redirectAttributes.addFlashAttribute("message",
        "Hóa đơn đã được tạo thành công với mã: " + hoaDon.getMaHoaDon());
    return "redirect:/hoadon/bill-list";
  }
  // Xóa sản phẩm khỏi hóa đơn
  @GetMapping("/delete-product/{id}")
  public String deleteProduct(@PathVariable Integer id, Model model) {
    HoaDonChiTiet chiTiet = hoaDonService.deleteProductFromBill(id);
    if (chiTiet != null) {
      return "redirect:/admin/getbill-detail/" + chiTiet.getHoaDon().getMaHoaDon();
    }
    return "redirect:/admin/bill-list";
  }

  // Chuyển hướng đến form sửa sản phẩm (chưa triển khai chi tiết)
  @GetMapping("/edit-product/{id}")
  public String editProduct(@PathVariable Integer id, Model model) {
    Optional<HoaDonChiTiet> chiTiet = hoaDonChiTietRepository.findById(id); // Giả sử bạn thêm phương thức này trong service
    if (chiTiet == null) {
      return "redirect:/admin/bill-list";
    }
    model.addAttribute("productDetail", chiTiet);
    return "admin/hoadon/edit-product"; // Giả sử bạn có file edit-product.html
  }

  // Xuất PDF (chỉ là placeholder, cần thư viện như iText để triển khai)
  @GetMapping("/export-pdf/{maHoaDon}")
  public String exportPdf(@PathVariable String maHoaDon) {
    // Logic xuất PDF ở đây (sử dụng iText hoặc thư viện tương tự)
    return "redirect:/admin/getbill-detail/" + maHoaDon;
  }


}