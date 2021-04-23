package kr.upcake.opengraph.vo;

import lombok.Data;

@Data
public class OpenGraphVO {
	private String title;
	private String description;
	private String type;
	private String url;
	private String image;
}