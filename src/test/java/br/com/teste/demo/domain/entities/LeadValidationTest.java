package br.com.teste.demo.domain.entities;

import br.com.teste.demo.domain.enums.LeadSituation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.UUID;

import static br.com.teste.demo.utils.ViolationMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class LeadValidationTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Should validate if name is blank with success")
    void should_validateNameNotBlank_withSuccess() {
        //arrange
        var lead = new Lead(
                "",
                "email@teste.com",
                "Empresa teste",
                null,
                Arrays.asList(new LeadPhone("22", "987342234")),
                LeadSituation.LOST,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(false));
        assertThat(violations, violates(on("name")));
        assertThat(violations, violates(withError("{javax.validation.constraints.NotBlank.message}")));
    }

    @Test
    @DisplayName("Should not validate if name is valid with success")
    void shouldNot_validateNameNotBlank_withSuccess() {
        //arrange
        var lead = new Lead(
                "João da silva",
                "email@teste.com",
                "Empresa teste",
                null,
                Arrays.asList(new LeadPhone("22", "987342234")),
                LeadSituation.LOST,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Should validate if email is blank with success")
    void should_validateEmailNotBlank_withSuccess() {
        //arrange
        var lead = new Lead(
                "João da silva",
                "",
                "Empresa teste",
                null,
                Arrays.asList(new LeadPhone("22", "987342234")),
                LeadSituation.LOST,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(false));
        assertThat(violations, violates(on("email")));
        assertThat(violations, violates(withError("{javax.validation.constraints.NotBlank.message}")));
    }

    @Test
    @DisplayName("Should validate if email is invalid with success")
    void should_validateEmailInvalid_withSuccess() {
        //arrange
        var lead = new Lead(
                "João da silva",
                "asdasdasdasdas",
                "Empresa teste",
                null,
                Arrays.asList(new LeadPhone("22", "987342234")),
                LeadSituation.LOST,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(false));
        assertThat(violations, violates(on("email")));
        assertThat(violations, violates(withError("{javax.validation.constraints.Email.message}")));
    }

    @Test
    @DisplayName("Should not validate if email is valid with success")
    void shouldNot_validateEmailInvalid_withSuccess() {
        //arrange
        var lead = new Lead(
                "João da silva",
                "abcas@ash.com.br",
                "Empresa teste",
                null,
                Arrays.asList(new LeadPhone("22", "987342234")),
                LeadSituation.LOST,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Should validate if company is blank with success")
    void should_validateCompanyNotBlank_withSuccess() {
        //arrange
        var lead = new Lead(
                "João da silva",
                "asdaas@asd.com.br",
                "",
                null,
                Arrays.asList(new LeadPhone("22", "987342234")),
                LeadSituation.LOST,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(false));
        assertThat(violations, violates(on("company")));
        assertThat(violations, violates(withError("{javax.validation.constraints.NotBlank.message}")));
    }

    @Test
    @DisplayName("Should not validate if company is valid with success")
    void shouldNot_validateCompanyNotBlank_withSuccess() {
        //arrange
        var lead = new Lead(
                "João da silva",
                "asdaas@asd.com.br",
                "Empresa teste",
                null,
                Arrays.asList(new LeadPhone("22", "987342234")),
                LeadSituation.LOST,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Should validate if phones is empty with success")
    void should_validatePhonesNotEmpty_withSuccess() {
        //arrange
        var lead = new Lead(
                "João da silva",
                "asdaas@asd.com.br",
                "Empresa teste",
                null,
                Arrays.asList(),
                LeadSituation.LOST,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(false));
        assertThat(violations, violates(on("phones")));
        assertThat(violations, violates(withError("{javax.validation.constraints.NotEmpty.message}")));
    }

    @Test
    @DisplayName("Should not validate if phones has item with success")
    void shouldNot_validatePhonesNotEmpty_withSuccess() {
        //arrange
        var lead = new Lead(
                "João da silva",
                "asdaas@asd.com.br",
                "Empresa teste",
                null,
                Arrays.asList(new LeadPhone("22", "987342234")),
                LeadSituation.LOST,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Should validate if status is null with success")
    void should_validateStatusNotNull_withSuccess() {
        //arrange
        var lead = new Lead(
                "João da silva",
                "asdaas@asd.com.br",
                "Empresa teste",
                null,
                Arrays.asList(new LeadPhone("22", "987342234")),
                null,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(false));
        assertThat(violations, violates(on("status")));
        assertThat(violations, violates(withError("{javax.validation.constraints.NotNull.message}")));
    }

    @Test
    @DisplayName("Should not validate if status is valid with success")
    void shouldNot_validateStatusNotNull_withSuccess() {
        //arrange
        var lead = new Lead(
                "João da silva",
                "asdaas@asd.com.br",
                "Empresa teste",
                null,
                Arrays.asList(new LeadPhone("22", "987342234")),
                LeadSituation.WON,
                null,
                UUID.randomUUID().toString(),
                null
        );
        lead.setId(UUID.randomUUID().toString());

        //act
        var violations = validator.validate(lead);

        //assert
        assertThat(violations.isEmpty(), is(true));
    }
}