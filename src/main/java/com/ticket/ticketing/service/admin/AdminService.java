package com.ticket.ticketing.service.admin;

import com.ticket.ticketing.dto.request.ConcertUploadDto;
import com.ticket.ticketing.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminMapper adminMapper;


    public void concertUpload(ConcertUploadDto concertUploadDto, String imageUrl) {
        try {
            adminMapper.concertUpload(concertUploadDto, imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
