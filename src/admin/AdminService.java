package admin;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import second.Second;

import basis.Hfsession;

public class AdminService {
	public static String getSecondName(int secondId){
		Session session = Hfsession.init();
		Transaction tx = session.beginTransaction();
		Second second =  new Second();
		try {
			Query q = session.createQuery("from Second where secondId =:secondId").setParameter("secondId", secondId);
			second = (Second)q.uniqueResult();
			tx.commit();
			Hfsession.close();
			if(second == null){
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return second.getName()/*+"生产管理员"*/;
	}
	
}
