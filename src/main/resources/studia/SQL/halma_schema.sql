
CREATE OR REPLACE TABLE games (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    variant INT,
    bots_number INT,
    players_number INT,
    starting_player INT,
    moreData INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE OR REPLACE TABLE moves (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    game_id INT,
    FOREIGN KEY (game_id) REFERENCES games(id),
    player_id INT,
    move_from INT,
    move_to INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);