ALTER TABLE user_roles
    DROP CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f,
    ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f
        FOREIGN KEY (user_id) REFERENCES users(id)
            ON DELETE CASCADE;
