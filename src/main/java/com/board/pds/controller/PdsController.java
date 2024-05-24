package com.board.pds.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;
import com.board.pds.domain.PdsVo;
import com.board.pds.mapper.PdsMapper;
@Controller
@RequestMapping("/Pds")
public class PdsController {
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private PdsService pdsService;
	
	// /pds/List?nowpage=1&menu_id=MENUI01
	// 파라미터를 받은 VO가 없으므로 HashMap 이용해서 파하미터 처리
	// HashMap 으로 인자처리할때는 @RequestParam 필수
	@RequestMapping("/List")
	public ModelAndView list(@RequestParam HashMap<String, Object> map) {
		System.out.println("map" + map );
		ModelAndView mv = new ModelAndView();
		// 메뉴 목록
		List<MenuVo>  menuList   =  menuMapper.getMenuList();
		List<PdsVo> pdsList = pdsService.getPsdList();
		mv.addObject("menuList", menuList);
		mv.addObject("map", map);
		mv.addObject("pdsList", pdsList);
		// 자료실 목록
		
		mv.setViewName("pds/list");
		return mv;
	}
	
	@RequestMapping("/WriteForm")
	public ModelAndView writeForm() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

}
