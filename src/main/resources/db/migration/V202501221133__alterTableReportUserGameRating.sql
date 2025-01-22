ALTER TABLE report_user_game_rating
    DROP CONSTRAINT IF EXISTS fk7ptdx72158nqonilrt4rcaq17,
    ADD CONSTRAINT fk7ptdx72158nqonilrt4rcaq17
        FOREIGN KEY (user_game_rating_id) REFERENCES game_ratings(id)
            ON DELETE CASCADE;
