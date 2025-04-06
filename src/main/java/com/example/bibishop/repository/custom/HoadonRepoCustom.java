package com.example.bibishop.repository.custom;


import com.example.bibishop.dto.HDCTSearchRequest;
import com.example.bibishop.dto.HDCTSearchResponse;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface HoadonRepoCustom {

  List<HDCTSearchResponse> search(HDCTSearchRequest request, Pageable pageable);

  long  countSearch(HDCTSearchRequest assignTaskSearchRequest);
}
