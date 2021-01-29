package com.example.querydsl.basic;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("h2")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BasicTest {
}
