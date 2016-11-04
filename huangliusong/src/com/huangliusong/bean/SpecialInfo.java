package com.huangliusong.bean;

public class SpecialInfo {
	String user_id; // 用户id
	String user_name; // 用户名字
	String userHeaderUrl; // 用户头像
	int up; // 点赞数量
	int down;
	int comments_count; // 评论数量
	int share_count; // 分享数量
	String content; // 内容信息
	String content_image; // 内容图片

	public SpecialInfo() {
	};

	public SpecialInfo(String user_id, String user_name, String userHeaderUrl,
			int up, int down, int comments_count, int share_count,
			String content, String content_image) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.userHeaderUrl = userHeaderUrl;
		this.up = up;
		this.down = down;
		this.comments_count = comments_count;
		this.share_count = share_count;
		this.content = content;
		this.content_image = content_image;
	}

	public String getUserHeaderUrl() {
		return userHeaderUrl;
	}

	public void setUserHeaderUrl(String userHeaderUrl) {
		this.userHeaderUrl = userHeaderUrl;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_icon() {
		return userHeaderUrl;
	}

	public void setUser_icon(String user_icon) {
		this.userHeaderUrl = user_icon;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getComments_count() {
		return comments_count;
	}

	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}

	public int getShare_count() {
		return share_count;
	}

	public void setShare_count(int share_count) {
		this.share_count = share_count;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent_image() {
		return content_image;
	}

	public void setContent_image(String content_image) {
		this.content_image = content_image;
	}

	@Override
	public String toString() {
		return "SpecialInfo [user_id=" + user_id + ", user_name=" + user_name
				+ ", userHeaderUrl=" + userHeaderUrl + ", up=" + up + ", down="
				+ down + ", comments_count=" + comments_count
				+ ", share_count=" + share_count + ", content=" + content
				+ ", content_image=" + content_image + "]";
	}

}
