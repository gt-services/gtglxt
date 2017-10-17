package second;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kqb.Job;
import kqb.Size;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basis.Hfsession;
import basis.ResultUtils;
import second.Second;
import admin.Admin;

public class SecondService {
	public static Integer getSecondId(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		List<Second> list = new ArrayList<Second>();
		Second second = new Second();
		try {
			Query q = session.createQuery("from Second order by secondId desc");
			list = q.list();
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			return list.get(0).getSecondId()+1;
		}
		return 1;
	}
	
	public static List<Second> getListSecond(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		List<Second> list = new ArrayList<Second>();
		try {
			Query q = session.createQuery("from Second order by createDate desc");
			list = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static Map<String,String> getListSize(){
		Session session = Hfsession.init();
		List<Size> list = new ArrayList<Size>();
		Map<String,String> map = new HashMap<>();
		try {
			Query q = session.createQuery("from Size where 1=1");
			list = q.list();
			for(int i =0;i<list.size();i++){
				map.put(list.get(i).getSizeid()+"", list.get(i).getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	@SuppressWarnings("null")
	public static Map<String,String> getListJob(){
		Session session = Hfsession.init();
		List<Job> list = new ArrayList<Job>();
		Map<String,String> map = new HashMap<>();
 		try {
			Query q = session.createQuery("from Job where 1=1 ");
			list = q.list();
			for(int i =0;i<list.size();i++){
				map.put(list.get(i).getSecondId()+"", list.get(i).getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	@SuppressWarnings("null")
	public static Map<String,String> getSecondMap(){
		Session session = Hfsession.init();
		List<Second> list = new ArrayList<Second>();
		Map<String,String> map = new HashMap<>();
 		try {
			Query q = session.createQuery("from Second where 1=1 ");
			list = q.list();
			for(int i =0;i<list.size();i++){
				map.put(list.get(i).getSecondId()+"", list.get(i).getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Map<String,String> getSecondNameToIdMap(){
		Session session = Hfsession.init();
		List<Second> list = new ArrayList<Second>();
		Map<String,String> map = new HashMap<>();
		try {
			Query q = session.createQuery("from Second where 1=1 ");
			list = q.list();
			for(int i =0;i<list.size();i++){
				map.put(list.get(i).getName(),list.get(i).getSecondId()+"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
