package com.huangliusong.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.huangliusong.bean.SpecialInfo;

public class JsonUtils {

	
	//��������ListView�ܷ�������ʾ��Щ��Ϣ
	public static List<SpecialInfo> josnStringToList(){
		List<SpecialInfo> list = new ArrayList<SpecialInfo>();

		SpecialInfo specialInfo1 = new SpecialInfo();
		specialInfo1.setUser_name("����������ûǮ");
		specialInfo1.setContent("�����������Ҫ�ĺ�����");
		specialInfo1.setUser_icon("");
		specialInfo1.setUserHeaderUrl("");
		specialInfo1.setContent_image("");
	
		SpecialInfo specialInfo2 = new SpecialInfo();
		specialInfo2.setUser_name("�ҵ�צ���܆���");
		specialInfo2.setContent("��������:�������ĳĳ�쳵���ϳ��������г������Ǵ򿪵ģ�����˾��Ϊʲô���г������򿪡�"+
"˾���۲���һ����˵�������Ҵ򲻹���ѳ���������һ������˺ú��������ҵ�һ�μ���ʡ�յ�Ǯ˵����ô���ա������Ʒ");
		specialInfo2.setUser_icon("");
		specialInfo2.setUserHeaderUrl("");
		specialInfo2.setContent_image("");
		
		
		
		SpecialInfo specialInfo3 = new SpecialInfo();
		specialInfo3.setUser_name("�������");
		specialInfo3.setContent("Ů����һֱû�д��������Ҫ����ˣ���ͻȻ����˵Ҫȥ�����������ֵ��ʣ����㲻�����۲��Ҵ��𣿡�Ů����Ц��Ц˵��������˵�ˣ���������Ļ�������ʱ���ټ����Σ����ƻ���ͥ��г��ĸ�ﰡ��");
		specialInfo3.setUser_icon("");
		specialInfo3.setUserHeaderUrl("");
		specialInfo3.setContent_image("");
		
		SpecialInfo specialInfo4 = new SpecialInfo();
		specialInfo4.setUser_name("��ŵ");
		specialInfo4.setContent("�°�����������ˢ��ʱûע���ó����п��ˣ�һֱˢ���ϣ��һ�һ�����������ӿ���˾��˵��������ˢ�����ǲ��ǻ��ˡ�����˾��������˵����Ҫ�������֤ˢˢ���ԣ���");
		specialInfo4.setUser_icon("");
		specialInfo4.setUserHeaderUrl("");
		specialInfo4.setContent_image("");
		
		
		SpecialInfo specialInfo5 = new SpecialInfo();
		specialInfo5.setUser_name("�����");
		specialInfo5.setContent("�㱳�ҹ�ȥ���Ҿͼ޸��㣬�ö����¶�����������.."+
    "�����Ŀǰ������������֮��ĺ����żҽ��Ͽ�Ȳ����ţ��������Ÿ�300�ף���430�ף�����ȫ������͸���������裬�Ų������棬��ͷ��ɼ���ɲ�Ĵ�Ͽ�ȡ���Ҳ���ܶ����³�֮Ϊ���㱳�ҹ�ȥ���Ҿͼ޸��㡱�İ����ţ��Ҳ��Ҵ����İ�����һ����һ�Σ�"+
"7�£���Ͽ�Ȳ���������һ��Լ����[����][����]");
		specialInfo5.setUser_icon("");
		specialInfo5.setUserHeaderUrl("");
		specialInfo5.setContent_image("");
		
		SpecialInfo specialInfo6 = new SpecialInfo();
		specialInfo6.setUser_name("������������");
		specialInfo6.setContent("�������꣬Ů���˸��¡������죬�������ȣ����������ճ����ڿյ������Ů��˯�������غͶ���һ���ڳ������ո������껹ûĨ������Ҫ��������壬�Ҿͺ���������:���濪�˿յ�����������ȥ���ð�ġ�����С��һ��ͣ���ˣ�ת��ͷ���Ҵ���˵:������ú����ˣ����¹���˰ɣ�����˹������ɣ�����ôǿ�������飡���ʾ�Ȼ�����Զ԰���");
		specialInfo6.setUser_icon("");
		specialInfo6.setUserHeaderUrl("");
		specialInfo6.setContent_image("");
		SpecialInfo specialInfo7 = new SpecialInfo();
		specialInfo7.setUser_name("�������Է�");
		specialInfo7.setContent("Ů����ʹ���۵���������������ȵ���ˮ�������Ҹ��ּ��ң�˵�Ҳ������ˣ������Ҵ���ȥҽԺ���Һţ��Ŷӻ��������һ���磬ҽ��������������ȥ��ȵ���ˮ��");
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
			//1. ��ȡjson����
			JSONObject jsonObject=new JSONObject(jsonString);
			//2. ��ȡitems����
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
					//�û���ͷ�����ת��Ϊurl? ͷ��ͼƬ
					// ���û���ͷ��ת��Ϊurl,���ת��  12567793
					//"http://pic.qiushibaike.com/system/avtnew/1256/12567793/medium/20131226014613.jpg"
					String headImagePath = "http://pic.qiushibaike.com/system/avtnew/"
							+ specialInfo.getUser_id().substring(0,
									specialInfo.getUser_id().length() / 2)
							+ "/"
							+ specialInfo.getUser_id()
							+ "/medium/"
							+ headImageName;
					specialInfo.setUser_icon(headImagePath);//�����������url
					
				}
				
				String content=jsonObject_item.getString("content");
				specialInfo.setContent(content);
				//����ͼƬ
				String contentImageName=jsonObject_item.getString("image");
				//���ݵ�ͼƬ���ת��Ϊurl?
				// �ϳ�����
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
	 * �ϳ�����ͼƬ�����ӵ�ַ
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
			case 8://8λ
				urlFirst = imageName.substring(3, 7); // 1135
				break;
			case 9://9λ   113563076
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
			return "";//����ʾͼƬ
		}
		
	}
	
	
	
}
