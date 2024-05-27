package com.board.pds.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdsVo {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private int hit;
	private int filescount;
	private String menu_id;
	private String menu_name;
	private int menu_seq;
}
