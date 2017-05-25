package com.haoxw.terminal.business.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoxw.db.oracle.base.PageModel;
import com.haoxw.terminal.business.dao.AiFuncDao;
import com.haoxw.terminal.business.dao.AiRoleDao;
import com.haoxw.terminal.business.dao.AiUserDao;
import com.haoxw.terminal.business.dao.AiUserRoleDao;
import com.haoxw.terminal.business.model.AiFunc;
import com.haoxw.terminal.business.model.AiRole;
import com.haoxw.terminal.business.model.AiUser;
import com.haoxw.terminal.business.model.AiUserRole;
import com.haoxw.terminal.business.util.BaseResult;
import com.haoxw.terminal.business.util.BeanJsonUtil;
import com.haoxw.terminal.business.util.CookiesUtil;
import com.haoxw.terminal.business.util.DataResult;
import com.haoxw.terminal.business.util.DtreeUtil;

/**
 * 用户控制类
 * 
 * @author zhang
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends JsonController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private AiUserDao aiUserDao;

	@Resource
	private AiUserRoleDao aiUserRoleDao;
	@Resource
	private AiRoleDao aiRoleDao;
	@Resource
	private AiFuncDao aiFuncDao;

	@RequestMapping(value = "/login")
	@ResponseBody
	public ResponseEntity<String> login(HttpServletRequest request)
			throws JsonGenerationException, JsonMappingException, IOException {
		BaseResult br = new DataResult(BaseResult.SUCCESS.getCode(),
				BaseResult.SUCCESS.getMsg(), null);

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);
	}

	/**
	 * 获取用户列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/userlist", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String get(
			@RequestParam(value = "pageNo", required = true) Integer pageNo,
			@RequestParam(value = "pageSize", required = true) Integer pageSize,
			@RequestParam(value = "userName", required = false) String userName,
			HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) throws IOException {

		try {
			PageModel pm = null;
			
			if(pageNo < 1){
				pageNo = 1;
			}
			
			if(pageSize < 1){
				pageNo = 15;
			}
			
			if(StringUtils.isBlank(userName)){
				pm = aiUserDao.query(pageNo, pageSize);
			}else{
				pm = aiUserDao.query(pageNo, pageSize, userName);
			}
		 
			List<AiUser> list = pm.getList();
//			if(list.size() < 1){
//				String redUrl = "/user/userlist?pageNo="+(pageNo-1)+"&pageSize="+pageSize;
//				response.sendRedirect(redUrl);
//			}
			
			for (AiUser au : list) {
				List<AiUserRole> listrole = aiUserRoleDao.getUserRoleByUser(au
						.getGuid());
				if (listrole != null && listrole.size() > 0) {
					au.setRoleid(listrole.get(0).getRid());
				} else {
					au.setRoleid(-1);
				}
			}

			List<AiRole> roleList = aiRoleDao.allAiRole();

			modelMap.addAttribute("list", list);

			modelMap.addAttribute("roleList", roleList);
			modelMap.addAttribute("pageNo", list.size() < 1 ? pageNo-1 : pageNo);
			modelMap.addAttribute("pageSize", pageSize);
			modelMap.addAttribute("totalPages", pm.getTotalPages());

		} catch (Exception e) {
			logger.error("get 出现异常:" + e);
		}

		return "user/userlist";

	}

	/**
	 * 获取用户角色
	 * 
	 * @param uid
	 *            用户id
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/roleList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String addFuncList(
			@RequestParam(value = "uid", required = true) String uid,
			HttpServletRequest request, ModelMap modelMap) throws IOException {

		logger.info("传入参数:rid:" + uid);

		try {

			List<AiRole> allRole = aiRoleDao.allAiRole();// 获取所有角色

			modelMap.addAttribute("allRole", allRole);
			modelMap.addAttribute("uid", uid);

		} catch (Exception e) {
			logger.error("异常", e);
		}

		return "user/setRole";

	}

	@RequestMapping(value = "/addRole", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String addRole(
			@RequestParam(value = "uid", required = true) String uid,
			@RequestParam(value = "rid", required = true) Integer rid,
			HttpServletRequest request, ModelMap modelMap) throws IOException {

		logger.info("传入参数:rid:" + uid);

		try {

			boolean b = aiUserRoleDao.updateUserRoleByUser(rid, uid);

			if (b) {
				logger.info("角色设置成功");
			} else {
				logger.info("角色设置失败");
			}

		} catch (Exception e) {
			logger.error("异常", e);
		}

		return "redirect:/user/userlist";

	}

	@RequestMapping(value = "/manager")
	public String manager(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap,HttpSession httpSession) throws Exception {

		try {

			List<AiFunc> list = new ArrayList<AiFunc>();
			
			String guid = CookiesUtil.getInstance().getCookieValue("guid", request);
			
			//Object guid = httpSession.getAttribute("guid");
			logger.info("@@@@@@@@@@@guid="+guid);
			
			if(guid != null && !guid.equals("")){
				AiUser user = aiUserDao.getAiUserById(guid.toString());
			
				if(user != null && user.getUsername().equals("admin")){
					//admin用户
					list = aiFuncDao.allFunc();
				}else{
					list = aiFuncDao.getFuncByUser(guid.toString());
					
					List<AiUserRole> listRoles = aiUserRoleDao.getUserRoleByUser(guid.toString());
					if(listRoles != null && listRoles.size() > 0){
						int roleId = listRoles.get(0).getRid();//获取该用户角色id
						list = aiFuncDao.getFuncByRole(roleId);
		
					}
				}
			}
				
			//list = aiFuncDao.allFunc();
			

			String str = new DtreeUtil().getJSTreeString2Index(list);
			logger.info("str:" + str);
			modelMap.addAttribute("modulTreeMenu", str);
	
		} catch (Exception e) {
            logger.error("e", e);
		}

		return "manager";
	}

	/**
	 * 用户的功能列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/funclist")
	public String funclist(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {

		try {

			List<AiFunc> list = new ArrayList<AiFunc>();
			Object guid = request.getSession().getAttribute("guid");
			if(guid == null || guid.toString().equals("")){
				list = aiFuncDao.allFunc();
			}else{
				
				list = aiFuncDao.getFuncByUser(guid.toString());
				}
			
			String str = new DtreeUtil().getJSTreeString2Index(list);
			logger.info("str:" + str);
			modelMap.addAttribute("modulTree", str);

		} catch (Exception e) {
			logger.error("e", e);
		}

		return "user/funclist";
	}
	
	
	/**
	 * 获取用户信息返回修改页面
	 * @param uid
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String update(
			@RequestParam(value = "guid", required = true) String guid,
			HttpServletRequest request, ModelMap modelMap) throws IOException {

		logger.info("传入参数:guid:" + guid);

		try {

			AiUser user = aiUserDao.getAiUserById(guid);
			modelMap.addAttribute("user", user);

		} catch (Exception e) {
			logger.error("异常", e);
		}

		return "user/update";

	}

}
