package com.growth.cafe.domain.sns;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.reply.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sns {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public int id;

	@Column(nullable = false, length = 100)
	public String title;

	public String content;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memberId")
	public Member member;

	@CreationTimestamp
	public Timestamp createTime;
	
	@JsonIgnoreProperties({"sns"})
	@OneToMany(mappedBy = "sns", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@OrderBy("id desc")
	public List<Reply> replys;

	/////////////////////////////////////////////////////////////////

}
