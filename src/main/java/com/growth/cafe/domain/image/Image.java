package com.growth.cafe.domain.image;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growth.cafe.domain.member.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(nullable = false)
	private String title;
	
	private String content;
	private String postImageUrl;
	
	@JoinColumn(name = "memberId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Member member;
	
	private LocalDateTime createDate;
	
//	@OrderBy("id Desc")
//	@OneToMany
//	private List<Comment> comments;
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

}
