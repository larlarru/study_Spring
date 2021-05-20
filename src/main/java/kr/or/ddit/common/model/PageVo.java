package kr.or.ddit.common.model;

public class PageVo {
	
   private int page;
   private int pageSize;
   
   private String userid;
   private String usernm;
   private String alias;
   
   public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public String getUsernm() {
	return usernm;
}

public void setUsernm(String usernm) {
	this.usernm = usernm;
}

public String getAlias() {
	return alias;
}

public void setAlias(String alias) {
	this.alias = alias;
}

public PageVo() {}
   
//   public PageVo(int page, int pageSize) {
//      this.page=page;
//      this.pageSize=pageSize;
//   }

   public PageVo(int page, int pageSize, String userid, String usernm, String alias) {
      this.page=page;
      this.pageSize=pageSize;
      this.userid=userid;
      this.usernm=usernm;
      this.alias=alias;
   }


   
   public int getPage() {
      return page;
   }
   public void setPage(int page) {
      this.page = page;
   }
   public int getPageSize() {
      return pageSize;
   }
   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }

   
}