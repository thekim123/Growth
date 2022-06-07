package com.growth.cafe.domain.reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.growth.cafe.domain.sns.Sns;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
}
