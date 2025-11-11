package com.sagarmalasi.App;

import com.sagarmalasi.App.dto.InstituteDTO;
import com.sagarmalasi.App.entity.Institute;
import com.sagarmalasi.App.service.InstituteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OepApplicationTests {
    @Autowired
    private InstituteService instituteService;

	@Test
	void contextLoads() {
	}

    @Test
    public void registerInstitute(){
        InstituteDTO institute = InstituteDTO.builder()
                .instituteName("Nepal Medical Council")
                .instituteCode("NMC-101")
                .email("nmc@gmail.com")
                .phoneNumber("9900112233")
                .address("Maharajgunj")
                .build();
        InstituteDTO instituteDTO = instituteService.registerInstitute(institute);
        System.out.println(instituteDTO);
    }




}
