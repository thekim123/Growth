package com.growth.cafe.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.growth.cafe.config.auth.PrincipalDetails;
import com.growth.cafe.domain.member.Member;
import com.growth.cafe.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService {

	private final MemberRepository memberRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		Map<String, Object> memberInfo = oAuth2User.getAttributes();
		String name = (String) memberInfo.get("name");
		String password = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());
		String email = (String) memberInfo.get("email");
		String username = "facebook_"+ memberInfo.get("id");
		
		Member memberEntity = memberRepository.findByUsername(username);
		
		if(memberEntity==null) {
			Member member = Member.builder()
					.username(username)
					.password(password)
					.name(name)
					.email(email)
					.role("ROLE_USER")
					.build();

			return new PrincipalDetails(memberRepository.save(member), oAuth2User.getAttributes());
		} else {
			return new PrincipalDetails(memberEntity, oAuth2User.getAttributes());
			
		}
		
	}
	
	
}
