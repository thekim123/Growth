package com.growth.cafe.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.growth.cafe.domain.video.FileInfo;
import com.growth.cafe.web.api.FileApiController;

@Service
public class FileService {

	@Value("${spring.servlet.multipart.location}") // multipart에 location으로 설정한 폴더
//@Value("${file.path}") // -> yml파일에 file/path:로 설정한 폴더
	private String filePath;

	private Path getFileName(String filename) {
		return Paths.get(filePath+"/video").resolve(filename);
	}

//	private Date getFileTime(String filename) {
//		Path file = Paths.get(filePath).resolve(filename);
//		BasicFileAttributes attr = null;
//		try {
//			attr = Files.readAttributes(file, BasicFileAttributes.class);
//		} catch (IOException e) {
//			throw new RuntimeException("Could not access file: " + filename);
//		}
//		return new Date(attr.lastModifiedTime().toMillis()); 
//	}

	public Resource getResource(String filename) {
		try {
			Path file = getFileName(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read file: " + filename);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Could not read file: " + filename, e);
		}
	}

	private Stream<Path> getAll() {
		try {
			Path root = Paths.get(filePath);
			return Files.walk(root, 1).filter(path -> !path.equals(root));
		} catch (IOException e) {
			throw new RuntimeException("Failed to read stored files", e);
		}
	}

	public String decodeFile(String filename) {
		String decodeFilename = null;
		try {
			decodeFilename = URLDecoder.decode(filename, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Failed to read files", e);
		}
		return decodeFilename;
	}

	public String encodeFile(String filename) {
		String encodedFilename = null;
		try {
			encodedFilename = URLEncoder.encode(filename, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Failed to read files", e);
		}
		return encodedFilename;
	}

	public List<FileInfo> getListFiles() {
		List<FileInfo> fileInfos = getAll().map(path -> {
			FileInfo fileInfo = new FileInfo();
			String filename = path.getFileName().toString();
			fileInfo.setFilename(filename);
			fileInfo.setUrl(MvcUriComponentsBuilder
					.fromMethodName(FileApiController.class, "getFile", encodeFile(filename)).build().toString());
			return fileInfo;
		}).collect(Collectors.toList());
		return fileInfos;
	}

	public Page<FileInfo> toPage(Pageable pageable) {
		List<FileInfo> fileInfos = getListFiles();
		fileInfos.sort(Comparator.comparing(FileInfo::getFilename, Comparator.nullsLast(Comparator.naturalOrder()))
				.reversed());
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), fileInfos.size());
		if (start > fileInfos.size())
			return new PageImpl<>(new ArrayList<>(), pageable, fileInfos.size());
		return new PageImpl<>(fileInfos.subList(start, end), pageable, fileInfos.size());
	}
}
