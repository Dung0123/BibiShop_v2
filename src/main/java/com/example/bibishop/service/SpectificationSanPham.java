package com.example.bibishop.service;



import com.example.bibishop.dto.SanPhamFiterDTO;
import com.example.bibishop.entity.SanPhamEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

public class SpectificationSanPham {
        public static Specification<SanPhamEntity> buildWhere(SanPhamFiterDTO form) {
            Specification<SanPhamEntity> where = Specification.where(null); // Khởi tạo là một Specification trống

            if (form != null) {
                if (form.getTrangThai() != null) {
                    CustomSpecification trangThaiSpec = new CustomSpecification("trangThai", form.getTrangThai());
                    where = where.and(trangThaiSpec); // Thêm điều kiện tìm kiếm vào Specification
                }
            }

            return where;
        }

        @RequiredArgsConstructor
        static class CustomSpecification implements Specification<SanPhamEntity> {
            @NonNull
            private String field;
            @NonNull
            private Object value;

            @Override
            public Predicate toPredicate(
                    Root<SanPhamEntity> root,
                    CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder
            ){
                if (field.equals("trangThai") && value instanceof String) {
                    return criteriaBuilder.equal(root.get(field), value);
                }

                return null;
            }
        }
    }




