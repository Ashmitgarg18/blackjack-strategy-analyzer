CREATE TABLE simulation_result (
    id BIGSERIAL PRIMARY KEY,
    strategy_name VARCHAR(100) NOT NULL,
    rounds INT NOT NULL,
    decks INT NOT NULL,
    penetration DOUBLE PRECISION NOT NULL,
    rules JSONB NOT NULL,
    hands BIGINT NOT NULL,
    wins BIGINT NOT NULL,
    losses BIGINT NOT NULL,
    pushes BIGINT NOT NULL,
    blackjacks BIGINT NOT NULL,
    player_busts BIGINT NOT NULL,
    dealer_busts BIGINT NOT NULL,
    net_units BIGINT NOT NULL,
    total_bet BIGINT NOT NULL,
    ev_per_hand DOUBLE PRECISION NOT NULL,
    average_bet DOUBLE PRECISION NOT NULL,
    roi_percent DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()

);