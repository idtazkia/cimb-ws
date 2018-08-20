delete from payment;
delete from virtual_account;

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('c98232d6-cead-4005-aaac-4ad26008ca0c', '20180820001', '081234567890', 'CLOSED', 'TAZKIAPMB1', 'Endy Muhardin', 123000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('18bc72cc-fd13-4371-81bb-b05251a62235', '20180820002', '081234567891', 'CLOSED', 'TAZKIAPMB1', 'Gifar Haidar', 250000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('2579fcce-be7b-48cf-b065-f05d25aba01c', '20180820003', '081234567892', 'OPEN', 'TAZKIAINF1', 'Donasi Lombok', 10000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('0a03e5fd-e2d7-43d6-aaf4-db6c14e06a88', '20180820004', '081234567893', 'INSTALLMENT', 'TAZKIAASR1', 'Asrama', 10000000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('f551cacb-fdec-4de9-98a8-1007441612db', '20180820005', '081234567894', 'CLOSED', 'TAZKIASPP1', 'SPP Tetap a.n Mahasiswa 001', 500000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');

insert into virtual_account (id, invoice_number, account_number, account_type, invoice_type, name, amount, create_time, expire_date, account_status)
values ('82ed5496-fa32-4b48-9442-d91e76b6963d', '20180820006', '081234567895', 'CLOSED', 'TAZKIASPP2', 'SPP Variabel a.n Mahasiswa 001', 750000, '2018-08-01 23:59:59', '2019-02-01', 'ACTIVE');