package com.sist.web.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.RentDAO;
import com.sist.web.entity.PageVO;
import com.sist.web.entity.RentEntity;


@RestController
@RequestMapping("rent/")
@CrossOrigin("http://localhost:3000")
public class RentRestController {
		
	@Autowired
	private RentDAO dao;
	
	@GetMapping("main_list_react")
	public List<RentEntity> rent_main_list()
	{
		List<RentEntity> list=dao.rentMainList();
		
		return list;
	}
	
	@GetMapping("main_list_three_react")
	public List<RentEntity> rent_main_three()
	{
		List<RentEntity> list=dao.rentMainThree();
	
		return list;
	}
	
	@RequestMapping("list_react")
	public List<RentEntity> rent_list(String page,String fd)
	{
		if(page==null)
			page="1";
		if(fd==null)
			fd=" ";
		int curpage=Integer.parseInt(page);
		int start=(16*curpage)-16;
		List<RentEntity> list=dao.rentList(fd, start);
		for(RentEntity vo:list)
		{
			String maker=vo.getMaker();
			if(maker.contains("삼성"))
			{
				maker=maker.substring(2,4);
				vo.setMaker(maker);
			}
		}
		
		
		return list;
				
	}
	
	@GetMapping("rent_page_react")
	public PageVO rent_page(String fd,int page)
	{
		int totalpage=dao.rentTotal(fd);
		PageVO vo=new PageVO();
		vo.setCurpage(page);
		vo.setTotalpage(totalpage);
		
		return vo;
	}
}
