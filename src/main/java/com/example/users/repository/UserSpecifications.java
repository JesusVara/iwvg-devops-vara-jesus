package com.example.users.repository;

import com.example.users.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public final class UserSpecifications {

    private UserSpecifications() {
    }

    public static Specification<User> search(String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }

        String pattern = "%" + value.trim().toLowerCase() + "%";
        return (root, query, cb) -> cb.or(
                cb.like(cb.lower(root.get("firstName")), pattern),
                cb.like(cb.lower(root.get("familyName")), pattern),
                cb.like(cb.lower(root.get("email")), pattern),
                cb.like(cb.lower(root.get("identity")), pattern),
                cb.like(cb.lower(root.get("address")), pattern),
                cb.like(cb.lower(root.get("city")), pattern),
                cb.like(cb.lower(root.get("province")), pattern),
                cb.like(cb.lower(root.get("postalCode")), pattern)
        );
    }

    public static Specification<User> billable(Boolean billable) {
        if (billable == null) {
            return null;
        }
        return billable ? isBillable() : Specification.not(isBillable());
    }

    private static Specification<User> isBillable() {
        return (root, query, cb) -> cb.and(
                cb.isNotNull(root.get("firstName")), cb.notEqual(cb.trim(root.get("firstName")), ""),
                cb.isNotNull(root.get("familyName")), cb.notEqual(cb.trim(root.get("familyName")), ""),
                cb.isNotNull(root.get("email")), cb.notEqual(cb.trim(root.get("email")), ""),
                cb.isNotNull(root.get("identity")), cb.notEqual(cb.trim(root.get("identity")), ""),
                cb.isNotNull(root.get("address")), cb.notEqual(cb.trim(root.get("address")), ""),
                cb.isNotNull(root.get("city")), cb.notEqual(cb.trim(root.get("city")), ""),
                cb.isNotNull(root.get("province")), cb.notEqual(cb.trim(root.get("province")), ""),
                cb.isNotNull(root.get("postalCode")), cb.notEqual(cb.trim(root.get("postalCode")), "")
        );
    }
}
