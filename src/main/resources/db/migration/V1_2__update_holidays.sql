ALTER TABLE holidays
ADD COLUMN end_date date;

ALTER TABLE holidays 
RENAME COLUMN date TO start_date;