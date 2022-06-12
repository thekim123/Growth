package com.growth.cafe.service;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	public void getVideo(HttpServletRequest req) {
		try {
			URI videoPath = resourceLoader.getResource("classpath:static/video").getURI();
			File videoDir = new File(videoPath);
			File[] videos = videoDir.listFiles();
			ArrayList<String> videoFileList = new ArrayList<String>();
			for (File f : videos) {
				videoFileList.add(f.getName());
				System.out.println(f.getName());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
