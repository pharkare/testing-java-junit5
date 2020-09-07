package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerTest {

    @DisplayName("Dependent Assertions")
    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1L, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");

        assertAll("Properties Test",
                () -> assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName()),
                        () -> assertEquals("Buck", owner.getLastName())),
                () -> assertAll("Owner Properties",
                        () -> assertEquals("Joe", owner.getFirstName()),
                        () -> assertEquals("Buck", owner.getLastName()))
        );
    }

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @EnumSource(OwnerType.class)
    void testEnumSource(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("CSV from File Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + " : " + val2);
    }

    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @MethodSource("getargs")
    void fromMethodTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + " : " + val2);
    }

    static Stream<Arguments> getargs() {
        return Stream.of(
                Arguments.of("FL", 2, 3),
                Arguments.of("MI", 4, 5),
                Arguments.of("OH", 6, 8)
        );
    }

    @DisplayName("Custom Provider Test")
    @ParameterizedTest(name = "{displayName} - {index} {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest (String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + " : " + val2);
    }

}
