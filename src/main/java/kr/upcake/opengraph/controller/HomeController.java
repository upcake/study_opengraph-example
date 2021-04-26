package kr.upcake.opengraph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.upcake.opengraph.lib.OpenGraph;
import kr.upcake.opengraph.vo.OpenGraphVO;

@Controller
public class HomeController {
	
	@GetMapping(value="/")
	public String home(Model model) {
		
		return "index";
	}
	
	@ResponseBody
	@GetMapping(value="/getOpenGraph")
	public OpenGraphVO getOpenGraph(String baseURL) {
		OpenGraphVO ogVO = null;
		
		try {
			OpenGraph page = new OpenGraph(baseURL, true);
			//MetaElement[] meta = page.getProperties();
			
			ogVO = new OpenGraphVO();
			ogVO.setTitle(getContent(page, "title"));
			ogVO.setDescription(getContent(page, "description"));
			ogVO.setImage(getContent(page, "image"));
			ogVO.setType(getContent(page, "type"));
			ogVO.setUrl(getContent(page, "url"));
			ogVO.setAuthor(getContent(page, "article:author"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ogVO;
	}
	
	private String getContent(OpenGraph page, String propertyName) {
		try {
			return page.getContent(propertyName);
		} catch (NullPointerException e) {
			return "태그 없음";
		}
	}
}