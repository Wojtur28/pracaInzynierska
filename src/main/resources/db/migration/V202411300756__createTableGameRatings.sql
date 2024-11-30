CREATE TABLE IF NOT EXISTS game_ratings (
                              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              steam_games_id UUID NOT NULL,
                              user_id UUID NOT NULL,
                              rating INTEGER,
                              content TEXT,
                              votes_up INTEGER NOT NULL DEFAULT 0,
                              votes_down INTEGER NOT NULL DEFAULT 0,
                              CONSTRAINT fk_steam_games FOREIGN KEY (steam_games_id) REFERENCES steam_games (id) ON DELETE CASCADE,
                              CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
