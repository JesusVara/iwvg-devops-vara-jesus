package com.example.users.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @Test
    void isBillableReturnsTrueWhenAllFieldsHaveRealContent() {
        User user = new User();
        user.setFirstName("Ana");
        user.setFamilyName("Lopez");
        user.setEmail("ana@example.com");
        user.setIdentity("ID-123");
        user.setAddress("Main Street 1");
        user.setCity("Madrid");
        user.setProvince("Madrid");
        user.setPostalCode("28001");

        assertThat(user.isBillable()).isTrue();
    }

    @Test
    void isBillableReturnsFalseWhenAnyFieldIsBlank() {
        User user = new User();
        user.setFirstName("Ana");
        user.setFamilyName("Lopez");
        user.setEmail("ana@example.com");
        user.setIdentity("ID-123");
        user.setAddress(" ");
        user.setCity("Madrid");
        user.setProvince("Madrid");
        user.setPostalCode("28001");

        assertThat(user.isBillable()).isFalse();
    }
}
