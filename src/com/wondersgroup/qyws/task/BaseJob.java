package com.wondersgroup.qyws.task;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.wondersgroup.qyws.tjfx.common.PublicStatic;
import com.wondersgroup.util.spring.ApplicationContextUtils;

public abstract class BaseJob extends QuartzJobBean{
	protected final Logger log = Logger.getLogger(this.getClass());
	@Override
	protected void executeInternal(JobExecutionContext context)throws JobExecutionException {
		try{
//			LocalSessionFactoryBean sfb = (LocalSessionFactoryBean)ApplicationContextUtils.getBean(LocalSessionFactoryBean.class);
			SessionFactory sessionFactory = (SessionFactory)ApplicationContextUtils.getBean(PublicStatic.sessionFactory);
//			SessionFactory sessionFactory = (SessionFactory)sfb.getObject();
			Session session = sessionFactory.openSession();
			boolean existingTransaction = SessionFactoryUtils.isSessionTransactional(session,sessionFactory);
			if(existingTransaction){
				this.log.info("Found thread-bound Session for BaseJob");
			}else{
				TransactionSynchronizationManager.bindResource(sessionFactory,new SessionHolder(session));
			}
			try{
				System.out.println("begin to run"); 
				this.run(context);
			}catch(Exception e){
				System.out.println("begin to run-ex:"+e); 
				e.printStackTrace();
			}finally{
				if(existingTransaction){
					this.log.debug("Not closing pre-bound Hibernate Session after BaseJob");  
				}else{
					TransactionSynchronizationManager.unbindResource(sessionFactory);
					SessionFactoryUtils.releaseSession(session, sessionFactory);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected abstract void run(JobExecutionContext context);
	
}