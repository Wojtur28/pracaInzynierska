ALTER TABLE game_ratings_answers
    DROP CONSTRAINT fkhp0pbfg13a4fqutnysff5fdx9,
    ADD CONSTRAINT fkhp0pbfg13a4fqutnysff5fdx9
        FOREIGN KEY (game_rating_entity_id) REFERENCES game_ratings(id)
            ON DELETE CASCADE;
