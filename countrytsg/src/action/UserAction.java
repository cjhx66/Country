package action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import service.BorBooksService;
import service.UserService;
import service.impl.BorBooksServiceImpl;

import com.opensymphony.xwork2.ActionContext;

import entity.User;

public class UserAction {
	private User logUser;
	private UserService userService = null;
	private String userid;
	private String pwd;
	private String valiCode;
	private Date time;
	private String uids;
	private CountryAction c;
	private BorBooksServiceImpl bbService;
	

	public BorBooksServiceImpl getBbService() {
		return bbService;
	}

	public void setBbService(BorBooksServiceImpl bbService) {
		this.bbService = bbService;
	}

	public CountryAction getC() {
		return c;
	}

	public void setC(CountryAction c) {
		this.c = c;
	}

	public String getUids() {
		return uids;
	}

	public void setUids(String uids) {
		this.uids = uids;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getLogUser() {
		return logUser;
	}

	public void setLogUser(User logUser) {
		this.logUser = logUser;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getValiCode() {
		return valiCode;
	}

	public void setValiCode(String valiCode) {
		this.valiCode = valiCode;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String login() throws Exception {
		logUser = new User();
		ActionContext actionContext = ActionContext.getContext();
		ActionContext a = ActionContext.getContext();
		int num = 0;
		logUser.setUserid(userid);
		logUser.setPwd(pwd);
		User curUser = userService.login(logUser);
		String preValiCode = actionContext.getSession()
				.get("SESSION_SECURITY_CODE").toString();
		if (!preValiCode.equals(valiCode)) {
			num = 2;
		} else if (curUser != null) {
			actionContext.getSession().put("user", curUser);
			num = 1;
		}
		int id=curUser.getCid();
		String name=c.findName(id);
		a.getSession().put("country", name);
		ServletActionContext.getResponse().getWriter().print(num);
		return null;
	}

	public String logout() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		logUser = (User) actionContext.getSession().get("user");
		if (logUser != null) {
			actionContext.getSession().remove("user");
			ServletActionContext.getResponse().getWriter().print("true");
		}
		return null;
	}

	public String changepwd() throws Exception {
		ActionContext action = ActionContext.getContext();
		logUser = (User) action.getSession().get("user");
		String oldpwd = ServletActionContext.getRequest().getParameter(
				"T_oldpwd");
		String newold = ServletActionContext.getRequest().getParameter(
				"T_newpwd");
		if (oldpwd.equals(logUser.getPwd())) {
			logUser.setPwd(newold);
			boolean b = userService.updatePwd(logUser);
			ServletActionContext.getResponse().getWriter().print(b);
		}
		return null;
	}

	public String getUser() throws Exception {
		String user = userService.getUser();
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		System.out.println(user);
		ServletActionContext.getResponse().getWriter().print(user);
		return null;
	}

	public String findUser() throws Exception {
		int uid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("uid"));
		String c = userService.findUser(uid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(c);
		return null;
	}

	public String editUser() throws Exception {
		String uname = ServletActionContext.getRequest().getParameter("T_name");
		String userid = ServletActionContext.getRequest().getParameter(
				"T_userid");
		String uphone = ServletActionContext.getRequest().getParameter(
				"T_Phone");
		String email = ServletActionContext.getRequest()
				.getParameter("T_Email");
		String sex = ServletActionContext.getRequest().getParameter("T_sex");
		String address = ServletActionContext.getRequest().getParameter(
				"T_Address");
		int uid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("uid"));

		int cid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("T_country_val"));
		String rid = ServletActionContext.getRequest().getParameter(
				"T_role_val");
		int rids = Integer.parseInt(rid);

		User user = userService.fUser(uid);
		user.setUname(uname);
		user.setUserid(userid);
		user.setUserPhone(uphone);
		user.setEmail(email);
		user.setAddress(address);
		user.setSex(sex);
		user.setCid(cid);
		user.setRid(rids);
		boolean b = userService.editUser(user);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;

	}

