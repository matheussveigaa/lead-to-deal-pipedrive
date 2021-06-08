package br.com.teste.demo.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.UUID;

import static br.com.teste.demo.utils.ViolationMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class UserValidationTest {
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
        var user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail("asdasd@sads.com.br");
        user.setName(null);

        //act
        var violations = validator.validate(user);

        //assert
        assertThat(violations.isEmpty(), is(false));
        assertThat(violations, violates(on("name")));
        assertThat(violations, violates(withError("{javax.validation.constraints.NotBlank.message}")));
    }

    @Test
    @DisplayName("Should not validate if name is valid with success")
    void shouldNot_validateNameNotBlank_withSuccess() {
        //arrange
        var user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail("asdasd@sads.com.br");
        user.setName("Vendedor 1");

        //act
        var violations = validator.validate(user);

        //assert
        assertThat(violations.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Should validate if email is blank with success")
    void should_validateEmailNotBlank_withSuccess() {
        //arrange
        var user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail("");
        user.setName("Vendedor 1");

        //act
        var violations = validator.validate(user);

        //assert
        assertThat(violations.isEmpty(), is(false));
        assertThat(violations, violates(on("email")));
        assertThat(violations, violates(withError("{javax.validation.constraints.NotBlank.message}")));
    }

    @Test
    @DisplayName("Should validate if email is blank with success")
    void shouldNot_validateEmailNotBlank_withSuccess() {
        //arrange
        var user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail("asdsa@asdsa.com.br");
        user.setName("Vendedor 1");

        //act
        var violations = validator.validate(user);

        //assert
        assertThat(violations.isEmpty(), is(true));
    }

    @Test
    @DisplayName("Should validate if email is invalid with success")
    void should_validateEmailInvalid_withSuccess() {
        //arrange
        var user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail("asdas.comasd");
        user.setName("Vendedor 1");

        //act
        var violations = validator.validate(user);

        //assert
        assertThat(violations.isEmpty(), is(false));
        assertThat(violations, violates(on("email")));
        assertThat(violations, violates(withError("{javax.validation.constraints.Email.message}")));
    }

    @Test
    @DisplayName("Should not validate if email is valid with success")
    void shouldNot_validateEmailInvalid_withSuccess() {
        //arrange
        var user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail("asdas@as2d.com");
        user.setName("Vendedor 1");

        //act
        var violations = validator.validate(user);

        //assert
        assertThat(violations.isEmpty(), is(true));
    }
}