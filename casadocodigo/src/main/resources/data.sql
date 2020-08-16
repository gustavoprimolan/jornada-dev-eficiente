INSERT INTO Author (id, instant, email, name, description) VALUES
    (1, NOW(), 'gustavo.teste@hotmail.com', 'Gustavo', 'Escrevo livros de ficção científica'),
    (2, NOW(), 'pedrin@hotmail.com', 'Pedro', 'Escrevo livros educacionais');


INSERT INTO Category (id, name) VALUES
    (1, 'Educacional'),
    (2, 'Ficção Científica');

INSERT INTO Book (id, title, summary, preface, price, page_number, isbn, publication_date, autor_id, category_id) VALUES
    (1, 'Star Wars', 'Um jovem Jedi ataca novamente', 'Star Wars preface', 50.00, 253, 'isbn01', NOW()+50, 1, 2),
    (2, 'Driven Domain Design', 'Design de código', 'DD preface', 20.00, 560, 'isbn02', NOW()+20, 2, 1);


INSERT INTO Country(id, name) VALUES
    (1, 'Brazil'),
    (2, 'United States of America');


INSERT INTO State(id, name, country_id) VALUES
    (1, 'São Paulo', 1),
    (2, 'Minas Gerais', 1),
    (3, 'Iowa', 2),
    (4, 'Maryland', 2);

INSERT INTO Voucher(id, code, discount_percentage, expiry_date) VALUES
    (1, 'CODE1', 50.00, NOW()+10),
    (2, 'CODE2', 20.00, NOW()-20);

INSERT INTO Purchase (id, email, name, last_name, cpf_cnpj, address, complement, city, phone, cep, total_price, country_id, state_id, voucher_id) VALUES
    (1, 'jose.legal@legalzin.com', 'José', 'Legalzin', '12345678912', 'Rua dos Alfinetes', '144', 'Cidade das Pitangas', '5531945644784', '38567900', 140.00 , 1, 1, 1),
    (2, 'gustavo.legal@chatin.com', 'Gustavo', 'Chatin', '12123779912', 'Rua da Jovem Pan', '144', 'Margarida City', '233798546054', '4451122', 190.00 , 1, 1, 1);

INSERT INTO PurchasedItem (id, quantity, book_id, purchase_id) VALUES
    (1, 2, 2, 1),
    (2, 2, 1, 1),
    (3, 2, 2, 2),
    (4, 3, 1, 2);
