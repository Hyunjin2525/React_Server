package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.ShopEntity;

@Repository
public interface ShopDAO extends JpaRepository<ShopEntity, Integer>{
	
	@Query(value = "SELECT * FROM goods ORDER BY hit DESC LIMIT 0,8",nativeQuery = true)
	public List<ShopEntity> shopMainList();
	
	@Query(value = "SELECT * FROM goods WHERE goods_name LIKE CONCAT('%','나이키','%') LIMIT 0,3",nativeQuery = true)
	public List<ShopEntity> shopMainThree();
	
	
	@Query(value ="SELECT * FROM goods WHERE goods_name LIKE CONCAT('%',:fd,'%') LIMIT :start,16",nativeQuery = true)
	public List<ShopEntity> shopList(@Param("fd") String fd,@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/16.0) FROM goods WHERE goods_name LIKE CONCAT('%',:fd,'%')",nativeQuery = true)
	public int shopTotal(String fd);
	
	public ShopEntity findByGno(int gno);
}
