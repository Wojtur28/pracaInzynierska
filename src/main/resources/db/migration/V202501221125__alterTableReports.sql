ALTER TABLE reports
    DROP CONSTRAINT fk1rtk3jxoltr9pkb3bw0y20tvf,
    ADD CONSTRAINT fk1rtk3jxoltr9pkb3bw0y20tvf
        FOREIGN KEY (created_by) REFERENCES users(id)
            ON DELETE CASCADE;

ALTER TABLE reports
    DROP CONSTRAINT fkr7onij5hykmly8g8qh7lyrs05,
    ADD CONSTRAINT fkr7onij5hykmly8g8qh7lyrs05
        FOREIGN KEY (modified_by) REFERENCES users (id)
            ON DELETE CASCADE;
