package com.wondersgroup.qyws.tjfx.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
public class PublicStatic {
	
	
	
	public static String commfile="applicationContext-common-jndi.xml";
	public static String devCommfile="applicationContext-common.xml";
	//统计监测根节点ID
	public static String tjjcRootId="10900001";
	public static String tjjcYwjcId="10920000";
	public static String tjjcjhzId="10910000";
	//系统菜单资源
	public static String SystemMenuResource="SYSTEMMENURESOURCE";
	//用户所属区县
	public static String usrAdminQx="allQx";
	//xmlsocket security message
	public static String securitySwitch="<policy-file-request/>";
	
	//ywjc md5{console缺省密码为wonders,2014}
	public static String key="910278e258ef3760fc6d1884c1733854";
	public static String consoleAdminId="001";
	public static String consoleAccount="CONSOLEACCOUNT";
	//注入查询条件名称
	public static String SearchContent="SEARCHCONTENT";

	public static String indexJszk="INDEXJSZK";
	public static String userMessage="USERMESSAGE";
	//人社药品分
	public static String RSYPFL="rsypfl";
	//医药药品分类
	public static String YBYPFL="ybypfl";
	//全部选项
	public static String ALLCHOICE="ALL";
	
	public static String DDJE="DDJE";
	public static String PSJ="PSJ";
	public static String RZJE="RZJE";
	public static String RKJE="RKJE";
	public static String WCZFJE="WCZFJE";
	public static String THJE="THJE";
	public static String QXMC="QXMC";
	
	
	
	//TB_YSXT_HZTJ反射列表
	public static String byYear="byYear";
	public static String byQuarter="byQuarter";
	public static String byMonth="byMonth";
	public static String byQxdm="byQxdm";
	public static String byJgjb="byJgjb";
	public static String byCgms="byCgms";
	public static String byJgxz="byJgxz";
	public static String byJglx="byJglx";
	public static String bySfyb="bySfyb";
	public static String byJgdm="byJgdm";
	public static String byYplx="byYplx";
	public static String byYpfl="byYpfl";
	public static String byDfybfl="byDfybfl";
	public static String byYppg="byYppg";
	public static String byYppgMap="byYppgMap";
	public static String ypflmodel="ypflmodel";
	
	public static String bySsqx="bySsqx";
	
	public static String byXcqydm="byXcqydm";
	public static String byScqydm="byScqydm";
	
	//业务监测反射列表
	public static String byGhyqYwjc="byGhyqYwjc";
	public static String byJgjbYwjc="byJgjbYwjc";
	public static String byJgmcYwjc="byJgmcYwjc";
	public static String byClztYwjc="byClztYwjc";
	public static String byYplxYwjc="byYplxYwjc";
	public static String byRsypfl1="byRsypfl1";
	public static String byRsypfl2="byRsypfl2";
	public static String byRsypfl3="byRsypfl3";
	public static String byRsypfl4="byRsypfl4";
	public static String byTym="byTym";
	public static String byJx="byJx";
	public static String byScqy="byScqy";
	public static String byGgbz="byGgbz";
	public static String byBzdw="byBzdw";
	public static String bySjkczkYwjc="bySjkczkYwjc";
	public static String byCgmsYwjc="byCgmsYwjc";
	public static String byZslxYwjc="byZslxYwjc";
	public static String byJcdjYwjc="byJcdjYwjc";
	//大屏展示反射列表
	public static String byQxmcBigScreen="byQxmcBigScreen"; //区县名称
	public static String byJgjbBigScreen="byJgjbBigScreen"; //机构级别
	public static String byJgxzBigScreen="byJgxzBigScreen"; //机构性质
	public static String byJglxBigScreen="byJglxBigScreen"; //机构类型
	public static String bySfybBigScreen="bySfybBigScreen"; //是否医保
	public static String byCglxBigScreen="byCglxBigScreen"; //采购类型---------------等同于采购模式
	public static String byJgmcBigScreen="byJgmcBigScreen"; //机构名称
	public static String byYqmcBigScreen="byJgmcBigScreen"; //药企名称
	public static String byYplxBigScreen="byYplxBigScreen"; //药品类型
	//public static String byYpfl; //药品分类类型
	//public static YpflModel ypflmodel; //药品分类对象
	
	//任务detail_task相关
	public static String jczbsz="JCZBSZ";
	public static String jcdj="JCDJ";
	public static String jcsdsz="JCSDSZ";
	public static String per="PER";
	
	public static ApplicationContext context;
	
	
	public static String logProcedure="pkg_business_log.PROC_CREATE_BUSILOG";
	
	public static class DATA{
		public static int Limit = 10;//默认分页条数
	}
	
	public static String socketListenPort="9009";
	public static String getLocalHost()
	{
		HttpServletRequest request =ServletActionContext.getRequest();
		return request.getServerName();
	}
	public static String TOECHAR="TOECHAR";
	
	public static String TOINDEX="TOINDEX";
	
	public static String TOECHAR2="TOECHAR2";
	
	public static String TOLOGIN="TOLOGIN";
	
	public static String TOPAGE9PAGESET="TOPAGE9PAGESET";
	
	public static String TOPAGE10PAGESET="TOPAGE10PAGESET";
	
	public static String BACKTOPAGE10PAGESET="BACKTOPAGE10PAGESET";
	
	public static String TOPAGE11PAGESET="TOPAGE11PAGESET";
	
	public static String TOWHO="toWho";
	
	public static String FROMWHO="fromWho";
	
	public static String SENDCONTENT="sendContent";
	
	public static String MYID="myId";
	
	public static String LOGINUSER="loginUser";
	
	public static List users=new ArrayList();
	
	public static String sessionFactory="sessionFactory";
	
    public static String stringToJson(String s){    
        StringBuffer sb = new StringBuffer();     
        for(int i=0; i<s.length(); i++){     
      
            char c =s.charAt(i);     
            switch(c){     
            case'\"':     
                sb.append("\\\"");     
                break;     
//            case'\\':   //如果不处理单引号，可以释放此段代码，若结合下面的方法处理单引号就必须注释掉该段代码
//                sb.append("\\\\");     
//                break;     
            case'/':     
                sb.append("\\/");     
                break;     
            case'\b':      //退格
                sb.append("\\b");     
                break;     
            case'\f':      //走纸换页
                sb.append("\\f");     
                break;     
            case'\n':     
                sb.append("\\n");//换行    
                break;     
            case'\r':      //回车
                sb.append("\\r");     
                break;     
            case'\t':      //横向跳格
                sb.append("\\t");     
                break;     
            default:     
                sb.append(c);    
            }}
        return sb.toString();     
     }
    
	//count定义输出随机数的长度
	public static String getRandomNum(int count)
	{
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
	}
	
	public static void main(String a[])
	{
		PublicStatic PublicStatic=new PublicStatic();
		System.out.println(PublicStatic.getRandomNum(6));
	}
    
}
