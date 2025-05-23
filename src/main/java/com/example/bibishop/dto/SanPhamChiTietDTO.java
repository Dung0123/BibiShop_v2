  package com.example.bibishop.dto;

  import com.example.bibishop.entity.KichCoEntity;
  import com.example.bibishop.entity.MauSacEntity;
  import com.example.bibishop.entity.SanPhamChiTietEntity;
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;
  import lombok.ToString;

  import java.math.BigDecimal;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @ToString
  public class SanPhamChiTietDTO extends SuperDTO {

    private KichCoDTO kichCo;

    private MauSacDTO mauSac;

    private ChatLieuDTO chatLieu;

    private HinhAnhDTO hinhAnh;

    private DanhMucDTO danhMuc;

    private int trangThai;

    private BigDecimal giaSanPham;

    private Integer soLuong;

    private String trongLuong;

    private String moTa;

    private SanPhamDTO sanPham;

    // Builder constructor nếu cần giữ, hoặc bỏ

    public static SanPhamChiTietDTO toDTO(SanPhamChiTietEntity entity) {
      SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
      dto.setId(entity.getId());
      dto.setGiaSanPham(entity.getGiaSanPham());
      dto.setSoLuong(entity.getSoLuong());
      dto.setTrongLuong(entity.getTrongLuong());
      dto.setMoTa(entity.getMoTa());
      dto.setTrangThai(entity.getTrangThai());

      // SanPhamDTO
      if (entity.getSanPham() != null) {
        SanPhamDTO spDTO = new SanPhamDTO();
        spDTO.setTenSanPham(entity.getSanPham().getTenSanPham());
        dto.setSanPham(spDTO);
      }

      // MauSacDTO
      if (entity.getMauSac() != null) {
        MauSacDTO msDTO = new MauSacDTO();
        msDTO.setTen(entity.getMauSac().getTen());
        dto.setMauSac(msDTO);
      }

      // KichCoDTO
      if (entity.getKichCo() != null) {
        KichCoDTO kcDTO = new KichCoDTO();
        kcDTO.setTenKichCo(entity.getKichCo().getTenKichCo());
        kcDTO.setDoDai(entity.getKichCo().getDoDai());
        dto.setKichCo(kcDTO);
      }

      // ChatLieuDTO
      if (entity.getChatLieu() != null) {
        ChatLieuDTO clDTO = new ChatLieuDTO();
        clDTO.setTen(entity.getChatLieu().getTen());
        dto.setChatLieu(clDTO);
      }

      // DanhMucDTO
      if (entity.getDanhMuc() != null) {
        DanhMucDTO dmDTO = new DanhMucDTO();
        dmDTO.setTenDanhMuc(entity.getDanhMuc().getTenDanhMuc());
        dto.setDanhMuc(dmDTO);
      }

      // HinhAnhDTO
      if (entity.getHinhAnh() != null) {
        HinhAnhDTO haDTO = new HinhAnhDTO();
        haDTO.setDuongDan(entity.getHinhAnh().getDuongDan());
        dto.setHinhAnh(haDTO);
      }

      return dto;
    }

  }