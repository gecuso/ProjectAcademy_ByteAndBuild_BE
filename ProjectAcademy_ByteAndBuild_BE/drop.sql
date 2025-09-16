
    alter table alimentazione 
       drop 
       foreign key FKbvchl8gmwr1hga1q8yyphwsmf;

    alter table casee 
       drop 
       foreign key FKku42w51dqftbygngninjhdd9n;

    alter table casee 
       drop 
       foreign key FKik9u2ydycw3j77lla39csxb1v;

    alter table categoria_marca 
       drop 
       foreign key FKnhfxbxnxb54pr4edrldnj9vmr;

    alter table categoria_marca 
       drop 
       foreign key FKdd7nib3ab5xa5elnwl75279ui;

    alter table cpu 
       drop 
       foreign key FKfdbr0gire8e8gc6l1ygwabwdi;

    alter table laptop 
       drop 
       foreign key FKsilh6uwuvay9uhfehjnragfh2;

    alter table memoria 
       drop 
       foreign key FKinut5afkfsp8yl9mfw8gtlhpp;

    alter table monitor 
       drop 
       foreign key FK2dykqnjee67u3dgb1w9a4j02g;

    alter table mouse 
       drop 
       foreign key FKgmgsa2kl1w8c0q5lxf0cykd3s;

    alter table pc 
       drop 
       foreign key FK5uaj4l9hhx6qiethnunst1foa;

    alter table pc 
       drop 
       foreign key FKi1ki5o09ra9kkgvqrukn8msdn;

    alter table pc 
       drop 
       foreign key FKqv76gwb8fjp08q3fulsvql7y7;

    alter table pc 
       drop 
       foreign key FK9rbn2kmby1k2f3ed6kmr958di;

    alter table pc 
       drop 
       foreign key FK6gtp2t80empf4wfnlpilt6fdi;

    alter table pc 
       drop 
       foreign key FK1j2g8cr7pbb5wcdaq9clxp8uv;

    alter table pc 
       drop 
       foreign key FK8i88t7rt1r2dy8ur2cwejy93g;

    alter table pc 
       drop 
       foreign key FKbpsbja8xprpdoo2nis82iaagk;

    alter table pc 
       drop 
       foreign key FKvc8y2vdt6mqddqkstg6buuws;

    alter table prodotto 
       drop 
       foreign key FKt4r2hlvilai3vmhcsxyryrvkj;

    alter table prodotto 
       drop 
       foreign key FK6d9dw86iywursj1h1e7l59jvi;

    alter table ram 
       drop 
       foreign key FK9k83d30vit5b27863dyikyvcf;

    alter table scheda_grafica 
       drop 
       foreign key FKf1nn54fylf1l4l9fki27wr3hy;

    alter table scheda_madre 
       drop 
       foreign key FKld272atbukd5qvxooqjxps1ni;

    alter table scheda_madre 
       drop 
       foreign key FKqp8b89bl7v95xg5yy0yklaf0u;

    alter table sistema_raffreddamento 
       drop 
       foreign key FK8c3fxdxx8x3u4i6wbc815r447;

    alter table tastiera 
       drop 
       foreign key FKffc5iu91miyv7ayen4nks3t40;

    drop table if exists alimentazione;

    drop table if exists casee;

    drop table if exists categoria;

    drop table if exists categoria_marca;

    drop table if exists cpu;

    drop table if exists formato;

    drop table if exists laptop;

    drop table if exists marca;

    drop table if exists memoria;

    drop table if exists monitor;

    drop table if exists mouse;

    drop table if exists pc;

    drop table if exists prodotto;

    drop table if exists ram;

    drop table if exists scheda_grafica;

    drop table if exists scheda_madre;

    drop table if exists sistema_raffreddamento;

    drop table if exists tastiera;

    drop table if exists utente_jpa;
