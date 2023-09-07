package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.RentEntity;


@Repository
public interface RentDAO extends JpaRepository<RentEntity, Integer>{
	
	@Query(value = "SELECT * FROM rent2 ORDER BY price ASC LIMIT 1,8",nativeQuery = true)
	public List<RentEntity> rentMainList();
	
	@Query(value = "SELECT * FROM rent2 WHERE maker='현대' LIMIT 0,3",nativeQuery = true)
	public List<RentEntity> rentMainThree();
	
	@Query(value = "SELECT * FROM rent2 WHERE car_name LIKE CONCAT('%',:fd,'%') LIMIT :start,16",nativeQuery = true)
	public List<RentEntity> rentList(@Param("fd") String fd,@Param("start") Integer start);
	
	@Query(value = "SELECT CEIL(COUNT(*)/16.0) FROM rent2 WHERE car_name LIKE CONCAT('%',:fd,'%')",nativeQuery = true)
	public int rentTotal(String fd);
	
}
