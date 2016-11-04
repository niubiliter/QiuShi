package com.huangliusong.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.huangliusong.bean.SpecialInfo;

public class JsonUtils {

	
	//用来测试ListView能否正常显示这些信息
	public static List<SpecialInfo> josnStringToList(){
		List<SpecialInfo> list = new ArrayList<SpecialInfo>();

		SpecialInfo specialInfo1 = new SpecialInfo();
		specialInfo1.setUser_name("长的龌龊还没钱");
		specialInfo1.setContent("这就是你们想要的好男人");
		specialInfo1.setUser_icon("");
		specialInfo1.setUserHeaderUrl("");
		specialInfo1.setContent_image("");
	
		SpecialInfo specialInfo2 = new SpecialInfo();
		specialInfo2.setUser_name("我的爪机很啰嗦");
		specialInfo2.setContent("新闻内容:今天叫了某某快车，上车后发现所有车窗都是打开的，就问司机为什么所有车窗都打开。"+
"司机观察了一下我说，我怕我打不过你把车窗都打开万一被打劫了好喊救命。我第一次见把省空调钱说得这么文艺。求加人品");
		specialInfo2.setUser_icon("");
		specialInfo2.setUserHeaderUrl("");
		specialInfo2.setContent_image("");
		
		
		
		SpecialInfo specialInfo3 = new SpecialInfo();
		specialInfo3.setUser_name("大大懒虫");
		specialInfo3.setContent("女朋友一直没有打耳洞，快要结婚了，她突然对我说要去打耳洞。我奇怪地问：“你不是怕疼不敢打吗？”女朋友笑了笑说：“我妈说了，不打耳洞的话，结婚的时候少件首饰！”破坏家庭和谐丈母娘啊…");
		specialInfo3.setUser_icon("");
		specialInfo3.setUserHeaderUrl("");
		specialInfo3.setContent_image("");
		
		SpecialInfo specialInfo4 = new SpecialInfo();
		specialInfo4.setUser_name("万诺");
		specialInfo4.setContent("下班坐公交车，刷卡时没注意拿成银行卡了，一直刷不上，我还一脸嫌弃的样子看着司机说：“你这刷卡机是不是坏了”，那司机淡定的说：“要不换身份证刷刷试试？”");
		specialInfo4.setUser_icon("");
		specialInfo4.setUserHeaderUrl("");
		specialInfo4.setContent_image("");
		
		
		SpecialInfo specialInfo5 = new SpecialInfo();
		specialInfo5.setUser_name("杨泽宇？");
		specialInfo5.setContent("你背我过去，我就嫁给你，好多情侣都折在这里了.."+
    "这就是目前包揽多项世界之最的湖南张家界大峡谷玻璃桥！！这座桥高300米，长430米，桥面全部采用透明玻璃铺设，脚踩在上面，低头便可见深不可测的大峡谷。这也被很多情侣称之为“你背我过去，我就嫁给你”的爱情桥，敢不敢带上心爱的人一起走一次？"+
"7月，大峡谷玻璃桥与你一起约定！[呲牙][呲牙]");
		specialInfo5.setUser_icon("");
		specialInfo5.setUserHeaderUrl("");
		specialInfo5.setContent_image("");
		
		SpecialInfo specialInfo6 = new SpecialInfo();
		specialInfo6.setUser_name("奥特曼的粑粑");
		specialInfo6.setContent("儿子四岁，女儿八个月。这两天，天气狠热！昨晚老婆照常呆在空调房里哄女儿睡觉，我呢和儿子一起在冲凉，刚给他冲完还没抹干他就要开门往外冲，我就吼了他两句:外面开了空调，你这样出去会感冒的……臭小子一下停下了，转过头对我大声说:你把妹妹吼醒了，这下过瘾了吧？过瘾了哈？过瘾吧？还特么强调了三遍！劳资居然无言以对啊…");
		specialInfo6.setUser_icon("");
		specialInfo6.setUserHeaderUrl("");
		specialInfo6.setContent_image("");
		SpecialInfo specialInfo7 = new SpecialInfo();
		specialInfo7.setUser_name("你妈逼你吃饭");
		specialInfo7.setContent("女朋友痛经疼的厉害，我让她多喝点热水，她对我各种挤兑，说我不爱她了，于是我带她去医院，挂号，排队会诊，折腾了一下午，医生告诉她让她回去多喝点热水。");
		specialInfo7.setUser_icon("");
		specialInfo7.setUserHeaderUrl("");
		specialInfo7.setContent_image("");
		
		list.add(specialInfo1);
		list.add(specialInfo2);
		list.add(specialInfo3);
		list.add(specialInfo4);
		list.add(specialInfo5);
		list.add(specialInfo6);
		list.add(specialInfo7);
		list.add(specialInfo1);
		list.add(specialInfo2);
		list.add(specialInfo3);
		list.add(specialInfo4);
		list.add(specialInfo5);
		list.add(specialInfo6);
		list.add(specialInfo7);
		list.add(specialInfo1);
		list.add(specialInfo2);
		list.add(specialInfo3);
		list.add(specialInfo4);
		list.add(specialInfo5);
		list.add(specialInfo6);
		list.add(specialInfo7);
		
		return list;
	}
	
