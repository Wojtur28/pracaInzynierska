ALTER TABLE report_steam_games
    DROP CONSTRAINT IF EXISTS fk1l4wni8q452m8xihr84kuby7p,
    ADD CONSTRAINT fk1l4wni8q452m8xihr84kuby7p
        FOREIGN KEY (raport_id) REFERENCES reports(id)
            ON DELETE CASCADE;
