
    create table alimentazione (
        id integer not null auto_increment,
        id_prodotto integer,
        potenza integer not null,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table carrello (
        id integer not null auto_increment,
        id_utente integer,
        numero_prodotti integer not null,
        prezzo_totale integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table casee (
        id integer not null auto_increment,
        id_formato integer,
        id_prodotto integer,
        descrizione varchar(100) not null,
        dimensioni varchar(100),
        primary key (id)
    ) engine=InnoDB;

    create table categoria (
        id integer not null auto_increment,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table categoria_marca (
        id_categoria integer not null,
        id_marca integer not null
    ) engine=InnoDB;

    create table cpu (
        consumo integer not null,
        id integer not null auto_increment,
        id_prodotto integer,
        compatibilita varchar(100) not null,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table formato (
        id integer not null auto_increment,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table laptop (
        consumo integer not null,
        id integer not null auto_increment,
        id_prodotto integer,
        caratteristiche varchar(100) not null,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table marca (
        id integer not null auto_increment,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table memoria (
        id integer not null auto_increment,
        id_prodotto integer,
        spazio integer not null,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table monitor (
        id integer not null auto_increment,
        id_prodotto integer,
        descrizione varchar(100) not null,
        frequenza varchar(100) not null,
        latenza varchar(100) not null,
        risoluzione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table mouse (
        id integer not null auto_increment,
        id_prodotto integer,
        collegamento varchar(100) not null,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table oggetto_nel_carrello (
        id integer not null auto_increment,
        id_carrello integer,
        id_prodotto integer,
        quantita integer not null,
        primary key (id)
    ) engine=InnoDB;

    create table pc (
        id integer not null auto_increment,
        id_alimentazione integer,
        id_casee integer,
        id_cpu integer,
        id_memoria integer,
        id_prodotto integer,
        id_ram integer,
        id_scheda_grafica integer,
        id_scheda_madre integer,
        id_sistema_raffreddamento integer,
        tot_consumo integer not null,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table prodotto (
        costo integer not null,
        id integer not null auto_increment,
        id_categoria integer,
        id_marca integer,
        prezzo integer not null,
        quantita integer not null,
        descrizione varchar(100) not null,
        img mediumtext not null,
        primary key (id)
    ) engine=InnoDB;

    create table ram (
        consumo integer not null,
        id integer not null auto_increment,
        id_prodotto integer,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table scheda_grafica (
        consumo integer not null,
        id integer not null auto_increment,
        id_prodotto integer,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table scheda_madre (
        consumo integer not null,
        id integer not null auto_increment,
        id_formato integer,
        id_prodotto integer,
        compatibilita varchar(100) not null,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table sistema_raffreddamento (
        consumo integer not null,
        id integer not null auto_increment,
        id_prodotto integer,
        descrizione varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table tastiera (
        id integer not null auto_increment,
        id_prodotto integer,
        collegamento varchar(100) not null,
        descrizione varchar(100) not null,
        tipologia varchar(100) not null,
        primary key (id)
    ) engine=InnoDB;

    create table utente (
        id integer not null auto_increment,
        role tinyint check (role between 0 and 2),
        email varchar(255),
        indirizzo varchar(255),
        pwd varchar(255),
        telefono varchar(255),
        user_name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table alimentazione 
       add constraint UKlk1frnhdxn80jtdhyfk1h0s2u unique (id_prodotto);

    alter table alimentazione 
       add constraint UKq4q9p35gc0lk9518ljake6jvb unique (descrizione);

    alter table carrello 
       add constraint UKl287dga2nb4ahi1j34on39ruk unique (id_utente);

    alter table casee 
       add constraint UKgo3orw0mwdoap3amq50hrn3xk unique (id_prodotto);

    alter table casee 
       add constraint UKidu4sigetyt75h8alyodh87j6 unique (descrizione);

    alter table cpu 
       add constraint UKxqg63nqqlvxg1hdk7jjg24ub unique (id_prodotto);

    alter table cpu 
       add constraint UKg1aiaqqupjo2gbcltyvm9slc9 unique (descrizione);

    alter table laptop 
       add constraint UK6vb7425mmfvc3qg7msiesh870 unique (id_prodotto);

    alter table laptop 
       add constraint UKi5axgppkogpjo6tnuum1690n unique (descrizione);

    alter table memoria 
       add constraint UK3mfynf16daxjhg51ij6qay6rk unique (id_prodotto);

    alter table memoria 
       add constraint UKlqcx86e85olgw1y81ja4pyjx8 unique (descrizione);

    alter table monitor 
       add constraint UKnm8fgsmygwbdo60kc9jvpp1jp unique (id_prodotto);

    alter table monitor 
       add constraint UKmfhl7b6u2nwg2hmxl6etjyoh9 unique (descrizione);

    alter table mouse 
       add constraint UKca4mu82y3bcxhfs0bh5levoun unique (id_prodotto);

    alter table mouse 
       add constraint UKl70q0if90w8h4kfpndpad41w2 unique (descrizione);

    alter table pc 
       add constraint UK3v895ypperu2jjqmnybny9bjx unique (id_prodotto);

    alter table pc 
       add constraint UKmrm7a858o2e2s6peuqq03j4ou unique (descrizione);

    alter table ram 
       add constraint UKmdpuem31yj2niayux36i7amhr unique (id_prodotto);

    alter table ram 
       add constraint UKhhumuwy8ncwpucoh23aivg3uf unique (descrizione);

    alter table scheda_grafica 
       add constraint UK4kobty6sbgcb2pixbhl6jor21 unique (id_prodotto);

    alter table scheda_grafica 
       add constraint UKixvwm88n6j7bx0smc8g98yexb unique (descrizione);

    alter table scheda_madre 
       add constraint UKo1tc77cb9trmka4susru0nti1 unique (id_prodotto);

    alter table scheda_madre 
       add constraint UKjxmdfmyty2q8ps7ob2imj16pt unique (descrizione);

    alter table sistema_raffreddamento 
       add constraint UKop6fxv3rx2fd041aqwqq25pet unique (id_prodotto);

    alter table sistema_raffreddamento 
       add constraint UKrujo0vi40ppxa89t8qrxchb5q unique (descrizione);

    alter table tastiera 
       add constraint UK3g3g9pxvmifucp2m05o5jds77 unique (id_prodotto);

    alter table alimentazione 
       add constraint FKbvchl8gmwr1hga1q8yyphwsmf 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table carrello 
       add constraint FKimyxl9cko6g83slko5cldpbh 
       foreign key (id_utente) 
       references utente (id);

    alter table casee 
       add constraint FKku42w51dqftbygngninjhdd9n 
       foreign key (id_formato) 
       references formato (id);

    alter table casee 
       add constraint FKik9u2ydycw3j77lla39csxb1v 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table categoria_marca 
       add constraint FKj75t71e5l5xa8shw2emf7wuf2 
       foreign key (id_categoria) 
       references categoria (id);

    alter table categoria_marca 
       add constraint FKctbxfahjgsv3qndcb8xrsrtis 
       foreign key (id_marca) 
       references marca (id);

    alter table cpu 
       add constraint FKfdbr0gire8e8gc6l1ygwabwdi 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table laptop 
       add constraint FKsilh6uwuvay9uhfehjnragfh2 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table memoria 
       add constraint FKinut5afkfsp8yl9mfw8gtlhpp 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table monitor 
       add constraint FK2dykqnjee67u3dgb1w9a4j02g 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table mouse 
       add constraint FKgmgsa2kl1w8c0q5lxf0cykd3s 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table oggetto_nel_carrello 
       add constraint FK6bfhwb991pmwm4nc9wbarr6kb 
       foreign key (id_carrello) 
       references carrello (id);

    alter table oggetto_nel_carrello 
       add constraint FKhilt76x3lvcoaaxri4f14m4su 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table pc 
       add constraint FK5uaj4l9hhx6qiethnunst1foa 
       foreign key (id_alimentazione) 
       references alimentazione (id);

    alter table pc 
       add constraint FKi1ki5o09ra9kkgvqrukn8msdn 
       foreign key (id_casee) 
       references casee (id);

    alter table pc 
       add constraint FKqv76gwb8fjp08q3fulsvql7y7 
       foreign key (id_cpu) 
       references cpu (id);

    alter table pc 
       add constraint FK9rbn2kmby1k2f3ed6kmr958di 
       foreign key (id_memoria) 
       references memoria (id);

    alter table pc 
       add constraint FK6gtp2t80empf4wfnlpilt6fdi 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table pc 
       add constraint FK1j2g8cr7pbb5wcdaq9clxp8uv 
       foreign key (id_ram) 
       references ram (id);

    alter table pc 
       add constraint FK8i88t7rt1r2dy8ur2cwejy93g 
       foreign key (id_scheda_grafica) 
       references scheda_grafica (id);

    alter table pc 
       add constraint FKbpsbja8xprpdoo2nis82iaagk 
       foreign key (id_scheda_madre) 
       references scheda_madre (id);

    alter table pc 
       add constraint FKvc8y2vdt6mqddqkstg6buuws 
       foreign key (id_sistema_raffreddamento) 
       references sistema_raffreddamento (id);

    alter table prodotto 
       add constraint FKt4r2hlvilai3vmhcsxyryrvkj 
       foreign key (id_categoria) 
       references categoria (id);

    alter table prodotto 
       add constraint FK6d9dw86iywursj1h1e7l59jvi 
       foreign key (id_marca) 
       references marca (id);

    alter table ram 
       add constraint FK9k83d30vit5b27863dyikyvcf 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table scheda_grafica 
       add constraint FKf1nn54fylf1l4l9fki27wr3hy 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table scheda_madre 
       add constraint FKld272atbukd5qvxooqjxps1ni 
       foreign key (id_formato) 
       references formato (id);

    alter table scheda_madre 
       add constraint FKqp8b89bl7v95xg5yy0yklaf0u 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table sistema_raffreddamento 
       add constraint FK8c3fxdxx8x3u4i6wbc815r447 
       foreign key (id_prodotto) 
       references prodotto (id);

    alter table tastiera 
       add constraint FKffc5iu91miyv7ayen4nks3t40 
       foreign key (id_prodotto) 
       references prodotto (id);
