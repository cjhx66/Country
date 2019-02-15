package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import utils.SecurityCode;
import utils.SecurityImg;

/**
 * 提供图片验证码
 * @version 1.0 2012/08/22
 */
@SuppressWarnings("serial")
public class SecurityImgAction extends ActionSupport {
    
    //Struts2中Map类型的session
    
    
    //图片流
    private ByteArrayInputStream imageStream;

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    
    public String execute() throws Exception {
        //如果开启Hard模式，可以不区分大小写
//        String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();
        
        //获取默认难度和长度的验证码
        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImg.getImageAsInputStream(securityCode);
        //放入session中
        ActionContext context=ActionContext.getContext();
        context.getSession().put("SESSION_SECURITY_CODE", securityCode);
        return SUCCESS;
    }
}