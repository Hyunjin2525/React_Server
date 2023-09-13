package com.sist.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.ShopDAO;
import com.sist.web.entity.PageVO;
import com.sist.web.entity.ShopEntity;

@RestController
@RequestMapping("shop/")
@CrossOrigin("http://localhost:3000")
public class ShopRestController {
	
	@Autowired
	private ShopDAO dao;
	
	@GetMapping("main_list_react")
	public List<ShopEntity> shop_main_list()
	{
		List<ShopEntity> list=dao.shopMainList();
		
		return list;
	}
	
	@GetMapping("main_list_three_react")
	public List<ShopEntity> shop_main_three()
	{
		List<ShopEntity> list=dao.shopMainThree();
		
		for(ShopEntity vo:list)
		{
			String name=vo.getGoods_name();
			name=name.substring(0,name.indexOf("("));
			vo.setGoods_name(name.trim());
		}
		
		return list;
	}
	
	@RequestMapping("list_react")
	public List<ShopEntity> shop_list(String page,String fd)
	{
		if(page==null)
			page="1";
		if(fd==null)
			fd=" ";
		int curpage=Integer.parseInt(page);
		int start=(16*curpage)-16;
		List<ShopEntity> list=dao.shopList(fd, start);
		
		return list;
				
	}
	
	@GetMapping("shop_page_react")
	public PageVO shop_page(String fd,int page)
	{
		int totalpage=dao.shopTotal(fd);
		final int BLOCK=5;
		int startpage=((page-1)/BLOCK*BLOCK)+1;
		int endpage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage)
			endpage=totalpage;	
		PageVO vo=new PageVO();
		vo.setCurpage(page);
		vo.setEndpage(endpage);
		vo.setStartpage(startpage);
		vo.setTotalpage(totalpage);
		
		return vo;
	}
	@GetMapping("shop_detail")
	public ShopEntity shop_detail(String gno)
	{
		ShopEntity vo=dao.findByGno(Integer.parseInt(gno));
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		vo=dao.findByGno(Integer.parseInt(gno));
		return vo;
	}
	
}
