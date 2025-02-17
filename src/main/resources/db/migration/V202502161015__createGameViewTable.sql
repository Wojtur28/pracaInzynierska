CREATE TABLE game_views (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID,
    steam_game_id UUID NOT NULL,
    view_duration INTEGER NOT NULL,
    viewed_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_game_views_user FOREIGN KEY (user_id)
        REFERENCES users (id),
    CONSTRAINT fk_game_views_game FOREIGN KEY (steam_game_id)
        REFERENCES steam_games (id)
);
