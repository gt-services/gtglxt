package basis;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class Hfsession {
		static SessionFactory sessionFactory;  //会话工厂
	    static Session session ;  //会话
		static Transaction tx ;   //事务
	    //此方法用于会话的创建和初始化
		
		public static Session init() {
			sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			return session;
		}
		//关闭会话
		public static void close() {
			if(session.isOpen()){
				session.close();  
		        sessionFactory.close(); 
			}
	        
		}
		/**
		 * 秘钥
		 */
		public static final String KEY_MD = "MD5";
		/**
		 * 加密
		 * @param inputStr
		 * @return
		 */
		public static String getResult(String inputStr)   {    
			//System.out.println("=======加密前的数据:"+inputStr);
			BigInteger bigInteger=null;
			try {
				MessageDigest md = MessageDigest.getInstance(KEY_MD); 
				byte[] inputData = inputStr.getBytes();
				md.update(inputData);
				bigInteger = new BigInteger(md.digest());
				} 
			catch (NoSuchAlgorithmException e) {e.printStackTrace();}
			//System.out.println("MD加密后:" + bigInteger.toString());
			return bigInteger.toString(); 
		}
	
}
