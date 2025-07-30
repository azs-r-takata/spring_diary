CREATE TABLE IF NOT EXISTS diaries (
    diary_id SERIAL NOT NULL PRIMARY KEY,
    diary_title VARCHAR(50) NOT NULL,
    diary_date TIMESTAMP NOT NULL,
    diary_content VARCHAR(100) NOT NULL
);