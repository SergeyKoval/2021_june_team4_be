package com.exadel.discount.controller.alterTestContainer;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = ApplicationContext.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(value = {
        "classpath:application.yaml",
        "classpath:application-test.yaml"
})
public abstract class DataBaseIT {

    @ClassRule
    public static TestPostgresContainer postgres = TestPostgresContainer
            .getInstance();
}
