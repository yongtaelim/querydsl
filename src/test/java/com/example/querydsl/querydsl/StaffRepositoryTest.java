package com.example.querydsl.querydsl;

import com.example.querydsl.basic.BasicTest;
import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.repository.StaffRepository;
import com.example.querydsl.staff.vo.StaffVo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StaffRepositoryTest extends BasicTest {

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

    @Test
    void 저장_테스트() {
        //given
        String name = "sssssss";
        Staff staff = Staff.builder()
                .name(name)
                .build();


        //when
        staffRepository.save(staff);


        //then
        assertThat(staff.getName()).isEqualTo(name);
    }

    @Test
    void querydsl_페이징처리() {
        //given
        final String name = "임임용태";

        Sort.Order order = Sort.Order.desc("lastName");
        Sort sort = Sort.by(order);

        Pageable pageable = PageRequest.of(0, 10, sort);

        //when
        PageImpl<StaffVo> staffsByNamePaging = staffRepository.findStaffsByNamePaging(name, pageable);

        //then
        assertThat(staffsByNamePaging.getNumber()).isEqualTo(0);
        assertThat(staffsByNamePaging.getTotalPages()).isEqualTo(1);
        assertThat(staffsByNamePaging.getContent().size()).isEqualTo(1);
    }

    @Test
    void dynamic_query_test() {
        //given
        final String name = "용태";

        //when
        Staff staff = staffRepository.dynamicQuery(name);

        //then
        assertThat(staff).isNotNull();
    }

    @Test
    void querydsl_exist_test() {
        //given
        final String name = "";

        //when
        Boolean exist = staffRepository.findExist(name);

        //then
        assertThat(exist).isFalse();
    }

    @Test
    void querydsl_exist_우회_test() {
        //given
        final String name = "";

        //when
        Boolean exist = staffRepository.findLimitOneInsteadOfExist(name);

        //then
        assertThat(exist).isFalse();
    }

    @Test
    void querydsl_간단한_exists() {
        //given
        final String name = "";

        //when
        boolean exists = staffRepository.existsByName(name);

        //then
        assertThat(exists).isFalse();
    }
}
