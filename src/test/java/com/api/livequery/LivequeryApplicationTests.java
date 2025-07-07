package com.api.livequery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LivequeryApplicationTests {

    @Test
    @DisplayName("Test main method runs successfully.")
    void mainMethodRuns() {
        try (MockedStatic<SpringApplication> mocked = Mockito.mockStatic(org.springframework.boot.SpringApplication.class)) {
            LivequeryApplication.main(new String[] {});
            mocked.verify(() -> org.springframework.boot.SpringApplication.run(LivequeryApplication.class, new String[] {}));
        }
    }
}
