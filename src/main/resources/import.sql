-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO account (id, accountId, balance, overdraft, depositCap, account_type) VALUES (1, 1, 100.00, 0, NULL, 'CURRENT');
INSERT INTO account (id, accountId, balance, overdraft, depositCap, account_type) VALUES (2, 2, 200.00, NULL, 1000.00, 'SAVING');
INSERT INTO account (id, accountId, balance, overdraft, depositCap, account_type) VALUES (3, 3, 400.00, 1000, NULL, 'CURRENT');