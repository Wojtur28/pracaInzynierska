package org.example.pracainzynierska.core.specification;

import jakarta.persistence.criteria.Join;
import org.example.pracainzynierska.core.entities.steam.category.CategoryEntity;
import org.example.pracainzynierska.core.entities.steam.game.SteamGameEntity;
import org.example.pracainzynierska.core.entities.steam.genre.GenreEntity;
import org.example.pracainzynierska.core.entities.steam.platform.PlatformEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SteamGameSpecifications {

    public static Specification<SteamGameEntity> hasPlatform(String platform) {
        return (root, query, criteriaBuilder) -> {
            if (platform == null || platform.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<SteamGameEntity, PlatformEntity> platforms = root.join("steamGameDetailEntity").join("platforms");
            return criteriaBuilder.and(
                    criteriaBuilder.equal(platforms.get("name"), platform),
                    criteriaBuilder.isTrue(platforms.get("isSupport"))
            );
        };
    }

    public static Specification<SteamGameEntity> hasCategories(List<String> categories) {
        return (root, query, criteriaBuilder) -> {
            if (categories == null || categories.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<SteamGameEntity, CategoryEntity> categoryJoin = root.join("steamGameDetailEntity").join("categories");
            return categoryJoin.get("name").in(categories);
        };
    }

    public static Specification<SteamGameEntity> hasGenres(List<String> genres) {
        return (root, query, criteriaBuilder) -> {
            if (genres == null || genres.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<SteamGameEntity, GenreEntity> genreJoin = root.join("steamGameDetailEntity").join("genres");
            return genreJoin.get("name").in(genres);
        };
    }

    public static Specification<SteamGameEntity> hasName(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + search.toLowerCase() + "%");
        };
    }
}
