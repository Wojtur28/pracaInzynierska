ALTER TABLE votes
    DROP CONSTRAINT fkdvr1g3bogkvm0llp7w0a8fa29,
    ADD CONSTRAINT fkdvr1g3bogkvm0llp7w0a8fa29
        FOREIGN KEY (user_id) REFERENCES users(id)
            ON DELETE CASCADE;