	public static List<SpecialInfo> josnStringToList(String jsonString){
		List<SpecialInfo> list = new ArrayList<SpecialInfo>();

		
		try {
			//1. 获取json对象
			JSONObject jsonObject=new JSONObject(jsonString);
			//2. 获取items数组
			JSONArray jsonArray_items=jsonObject.getJSONArray("items");
			for (int i = 0; i < jsonArray_items.length(); i++) {
				SpecialInfo specialInfo=new SpecialInfo();
				JSONObject jsonObject_item=jsonArray_items.getJSONObject(i);
				
				JSONObject jsonObject_user=jsonObject_item.optJSONObject("user");
				if(jsonObject_user==null){
					specialInfo.setUser_name("");
					specialInfo.setUser_icon("");
					specialInfo.setUser_id("");
				}else{
					String name=jsonObject_user.getString("login");
					specialInfo.setUser_name(name);
					String id=jsonObject_user.getString("id");
					specialInfo.setUser_id(id);
					String headImageName=jsonObject_user.getString("icon");
					//用户的头像如何转换为url? 头像图片
					// 将用户的头像转换为url,如何转换  12567793
					//"http://pic.qiushibaike.com/system/avtnew/1256/12567793/medium/20131226014613.jpg"
					String headImagePath = "http://pic.qiushibaike.com/system/avtnew/"
							+ specialInfo.getUser_id().substring(0,
									specialInfo.getUser_id().length() / 2)
							+ "/"
							+ specialInfo.getUser_id()
							+ "/medium/"
							+ headImageName;
					specialInfo.setUser_icon(headImagePath);//这才是真正的url
					
				}
				
				String content=jsonObject_item.getString("content");
				specialInfo.setContent(content);
				//内容图片
				String contentImageName=jsonObject_item.getString("image");
				//内容的图片如何转换为url?
				// 合成连接
				String contentImageUrl = getImageUrl(contentImageName);
				specialInfo.setContent_image(contentImageUrl);
				
				list.add(specialInfo);
			}
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return list;
		
	}
	/**
	 * 合成内容图片的链接地址
	 * 
	 * @param imageName
	 * @return
	 */
	private static String getImageUrl(String imageName) {
		if (imageName.indexOf('.') > 0) {
			StringBuilder sb = new StringBuilder();
			// imageName:app113563076.jpg
			String urlSecond = imageName.substring(3, imageName.indexOf('.')); // 113563076
			String urlFirst = "";
			switch (urlSecond.length()) {
			case 8://8位
				urlFirst = imageName.substring(3, 7); // 1135
				break;
			case 9://9位   113563076
				urlFirst = imageName.substring(3, 8); // 11356
				break;
			}
			// http://pic.qiushibaike.com/system/pictures/11356/113563076/small/app113563076.jpg
			sb.append("http://pic.qiushibaike.com/system/pictures/");
			sb.append(urlFirst);
			sb.append("/");
			sb.append(urlSecond);
			sb.append("/");
			sb.append("small/");
			sb.append(imageName);
			return sb.toString(); 
		} else {
			return "";//不显示图片
		}
		
	}
	
	
	
}
