package cn.ncss.jym.messagebox.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * ***********************
 * 
 *   用户登录controller
 *
 * ***********************
 * @author kyrin [2015年3月10日]
 *
 */
@Controller
@RequestMapping(value="/login")
public class LoginController {
	
//	@Autowired
//	private UserInfoService userInfoService;
//	
//	@RequestMapping(value="",method=RequestMethod.GET)
//	public String login(String name,String password,HttpServletRequest request){
//		if("kyrin".equals(name) && "123".equals(password)){
//			HttpSession session=request.getSession();
//			session.setAttribute(Constant.USER_NAME,name);
//			return "home";
//		}
//		return "login";
//	}
//	
//	@RequestMapping(value="users",method=RequestMethod.GET)
//	public List<UserInfo> users(){
//		return userInfoService.getList();
//	}
//	
//	@RequestMapping(value="list",method=RequestMethod.GET)
//	public ModelAndView get(ModelAndView model){
//		model.setViewName("list");
//		return model;
//	}
//	
	
}
