CREATE TABLE virtual_account (
  id                 VARCHAR(36),
  account_number     VARCHAR(255)   NOT NULL,
  invoice_number     VARCHAR(255)   NOT NULL,
  name               VARCHAR(255)   NOT NULL,
  account_type       VARCHAR(255)   NOT NULL,
  invoice_type       VARCHAR(255)   NOT NULL,
  amount             NUMERIC(19, 2) NOT NULL,
  cumulative_payment NUMERIC(19, 2) NOT NULL DEFAULT 0,
  description        VARCHAR(255),
  email              VARCHAR(255),
  phone              VARCHAR(255),
  create_time        TIMESTAMP      NOT NULL,
  expire_date        DATE           NOT NULL,
  account_status     VARCHAR(255)   NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (invoice_number)
);

CREATE TABLE payment (
  id                    VARCHAR(36),
  id_virtual_account    VARCHAR(36)    NOT NULL,
  amount                NUMERIC(19, 2) NOT NULL,
  transaction_time      TIMESTAMP      NOT NULL,
  client_reference      VARCHAR(36)    NOT NULL,
  payment_status        VARCHAR(255)   NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_virtual_account) REFERENCES virtual_account (id),
  UNIQUE (id_virtual_account,client_reference)
);