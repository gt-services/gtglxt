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

import kqb.Job;
import second.Second;

import admin.Admin;

public class JobService {
	public static Integer getSecondId(){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Map<String,Object> map = new HashMap<String,Object>();
		List<Job> list = new ArrayList<Job>();
		Job job = new Job();
		try {
			Query q = session.createQuery("from Job order by secondId desc");
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
	
	public static List<Job> getListJob(Integer sczid){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		List<Job> list = new ArrayList<Job>();
		try {
			Query q = session.createQuery("from Job where  scz =:scz order by createDate desc").setParameter("scz", sczid);

			list = q.list();
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Job> getListJobName(String sczName){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		List<Job> list = new ArrayList<Job>();
		try {
			Query q = session.createQuery("from Job where  scz =:scz order by createDate desc").setParameter("scz", sczName);

			list = q.list();
			tx.commit();
			Hfsession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
