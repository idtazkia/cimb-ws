delete from payment;
delete from virtual_account;

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('c98232d6-cead-4005-aaac-4ad26008ca0c', '20180820001', '081234567890', 'CLOSED', 'TAZKIAPMB1', 'Endy Muhardin', 123000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('18bc72cc-fd13-4371-81bb-b05251a62235', '20180820002', '081234567891', 'CLOSED', 'TAZKIAPMB1', 'Gifar Haidar', 250000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('2579fcce-be7b-48cf-b065-f05d25aba01c', '20180820003', '081234567892', 'OPEN', 'TAZKIAINF1', 'Donasi Lombok', 10000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('0a03e5fd-e2d7-43d6-aaf4-db6c14e06a88', '20180820004', '081234567893', 'INSTALLMENT', 'TAZKIAASR1', 'Asrama a.n Mahasiswi 001', 10000000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('f551cacb-fdec-4de9-98a8-1007441612db', '20180820005', '081234567894', 'CLOSED', 'TAZKIASPP1', 'SPP Tetap a.n Mahasiswa 001', 500000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('82ed5496-fa32-4b48-9442-d91e76b6963d', '20180820006', '081234567895', 'CLOSED', 'TAZKIASPP2', 'SPP Variabel a.n Mahasiswa 001', 750000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('2579fcce-be7b-48cf-b065-f05d25aba01d', '20180820007', '081234567896', 'OPEN', 'TAZKIAINF2', 'Infaq Shadaqah', 20000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('0a03e5fd-e2d7-43d6-aaf4-db6c14e06a80', '20180820008', '081234567897', 'INSTALLMENT', 'TAZKIAGED1', 'Uang Gedung', 15000000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('0a03e5fd-e2d7-43d6-aaf4-db6c14e06a11', '20180903001', '123456789012345601', 'CLOSED', 'TAZKIATES1', 'Test Account 001', 120001, '2018-09-03 08:01:00', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('0a03e5fd-e2d7-43d6-aaf4-db6c14e06a12', '20180903002', '123456789012345602', 'CLOSED', 'TAZKIATES1', 'Test Account 002', 120002, '2018-09-03 08:02:00', '2019-02-02', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('0a03e5fd-e2d7-43d6-aaf4-db6c14e06a13', '20180903003', '123456789012345603', 'CLOSED', 'TAZKIATES1', 'Test Account 003', 120003, '2018-09-03 08:03:00', '2019-02-03', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('3579fcce-be7b-48cf-b065-f05d25aba01d', '20181123001', '080234567896', 'OPEN', 'TAZKIAINF2', 'Test Open Account 001', 10001, '2018-11-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('4579fcce-be7b-48cf-b065-f05d25aba01d', '20181123002', '081234567896', 'OPEN', 'TAZKIAINF2', 'Test Open Account 002', 10002, '2018-11-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('5579fcce-be7b-48cf-b065-f05d25aba01d', '20181123003', '082234567896', 'OPEN', 'TAZKIAINF2', 'Test Open Account 003', 10003, '2018-11-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('6579fcce-be7b-48cf-b065-f05d25aba01d', '20181123004', '083234567896', 'OPEN', 'TAZKIAINF2', 'Test Open Account 004', 10004, '2018-11-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('7579fcce-be7b-48cf-b065-f05d25aba01d', '20181123005', '084234567896', 'OPEN', 'TAZKIAINF2', 'Test Open Account 005', 10005, '2018-11-01 23:59:59', '2019-02-01', 'ACTIVE');
