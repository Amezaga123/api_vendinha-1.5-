CREATE TABLE USERS (
        ID INTEGER PRIMARY KEY AUTO_INCREMENT,  -- Coluna ID é a chave primária, do tipo INTEGER, e será auto-incrementada automaticamente pelo banco de dados.
        NAME VARCHAR(255),                      -- Coluna NAME armazena o nome do usuário, com um máximo de 255 caracteres.
        EMAIL VARCHAR(255),
        PASSWORD VARCHAR(255),
        IS_ACTIVE BOOLEAN DEFAULT TRUE,
        CPF_CNPJ VARCHAR (14)
);

CREATE TABLE PRODUTO (
        ID INTEGER PRIMARY KEY AUTO_INCREMENT,  -- Coluna ID é a chave primária, do tipo INTEGER, e será auto-incrementada automaticamente pelo banco de dados.
        NOME VARCHAR(255),                      -- Coluna NAME armazena o nome do usuário, com um máximo de 255 caracteres.
        QUANTIDADE INTEGER,
        PRECO DECIMAL(5,2),
        USER_ID INTEGER REFERENCES USERS (ID)
);

CREATE TABLE PEDIDOS (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    USER_ID INTEGER,
    PRODUCT_ID INTEGER,
    QUANTITY INTEGER,
    PRICE DECIMAL(10, 2),
    FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUTO(ID)
);
