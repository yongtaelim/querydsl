package com.example.querydsl.querydsl;

import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.repository.StaffRepository;
import com.example.querydsl.staff.vo.StaffVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StaffRepositoryTest {

    @Autowired
    private StaffRepository staffRepository;

    @Test
    void searchAll() {
        //given


        //when
        List<Staff> staff = staffRepository.searchAll();

        //then
        assertThat(staff).isNotNull();
    }

    @Test
    void search() {
        //given
        final Long id = 1L;

        //when
        StaffVo search = staffRepository.search(id);

        //then
        assertThat(search.getId()).isEqualTo(id);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void find_후_저장테스트() {
        //given
        final Long id = 1L;
        final String name = "임임용태";
        Staff staff = staffRepository.findStaffById(id);

        //when
        staff.changeName(name);

        //then
    }
}
