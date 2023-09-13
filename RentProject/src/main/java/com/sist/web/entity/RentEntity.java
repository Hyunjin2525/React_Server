package com.sist.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rent2")
@Getter
@Setter
public class RentEntity {
	@Id
	private int rno;
	private String image,car_name,car_type,maker,inwon,fuel,car_option,price;;
}
 