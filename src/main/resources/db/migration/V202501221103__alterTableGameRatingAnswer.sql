ALTER TABLE game_ratings_answers
    DROP CONSTRAINT fk3f1dwic233jlev3k4txsgvpxl,
    ADD CONSTRAINT fk3f1dwic233jlev3k4txsgvpxl
        FOREIGN KEY (user_id) REFERENCES users(id)
            ON DELETE CASCADE;
