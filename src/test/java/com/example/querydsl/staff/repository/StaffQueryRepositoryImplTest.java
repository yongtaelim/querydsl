package com.example.querydsl.staff.repository;

import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.vo.StaffEtcVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StaffQueryRepositoryImplTest {
    @Autowired
    private StaffRepository staffRepository;

    @Test
    @DisplayName("Querydsl-JPA 사용 시 Entity 필드 전체 조회")
    void findStaffAndStoreAllData() {
        //given
        final String name = "staffN";
        Staff staff = Staff.builder()
                .name(name)
                .age(32)
                .build();
        staffRepository.save(staff);

        //when
        StaffEtcVo staffEtcVo = staffRepository.findStaffAndEtcOption(name);


        //then
        assertThat(staffEtcVo.getStaff()).isNotNull();
    }
}