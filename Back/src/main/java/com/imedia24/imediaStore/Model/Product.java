package com.imedia24.imediaStore.Model;

import java.math.BigDecimal; 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
public class Product extends Global {
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "euro_price")
	private BigDecimal europrice;
	
	@Column(name = "dolalr_price")
	private BigDecimal dollarprice;

	@Column(name = "mad_price")
	private BigDecimal madprice;
	
	@Column(name = "image")
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "id_category")
	private Category category;

}
