ALTER TABLE game_ratings_answers
    DROP CONSTRAINT fkajq8ifqghg4x18kux8aa6044o,
    ADD CONSTRAINT fkajq8ifqghg4x18kux8aa6044o
        FOREIGN KEY (user_id) REFERENCES users(id)
            ON DELETE CASCADE;
