package com.example.querydsl.querydsl;

import com.example.querydsl.order.OrderSort;
import com.example.querydsl.order.PageOrderVo;
import com.example.querydsl.order.SortVo;
import com.example.querydsl.staff.entity.Staff;
import com.example.querydsl.staff.repository.StaffRepository;
import com.example.querydsl.staff.vo.StaffEtcVo;
import com.example.querydsl.staff.vo.StaffVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ActiveProfiles("h2")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class StaffRepositoryTest {

    private final StaffRepository staffRepository;

    public StaffRepositoryTest(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @BeforeEach
    void beforeEach() {
        List<Staff> staffs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            staffs.add(
                    Staff.builder()
                            .name("yong" + i)
                            .age(i)
                            .lastName("lim" + i)
                            .build()
            );
        }

//        Store store = Store.builder()
//                .address("강남역 근처")
//                .name("대박사업장")
//                .staffs(staffs)
//                .build();

        staffRepository.saveAll(staffs);
    }

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

    @Test
    @DisplayName("querydsl paging, order 테스트")
    void findAllDynamicOrder_test() {
        //given
        PageOrderVo pageOrderVo = generate_findAllDynamicOrder_testData();

        //when
        PageImpl<Staff> pageImplStaff = staffRepository.findAllDynamicOrder(pageOrderVo);

        System.out.println(pageImplStaff.getContent().toString());

        //then
        assertAll(
                () -> assertThat(pageImplStaff.getTotalPages()).isEqualTo(1),
                () -> assertThat(pageImplStaff.getNumber()).isEqualTo(0),
                () -> assertThat(pageImplStaff.getContent()).hasSize(10)
        );
    }

    private PageOrderVo generate_findAllDynamicOrder_testData() {
        SortVo sortVo1 = new SortVo();
        sortVo1.setKey("name");
        sortVo1.setOrderSort(OrderSort.DESC);

        SortVo sortVo2 = new SortVo();
        sortVo2.setKey("lastName");
        sortVo2.setOrderSort(OrderSort.DESC);

        SortVo sortVo3 = new SortVo();
        sortVo3.setKey("age");
        sortVo3.setOrderSort(OrderSort.ASC);

        PageOrderVo pageOrderVo = new PageOrderVo();
        pageOrderVo.setPage(0);
        pageOrderVo.setPageSize(10);
        pageOrderVo.setSorts(Arrays.asList(sortVo1, sortVo2, sortVo3));
        return pageOrderVo;
    }
}
