package com.kavya.hackmate.user.specification;

import com.kavya.hackmate.skill.Skill;
import com.kavya.hackmate.user.User;
import com.kavya.hackmate.user.dto.UserSearchRequest;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> searchUsers(UserSearchRequest request) {

        return (root, query, criteriaBuilder) -> {

            var predicate = criteriaBuilder.conjunction();

            if (request.getCity() != null && !request.getCity().isBlank()) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("city"), request.getCity())
                );
            }

            if (request.getCollege() != null && !request.getCollege().isBlank()) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("college"), request.getCollege())
                );
            }

            if (request.getGraduationYear() != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(
                                root.get("graduationYear"),
                                request.getGraduationYear()
                        )
                );
            }

            if (request.getSkill() != null && !request.getSkill().isBlank()) {

                Join<User, Skill> skillJoin = root.join("skills");

                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(
                                skillJoin.get("name"),
                                request.getSkill()
                        )
                );
            }

            return predicate;
        };
    }
}