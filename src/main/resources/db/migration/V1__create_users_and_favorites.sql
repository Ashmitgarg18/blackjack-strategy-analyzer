CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

CREATE TABLE favorite_strategy (
  id SERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  strategy_name VARCHAR(255) NOT NULL,
  config_json TEXT
);

ALTER TABLE simulation_result_entity ADD COLUMN user_id BIGINT;
