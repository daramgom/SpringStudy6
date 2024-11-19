package com.itwillbs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping(value = "/file/*")
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@GetMapping(value = "/form")
	public void uploadForm() throws Exception {
		logger.debug(" ( •̀ ω •́ )✧ 파일업로드 폼페이지 뷰 ");
		logger.debug(" ( •̀ ω •́ )✧ /file/form.jsp 페이지 연결 ");
	}

	@PostMapping(value = "/upload")
	public String fileUpload(MultipartHttpServletRequest multiReq, Model model) throws Exception {
		logger.debug(" ( •̀ ω •́ )✧ fileUpload() 호출 ");

		multiReq.setCharacterEncoding("UTF-8");

		// 파라메터 정보 + 첨부파일 정보 저장
		Map map = new HashMap();

		// 1. 파라메터 정보 처리
		Enumeration enu = multiReq.getParameterNames();
		logger.debug(" ( •̀ ω •́ )✧ enu : {} ", enu);
		while (enu.hasMoreElements()) {
			// 해당 요소가 있을 때 (파라메터 정보가 있을 때)
			String name = (String) enu.nextElement();
			logger.debug(" ( •̀ ω •́ )✧ name : {} ", name);
			String value = multiReq.getParameter(name);
			logger.debug(" ( •̀ ω •́ )✧ value : {} ", value);
			// map에 정보를 모두 저장
			map.put(name, value);

		}

		logger.debug(" ( •̀ ω •́ )✧ map : {} ", map);

		/* ========================================================================== */

		// 2. 첨부파일(업로드 파일) 처리
		List<String> fileList = fileUploadProcess(multiReq);
		map.put("fileList", fileList);

		model.addAttribute("map", map);

		return "/file/uploadResult";
	}// fileUpload()

	// 파일 업로드 처리
	public List<String> fileUploadProcess(MultipartHttpServletRequest multiReq) throws Exception {

		// multiReq.getRealPath("/upload");
		// 파일 정보 저장하는 리스트
		List<String> fileList = new ArrayList<String>();

		// 업로드된 파일 정보를 가져오기
		// 파일 input 태그들의 이름을 가져오기
		Iterator<String> filenames = multiReq.getFileNames();
		logger.debug(" ( •̀ ω •́ )✧ fileNames : {} ", filenames);
		while (filenames.hasNext()) {
			String fileName = (String) filenames.next();
			logger.debug(" ( •̀ ω •́ )✧ fileName : {} ", fileName);
			// 파일 임시 저장
			MultipartFile mFile = multiReq.getFile(fileName);

			// 실제 파일의 이름
			String oFile = mFile.getOriginalFilename();
			// 파일 이름을 리스트에 저장
			fileList.add(oFile);
			File file = new File("C:\\upload\\" + oFile);
			if (mFile.getSize() != 0) {
				// => 파일(mfile)이 있을 때
				if (!file.exists()) {
					// => 파일(file)이 해당 경로에 없을 때
					if (file.getParentFile().mkdirs()) {
						// 해당 파일의 부모 폴더(디렉토리 C:\\upload\\) 생성
						file.createNewFile();
					} // 3
				} // 2
					// 파일 업로드 수행(임시파일 -> 실제파일)
				mFile.transferTo(file);
			} // 1
		} // while

		return fileList;
	}

	// 파일 다운로드 처리
	@GetMapping(value = "/download")
	public void fileDownload(@RequestParam("fileName") String fileName, HttpServletResponse res) throws Exception {
		logger.debug(" ( •̀ ω •́ )✧ 파일 다운로드 처리 fileDownload() 호출");
		logger.debug(" ( •̀ ω •́ )✧ 다운로드할 파일명 : {} ", fileName);

		// 다운로드할 파일
		File dFile = new File("C:\\upload\\" + fileName);

		// 외부로 정보 전송
		OutputStream out = res.getOutputStream();

		// 뷰페이지 -> 다운로드 창으로
		res.setHeader("Cache-Control", "no-cache");
		res.addHeader("Content-disposition", "attachment; fileName=" + URLEncoder.encode(fileName, "UTF-8"));

		// 다운로드 파일을 가져와서 화면(다운로드창)으로 전송
		FileInputStream fis = new FileInputStream(dFile);

		byte[] buffer = new byte[1024 * 8]; // 8KB

		int data = 0;
		while ((data = fis.read(buffer)) != -1) { // 파일의 끝(-1)
			out.write(buffer, 0, data);
		}
		out.flush(); // 버퍼에 공백 전달
		fis.close();
		out.close();

		// /file/download.jsp 뷰 페이지 연결
	}

	@GetMapping(value = "/thDownload")
	public void thumbDownload(@RequestParam("fileName") String fileName, HttpServletResponse res) throws Exception {
		OutputStream out = res.getOutputStream();
		
		// 다운로드할 파일
		File dFile = new File("C:\\upload\\" + fileName);
		
		// 썸네일 파일 생성 X & 화면에 출력
				if(dFile.exists()) {
					// 썸네일 파일을 화면출력
					Thumbnails.of(dFile)
							  .size(100, 100)
							  .outputFormat("png")
							  .toOutputStream(out);
				} else {
					return;
				}
		
		/*
		// 썸네일 파일 생성 & 화면에 출력
		int lastIdx = fileName.lastIndexOf(".");
		String onlyFileName = fileName.substring(0,lastIdx);
		logger.debug(" ( •̀ ω •́ )✧ onlyFileName : {} ",onlyFileName);
		File thumbnail = new File("C:\\upload\\"+"thumbnail\\"+onlyFileName+".png");
		
		if(dFile.exists()) {
			thumbnail.getParentFile().mkdirs();
			Thumbnails.of(dFile)
					  .size(50, 50)
					  .outputFormat("png")
					  .toFile(thumbnail);;
		}
		
		// 썸네일 이미지 출력
		FileInputStream fis = new FileInputStream(thumbnail);
		
		byte[] buffer = new byte[1024*8];
		int data = 0;
		while((data = fis.read(buffer)) != -1) {
			out.write(buffer,0,data);
		}
		out.flush();
		out.close();
		fis.close();
		*/
		
	}

}// controller