	public String addUser() throws Exception {
		String uname = ServletActionContext.getRequest().getParameter("T_name");
		String userid = ServletActionContext.getRequest().getParameter(
				"T_userid");
		String userPhone = ServletActionContext.getRequest().getParameter(
				"T_Phone");
		String email = ServletActionContext.getRequest()
				.getParameter("T_Email");
		String sex = ServletActionContext.getRequest().getParameter("T_sex");
		String address = ServletActionContext.getRequest().getParameter(
				"T_Address");
		String pwd = "e10adc3949ba59abbe56e057f20f883e";
		int cid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("T_country_val"));
		int rid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("T_role_val"));
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date addtime = sf.parse(sf.format(new Date()));

		User user = new User();
		user.setUname(uname);
		user.setUserid(userid);
		user.setUserPhone(userPhone);
		user.setEmail(email);
		user.setSex(sex);
		user.setAddress(address);
		user.setPwd(pwd);
		user.setCid(cid);
		user.setRid(rid);
		user.setUaddTime(addtime);

		boolean b = userService.addUser(user);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;
	}

	// 删除用户
	public String delUser() throws Exception {
		int num = 0;
		String[] id = uids.split(",");
		for (int i = 0; i < id.length; i++) {
			String u=findUserid(Integer.parseInt(id[i]));
			boolean b=bbService.delBorBooks(u);
			if (userService.delectUser(Integer.parseInt(id[i]))) {
				num = 1;
			}
		}
		ServletActionContext.getResponse().getWriter().print(num);
		return null;
	}

	// 根据cid查找用户
	public String getUsers() throws Exception {
		ActionContext act = ActionContext.getContext();
		User user = (User) act.getSession().get("user");

		int cid = user.getCid();
		String gs = userService.getUsers(cid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(gs);
		return null;
	}

	/*
	 * // 根据rid查找用户 public String getRids() throws Exception { int rid = 3; int
	 * rs = userService.getRids(rid);
	 * ServletActionContext.getResponse().setCharacterEncoding("utf-8");
	 * ServletActionContext.getResponse().getWriter().print(rs); return null; }
	 */

	// 查找姓名
	public String findName(int id) {
		User u = userService.fUser(id);
		if (u == null) {
			return "--";
		} else {
			return u.getUname();
		}
	}
	
	// 查找uid查找userid
		public String findUserid(int id) {
			User u = userService.fUser(id);
			if (u == null) {
				return "--";
			} else {
				return u.getUserid();
			}
		}

	// 根据userid查找用户名
	public String findName(String userid) {
		User u = userService.fName(userid);
		if (u == null) {
			return "--";
		} else {
			return u.getUname();
		}
	}

	// 判断是否有此用户
	public String findUserid() throws Exception {
		String userid = ServletActionContext.getRequest()
				.getParameter("userid");
		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");

		int cid = user.getCid();
		boolean a = userService.fUserid(cid, userid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(a);
		return null;

	}

	// 读者查看个人信息
	public String findPerson() throws Exception {
		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");
		int uid = user.getUid();
		String u = userService.findUser(uid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(u);
		return null;

	}

	public String editPerson() throws Exception {
		String uname = ServletActionContext.getRequest().getParameter("T_name");
		String phone = ServletActionContext.getRequest()
				.getParameter("T_Phone");
		String email = ServletActionContext.getRequest()
				.getParameter("T_Email");
		String sex = ServletActionContext.getRequest().getParameter("T_sex");
		String address = ServletActionContext.getRequest().getParameter(
				"T_Address");
		ActionContext action = ActionContext.getContext();
		User user = (User) action.getSession().get("user");
		int uid = user.getUid();
		
		User u = userService.fUser(uid);
		u.setUname(uname);
		u.setEmail(email);
		u.setAddress(address);
		u.setSex(sex);
		u.setUserPhone(phone);
		boolean b = userService.editUser(u);
		ServletActionContext.getResponse().getWriter().print(b);
		return null;

	}

	// 获取判断用户编号
	public String getCs() throws Exception {
		ActionContext act = ActionContext.getContext();
		User user = (User) act.getSession().get("user");

		int cid = user.getCid();

		List<User> list = userService.getUs();
		String cs = null;
		int a = 0;
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				cs = list.get(i).getUserid();
				if (Integer.parseInt(cs.substring(cs.indexOf("-") + 1)) > a) {
					a = Integer.parseInt(cs.substring(cs.indexOf("-") + 1));
				}
			}
			a = a + 1;
			if (a < 10) {
				cs = cid + "-000" + a;
			} else if (a < 100) {
				cs = cid + "-00" + a;
			} else {
				cs = cid + "-0" + a;
			}
			ServletActionContext.getResponse().getWriter().print(cs);
		} else {
			ServletActionContext.getResponse().getWriter().print(cid + "-0001");
		}
		return null;

	}
}
