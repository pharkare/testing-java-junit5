package guru.springframework;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
class AssumptionTest {

    @Test
    @DisplayName("Disable dynamically")
    void testRunConditionally() {
        assumeTrue("Expected".equalsIgnoreCase("Actual"), "Test is Skipped");
        System.out.println("Skip this test");
        assertEquals("a", "b");
    }

    @Test
    @DisplayName("Execute on mac only")
    @EnabledOnOs(OS.MAC)
    void testRunOnMac() {
        System.out.println("Is Windows");
        fail();
    }

    @Test
    @DisplayName("Execute for a specific only")
    @EnabledIfEnvironmentVariable(named = "SYS", matches = "abcd")
    void testRunForUser() {
        System.out.println("Is different user");
        assertTrue(true);
    }

}
