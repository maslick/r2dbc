CREATE TABLE IF NOT EXISTS feeds (
      id           SERIAL PRIMARY KEY,
      name         VARCHAR(255),
      timestamp    BIGINT
);