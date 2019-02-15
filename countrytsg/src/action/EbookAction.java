package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import entity.Ebook;
import entity.User;

import service.BookManageService;
import service.EbookService;

public class EbookAction {
	private EbookService es;
	private File wen;
	private String wenFileName;
	private String upUrl;
	private String eids;

	public String getEids() {
		return eids;
	}

	public void setEids(String eids) {
		this.eids = eids;
	}

	private BookManageService bmService;

	public BookManageService getBmService() {
		return bmService;
	}

	public void setBmService(BookManageService bmService) {
		this.bmService = bmService;
	}

	public File getWen() {
		return wen;
	}

	public void setWen(File wen) {
		this.wen = wen;
	}

	public String getWenFileName() {
		return wenFileName;
	}

	public void setWenFileName(String wenFileName) {
		this.wenFileName = wenFileName;
	}

	public String getUpUrl() {
		return upUrl;
	}

	public void setUpUrl(String upUrl) {
		this.upUrl = upUrl;
	}

	public EbookService getEs() {
		return es;
	}

	public void setEs(EbookService es) {
		this.es = es;
	}

	// 查找全部
	public String getEbook() throws Exception {
		ActionContext cont = ActionContext.getContext();
		User user = (User) cont.getSession().get("user");
		int cid = user.getCid();
		String e = es.getEbook(cid);
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getResponse().getWriter().print(e);
		return null;
	}

	// 文件查找
	public String findEk() throws Exception {
		int id = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("id"));
		Ebook ek = es.findEk(id);
		ServletActionContext.getResponse().getWriter().print(ek.getUrl());
		return null;
	}

	// 上传文件
	public String addEbook() throws Exception {
		String bname = ServletActionContext.getRequest().getParameter("bname");

		ActionContext cont = ActionContext.getContext();
		User user = (User) cont.getSession().get("user");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = sf.parse(sf.format(new Date()));
		String wenType = wenFileName
				.substring(wenFileName.lastIndexOf(".") + 1);
		try {
			InputStream in = new FileInputStream(wen);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String upTime = sdf.format(new Date());
			String s = ServletActionContext.getServletContext().getRealPath(
					upUrl + "\\" + user.getUname() + "\\"
							+ upTime + "\\");
			SimpleDateFormat wenTime = new SimpleDateFormat("HH_mm");
			String tu = wenTime.format(new Date());
			File upFile = new File(s, tu + "." + wenType);
			if (!upFile.getParentFile().exists()) {
				// 如果没有以上路径，则创建
				upFile.mkdirs();
			}
			OutputStream out = new FileOutputStream(upFile);
			byte[] by = new byte[1024 * 1024];
			int len;
			while ((len = in.read(by)) > 0) {
				out.write(by, 0, len);
			}
			Ebook eb = new Ebook();
			eb.setBname(bname);
			eb.setCid(user.getCid());
			eb.setTime(time);
			eb.setType(wenType);
			eb.setUid(user.getUid());
			eb.setUrl(upUrl + "\\"  + user.getUname() + "\\"
					+ upTime + "\\" + tu + "." + wenType);
			boolean b = es.addEbook(eb);
			ServletActionContext.getResponse().getWriter().print(b);
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public String delEbook() throws Exception {
		int num = 0;
		String[] id = eids.split(",");
		for (int i = 0; i < id.length; i++) {
			if (es.delEbook(Integer.parseInt(id[i]))) {
				num = 1;
			}
		}
		ServletActionContext.getResponse().getWriter().print(num);
		return null;

	}
}
