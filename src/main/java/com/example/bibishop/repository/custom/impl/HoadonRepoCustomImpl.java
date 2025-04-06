package com.example.bibishop.repository.custom.impl;


import com.example.bibishop.dto.HDCTSearchRequest;
import com.example.bibishop.dto.HDCTSearchResponse;
import com.example.bibishop.repository.custom.HoadonRepoCustom;
import com.example.bibishop.utils.DataUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

public class HoadonRepoCustomImpl implements HoadonRepoCustom {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<HDCTSearchResponse> search(HDCTSearchRequest request, Pageable pageable) {
        Query query = buildQuerySearch(request, pageable);
        List<Object[]> objs = query.getResultList();
        List<HDCTSearchResponse> result = new LinkedList<>();
        for (Object[] obj : objs) {
            int i = 0;
            HDCTSearchResponse assignTaskSearchResponse = new HDCTSearchResponse();
            assignTaskSearchResponse.setProductName(DataUtils.safeToString(obj[i++]));
            assignTaskSearchResponse.setLink(DataUtils.safeToString(obj[i++]));
            assignTaskSearchResponse.setNameSize(DataUtils.safeToString(obj[i++]));
            assignTaskSearchResponse.setColor(DataUtils.safeToString(obj[i++]));
            assignTaskSearchResponse.setCategoryName(DataUtils.safeToString(obj[i++]));
            assignTaskSearchResponse.setNumOfSale(DataUtils.safeToLong(obj[i++]));
            assignTaskSearchResponse.setRevenue(DataUtils.parseToDouble(obj[i++]));
            result.add(assignTaskSearchResponse);
        }
        return result;
    }

    @Override
    public long countSearch(HDCTSearchRequest request) {
        Query query = buildQueryCount(request);
        return DataUtils.safeToLong(query.getSingleResult());
    }
    private Query buildQuerySearch(HDCTSearchRequest request, Pageable pageable){
        String query = """
                SELECT  
                    sp.ten_san_pham ,
                    ha.duong_dan ,
                    kc.ten_kich_co ,
                    ms.ten AS mauSac,
                    dm.ten_danh_muc ,
                    SUM(hdct.so_luong) AS soLuongDaBan,
                    SUM(hdct.so_luong * hdct.gia) AS doanhThu
                FROM  
                    hoa_don_chi_tiet  hdct
                JOIN  
                    san_pham_chi_tiet  spct ON hdct.id_sanpham_chitiet = spct.id
                JOIN  
                    san_pham sp ON spct.sanpham_id = sp.id
                JOIN  
                    hinh_anh ha ON spct.hinh_anh_id = ha.id
                JOIN  
                    kich_co kc ON spct.kich_co_id  = kc.id
                JOIN  
                    mau_sac ms ON spct.mau_sac_id = ms.id
                JOIN  
                    danh_muc dm ON spct.danh_muc_id = dm.id
                JOIN hoa_don hd ON hdct.id_hoadon = hd.id
                WHERE  
                    hd.trang_thai = 'DA_THANH_TOAN'
                """;

        StringBuilder sql = new StringBuilder(query);
        Map<String, Object> params = new HashMap<>();
        if (!StringUtils.isEmpty(request.getProductName())) {
            sql.append("\nAND ( UPPER(sp.ten_san_pham) LIKE :name ) ");
            params.put("name", "%" + DataUtils.escapeSql(request.getProductName()).toUpperCase() + "%");
        }
        if (!StringUtils.isEmpty(request.getColor())) {
            sql.append("\nAND ( UPPER(ms.ten) LIKE :color ) ");
            params.put("color", "%" + DataUtils.escapeSql(request.getColor()).toUpperCase() + "%");
        }
        if (!StringUtils.isEmpty(request.getSize())) {
            sql.append("\nAND ( UPPER(kc.ten_kich_co) LIKE :size ) ");
            params.put("size", "%" + DataUtils.escapeSql(request.getSize()).toUpperCase() + "%");
        }
        sql.append("     GROUP BY " +
                "                    sp.id, sp.ten_san_pham , ha.duong_dan , kc.ten_kich_co , ms.ten, dm.ten_danh_muc" +
                "                order by soLuongDaBan desc  ");
        Query queryBuilder = em.createNativeQuery(sql.toString());
        params.forEach(queryBuilder::setParameter);
        if (pageable != null) {
            queryBuilder.setFirstResult((int) pageable.getOffset());
            queryBuilder.setMaxResults(pageable.getPageSize());
        }
        return queryBuilder;
    }

    private Query buildQueryCount(HDCTSearchRequest request){
        String query = """
                SELECT COUNT(*) AS so_luong_ban_ghi
                FROM (
                    SELECT   
                        sp.id
                    FROM   
                        hoa_don_chi_tiet hdct
                    JOIN   
                        san_pham_chi_tiet spct ON hdct.id_sanpham_chitiet = spct.id
                    JOIN   
                        san_pham sp ON spct.sanpham_id = sp.id
                    JOIN   
                        hinh_anh ha ON spct.hinh_anh_id = ha.id
                    JOIN   
                        kich_co kc ON spct.kich_co_id = kc.id
                    JOIN   
                        mau_sac ms ON spct.mau_sac_id = ms.id
                    JOIN   
                        danh_muc dm ON spct.danh_muc_id = dm.id
                    JOIN   
                        hoa_don hd ON hdct.id_hoadon = hd.id
                    WHERE   
                        hd.trang_thai = 'DA_THANH_TOAN'
                """;
        StringBuilder sql = new StringBuilder(query);
        Map<String, Object> params = new HashMap<>();
        if (!StringUtils.isEmpty(request.getProductName())) {
            sql.append("\nAND ( UPPER(sp.ten_san_pham) LIKE :name ) ");
            params.put("name", "%" + DataUtils.escapeSql(request.getProductName()).toUpperCase() + "%");
        }
        if (!StringUtils.isEmpty(request.getColor())) {
            sql.append("\nAND ( UPPER(ms.ten) LIKE :color ) ");
            params.put("color", "%" + DataUtils.escapeSql(request.getColor()).toUpperCase() + "%");
        }
        if (!StringUtils.isEmpty(request.getSize())) {
            sql.append("\nAND ( UPPER(kc.ten_kich_co) LIKE :size ) ");
            params.put("size", "%" + DataUtils.escapeSql(request.getSize()).toUpperCase() + "%");
        }
        sql.append("     GROUP BY " +
                "                        sp.id, sp.ten_san_pham, ha.duong_dan, kc.ten_kich_co, ms.ten, dm.ten_danh_muc\n" +
                "                ) AS subquery; ");
        Query queryBuilder = em.createNativeQuery(sql.toString());
        params.forEach(queryBuilder::setParameter);
        return queryBuilder;
    }
}
