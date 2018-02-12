create table myboard(
		
	user_prcode int not null,
	user_id varchar(20) not null,
	board_num int not null, 
	user_name varchar(20) not null, 
	board_pass varchar(15) not null, 
	board_subject varchar(50) not null, 
	board_content varchar(2000) not null, 
	board_ori_file varchar(50),
	board_re_file varchar(50), 
	board_re_ref int not null, 
	board_re_lev int not null, 
	board_rs_seq int not null, 
	board_readcount int default 0, 
	board_date date, 
	primary key(board_num),
	foreign key(user_prcode) references userstbl(prcode)
);