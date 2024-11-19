package com.itwillbs.web;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwillbs.service.MailService;

// @EnableAsync
// => 메서드(동작)가 호출될 때 비동기로 처리가능하게 설정


@Controller
@EnableAsync
public class MailController {
	
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Inject
	private MailService mService;
	
	@GetMapping(value = "/sendMail")
	public String sendMail() throws Exception {
		logger.debug(" ( •̀ ω •́ )✧ sendMail() 호출 ");
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h1> ITWILL 메일 전송 </h1>");
		sb.append("<img src='https://www.incredibleseeds.ca/cdn/shop/products/BeanBush-Provider_720x@2x.jpg?v=1679716832'>");
		sb.append("</body>");
		sb.append("</html>");
		
		mService.sendMail("테스트이메일", "테스트 메일 제목", sb.toString());
		
		return "redirect:/resultMail";
	}
	
}
