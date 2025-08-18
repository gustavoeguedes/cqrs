ALTER TABLE beautique_schema.customer
    ADD COLUMN IF NOT EXISTS email VARCHAR(255) NOT NULL;