配置檔案都寫好
你試試看

1)TestStarter 專門測試 hibernate 是有效，可以用的;
2)TestSpringWithHibernateFunction 測試 Spring 配上 Hibernate 是有效的

兩個都有效之後,我們再來試試看配上MVC。
如果確定連上hibernate,會出現類似這樣的訊息 :
==============================================
Hibernate:
    drop table if exists Empp
Hibernate:
    drop table if exists hibernate_sequence
Hibernate:
    create table Empp (
        id integer not null,
        name varchar(255),
        price varchar(255),
        primary key (id)
    )
==============================================