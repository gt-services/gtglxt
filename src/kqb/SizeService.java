package kqb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basis.Hfsession;
import basis.ResultUtils;
import kqb.Size;
import second.Second;
import admin.Admin;

public class SizeService {
	public static Integer getSizeid(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		List<Size> list = new ArrayList<Size>();
		Size Size = new Size();
		try {
			Query q = session.createQuery("from Size order by sizeid desc");
			list = q.list();
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			return list.get(0).getSizeid()+1;
		}
		return 1;
	}
	
	public static List<Size> getListSize(Integer sczid){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		List<Size> list = new ArrayList<Size>();
		try {
			Query q = session.createQuery("from Size where  scz =:scz order by createDate desc").setParameter("scz", sczid);
			list = q.list();
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public static List<Size> getListSizeName(String sczName){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		List<Size> list = new ArrayList<Size>();
		try {
			Query q = session.createQuery("from Size where  scz =:scz order by createDate desc").setParameter("scz", sczName);
			list = q.list();
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
