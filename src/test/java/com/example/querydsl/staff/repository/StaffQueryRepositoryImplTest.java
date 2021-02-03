package com.example.querydsl.staff.repository;

import com.example.querydsl.basic.BasicTest;
import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.vo.StaffEtcVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StaffQueryRepositoryImplTest extends BasicTest {
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

    @Test
    @DisplayName("Querydsl-JPA covering index 적용")
    void findByCoveringIndex() {
        //given
        final String name = "강남집";

        //when
        List<Staff> staffs = staffRepository.findByCoveringIndex(name);

        //then
        assertThat(staffs).isNotNull();
    }
}