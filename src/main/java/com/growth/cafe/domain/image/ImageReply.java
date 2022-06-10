package com.growth.cafe.domain.image;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.sns.Sns;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageReply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@JoinColumn(name = "imageId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Image image;
	
	@JoinColumn(name="memberId")
	@ManyToOne(fetch = FetchType.EAGER)
	private Member member;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
