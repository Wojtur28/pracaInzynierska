ALTER TABLE users
    DROP CONSTRAINT fk3aqdj6u2t0by2dj24m141utm,
    ADD CONSTRAINT fk3aqdj6u2t0by2dj24m141utm
        FOREIGN KEY (modified_by) REFERENCES users (id)
            ON DELETE CASCADE;

ALTER TABLE users
    DROP CONSTRAINT fkibk1e3kaxy5sfyeekp8hbhnim,
    ADD CONSTRAINT fkibk1e3kaxy5sfyeekp8hbhnim
        FOREIGN KEY (created_by) REFERENCES users (id)
            ON DELETE CASCADE;
