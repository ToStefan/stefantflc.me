INSERT INTO users(user_id, username, password) VALUES(1, "admin", "$2a$04$P2ZZlgrgr/Bx2tdcEcP03eKG69LcZSMsTDRPk3XeksC.o07Y4KjIC");
INSERT INTO users(user_id, username, password) VALUES(2, "mod", "$2a$04$hBAumWm729TG3jAyyjWdXOKOupgd8u8jTpm8Qai4jh5/pyilXDnTG");
INSERT INTO users(user_id, username, password) VALUES(3, "user", "$2a$04$8EFD8yzwsY3jr4rlHUteauDsNlHXW2R9MiA2ITZkRKMg3lQV/cwIa");

INSERT INTO roles (role_id, name) VALUES (1, "ROLE_ADMINISTRATOR");
INSERT INTO roles (role_id, name) VALUES (2, "ROLE_MODERATOR");
INSERT INTO roles (role_id, name) VALUES (3, "ROLE_USER");

INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(1, 2);
INSERT INTO user_roles(user_id, role_id) VALUES(1, 3);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 2);
INSERT INTO user_roles(user_id, role_id) VALUES(3, 1);