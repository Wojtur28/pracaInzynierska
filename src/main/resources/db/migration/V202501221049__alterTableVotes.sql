ALTER TABLE votes
    DROP CONSTRAINT fkli4uj3ic2vypf5pialchj925e,
    ADD CONSTRAINT fkli4uj3ic2vypf5pialchj925e
        FOREIGN KEY (user_id) REFERENCES users(id)
            ON DELETE CASCADE;
