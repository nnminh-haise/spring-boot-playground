create table
	accounts (
		id int auto_increment primary key,
		email varchar(255) not null,
		password text not null,
		created_at timestamp default CURRENT_TIMESTAMP not null,
		updated_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
		deleted_at timestamp null,
		constraint email unique (email),
		constraint id unique (id)
	);

create table
	roles (
		id int auto_increment primary key,
		name varchar(255) not null,
		created_at timestamp default CURRENT_TIMESTAMP not null,
		updated_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
		deleted_at timestamp null,
		constraint id unique (id),
		constraint name unique (name)
	);

create table
	employees (
		id int auto_increment primary key,
		first_name text not null,
		last_name text not null,
		citizen_id text null,
		dob date not null,
		address text null,
		phone text not null,
		salary double default 0 null,
		created_at timestamp default CURRENT_TIMESTAMP not null,
		updated_at timestamp default CURRENT_TIMESTAMP not null,
		deleted_at timestamp null,
		role_id int null,
		account_id int null,
		constraint employees_accounts_id_fk foreign key (account_id) references accounts (id) on update cascade,
		constraint employees_roles_id_fk foreign key (role_id) references roles (id) on update cascade,
		constraint employees_salary_chk check (`salary` >= 0)
	);

create table
	users (
		id int auto_increment primary key,
		first_name text not null,
		last_name text not null,
		citizen_id text not null,
		dob date not null,
		address text null,
		phone text not null,
		username text null,
		created_at timestamp default CURRENT_TIMESTAMP not null,
		updated_at timestamp default CURRENT_TIMESTAMP not null,
		deleted_at timestamp null,
		account_id int null,
		constraint users_accounts_id_fk foreign key (account_id) references accounts (id) on update cascade
	);