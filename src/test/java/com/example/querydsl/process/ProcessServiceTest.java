package com.example.querydsl.process;

import com.example.querydsl.process.service.ProcessService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProcessServiceTest {
    @Autowired
    private ProcessService processService;

    @Test
    void if문_탈출못했네_Test() {
        //given
        String storeBranch = "store";
        String staffBranch = "staff";

        //when
        String storeResult = processService.if문_탈출못했네(storeBranch);
        String staffResult = processService.if문_탈출못했네(staffBranch);

        //then
        assertThat(storeResult).isEqualTo("용태스토어");
        assertThat(staffResult).isEqualTo("임용태");
    }

    @Test
    void if문_탈출성공_Test() {
        //given
        String storeBranch = "store";
        String staffBranch = "staff";

        //when
        String storeResult = processService.if문_탈출성공(storeBranch);
        String staffResult = processService.if문_탈출성공(staffBranch);

        //then
        assertThat(storeResult).isEqualTo("용태스토어");
        assertThat(staffResult).isEqualTo("임용태");
    }
}
