ALTER TABLE votes
    DROP CONSTRAINT fktggxm09ful8oht2mgnec3nsym,
    ADD CONSTRAINT fktggxm09ful8oht2mgnec3nsym
        FOREIGN KEY (user_id) REFERENCES users(id)
            ON DELETE CASCADE;
