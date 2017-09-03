package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basis.Hfsession;
import basis.ResultUtils;

import second.Second;

public class SessinAction {
	
	public static List<Object> getList(Object object){
		List<Object> list = new ArrayList<>();
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			String sql = Object.class.toString();
			Query q = session.createQuery("from"+Object.class+"order by createDate desc");
			System.out.println(sql);
			list = q.list();
			if(list.size()>0){
				map.put("statusCode", 200);
				map.put("list",list);
				ResultUtils.toJson(ServletActionContext.getResponse(), map);
			}else{
				map.put("statusCode", 300);
				ResultUtils.toJson(ServletActionContext.getResponse(), map);
			}
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
