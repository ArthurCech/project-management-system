INSERT INTO tb_user (first_name, last_name, email, password, created_at) VALUES ('John', 'Doe', 'john.doe@gmail.com', '$2a$12$POSAI1JsPBSGAKZAN8sWJ.mVETZPDtBSCxCnla73GiLOqrixGOaEy', NOW());
INSERT INTO tb_user (first_name, last_name, email, password, created_at) VALUES ('Jane', 'Doe', 'jane.doe@gmail.com', '$2a$12$POSAI1JsPBSGAKZAN8sWJ.mVETZPDtBSCxCnla73GiLOqrixGOaEy', NOW());
INSERT INTO tb_user (first_name, last_name, email, password, created_at) VALUES ('Mark', 'Doe', 'mark.doe@gmail.com', '$2a$12$POSAI1JsPBSGAKZAN8sWJ.mVETZPDtBSCxCnla73GiLOqrixGOaEy', NOW());

INSERT INTO tb_project (owner_id, name, description, status, progress, created_at) VALUES (1, 'ERP Mercado ABC', 'ERP para o mercado ABC unidade Santo André', 'NOT_STARTED', 15, NOW());
INSERT INTO tb_project (owner_id, name, description, status, progress, created_at) VALUES (2, 'Landing Page Salão de Beleza Z', 'Landing Page para o salão de beleza Z de São Caetano do Sul', 'NOT_STARTED', 56, NOW());

INSERT INTO tb_participant (user_id, project_id, role) VALUES (1, 1, 'ADMIN');
INSERT INTO tb_participant (user_id, project_id, role) VALUES (2, 1, 'PARTICIPANT');
INSERT INTO tb_participant (user_id, project_id, role) VALUES (3, 1, 'PARTICIPANT');
INSERT INTO tb_participant (user_id, project_id, role) VALUES (2, 2, 'ADMIN');
INSERT INTO tb_participant (user_id, project_id, role) VALUES (1, 2, 'PARTICIPANT');
INSERT INTO tb_participant (user_id, project_id, role) VALUES (3, 2, 'PARTICIPANT');