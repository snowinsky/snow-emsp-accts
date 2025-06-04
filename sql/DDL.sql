-- emsp_accts.t_account definition

CREATE TABLE t_account (
	id BIGINT UNSIGNED auto_increment NOT NULL COMMENT 'primary key',
	vers BIGINT UNSIGNED DEFAULT 0 NOT NULL COMMENT 'version of change',
	email varchar(100) NOT NULL COMMENT 'email, identifier of account',
	contract_id varchar(100) NOT NULL COMMENT 'EMAID format',
	status SMALLINT DEFAULT 0 NOT NULL COMMENT '0: created, 1: actived, 2: deactived',
	create_at DATETIME DEFAULT now() NOT NULL COMMENT 'create time',
	last_updated DATETIME DEFAULT now() NOT NULL    COMMENT 'update time',
	CONSTRAINT t_account_pk PRIMARY KEY (id),
	CONSTRAINT t_account_unique UNIQUE KEY (email)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE t_card (
	id BIGINT UNSIGNED auto_increment NOT NULL COMMENT 'primary key',
	vers BIGINT UNSIGNED DEFAULT 0 NOT NULL COMMENT 'version of change',
	rfid_uid varchar(100) NOT NULL COMMENT 'rfid uid in the card',
	rfid_visible_number varchar(100) NOT NULL COMMENT 'rfid visible number in the card',
	contract_id varchar(100) COMMENT 'EMAID format',
	account_id BIGINT UNSIGNED COMMENT 't_account.id',
	status SMALLINT DEFAULT 0 NOT NULL COMMENT '0: created, 1: assigned, 2: actived, 3: deactived',
	create_at DATETIME DEFAULT now() NOT NULL COMMENT 'create time',
	last_updated DATETIME DEFAULT now() NOT NULL    COMMENT 'last update time',
	CONSTRAINT t_card_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;


-- t_account表：覆盖排序字段
ALTER TABLE t_account ADD INDEX idx_account_updated_id (last_updated DESC, id DESC);

-- t_card表：覆盖关联和排序
ALTER TABLE t_card ADD INDEX idx_card_acctid_id (account_id, id DESC);