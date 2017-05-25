package com.haoxw.terminal.business.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.haoxw.terminal.business.util.DataResult;
import com.haoxw.terminal.business.util.DtreeUtil;

/**
 * 用户控制类
 * 
 * @author zhang
 *
 */
@Controller
@RequestMapping(value = "/ajax/user")
public class AjaxUserController extends JsonController {

	private Logger logger = LoggerFactory.getLogger(AjaxUserController.class);

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
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			HttpServletRequest request, ModelMap modelMap) throws IOException {

		try {
			if (pageNo == null || pageNo < 1) {
				pageNo = 1;
			}

			if (pageSize == null || pageSize < 1) {
				pageSize = 100;
			}

			PageModel pm = aiUserDao.query(pageNo, pageSize);

			List<AiRole> roleList = aiRoleDao.allAiRole();

			modelMap.addAttribute("list", pm.getList());

			modelMap.addAttribute("roleList", roleList);

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

			// List<AiRole> userRoles =
			// aiUserRoleDao.getRoleByUser(uid);//获取用户角色

			// AiUserRole aur = aiUserRoleDao.getUserRoleByUser(uid);
			//
			// int userRoleId = -1;
			// if(aur != null){
			//
			// }

			List<AiRole> allRole = aiRoleDao.allAiRole();// 获取所有角色

			// modelMap.addAttribute("userRole", userRole.size()=0 ? null :
			// userRole);
			modelMap.addAttribute("allRole", allRole);
			modelMap.addAttribute("uid", uid);

		} catch (Exception e) {
			logger.error("异常", e);
		}

		return "user/setRole";

	}

	@RequestMapping(value = "/addRole", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> addRole(
			@RequestParam(value = "roleuserid", required = true) String roleuserid,
			HttpServletRequest request, ModelMap modelMap) throws IOException {

		String[] ruid = roleuserid.split(",");
		logger.info("传入参数:uid:" + ruid[0] + "|rid:" + ruid[1]);
		BaseResult br = null;
		try {

			List<AiUserRole> listrole = aiUserRoleDao
					.getUserRoleByUser(ruid[0]);
			if (listrole == null || listrole.size() < 1) {
				AiUserRole aur = new AiUserRole();
				aur.setRid(Integer.parseInt(ruid[1]));
				aur.setRoleuid(ruid[0]);
				aiUserRoleDao.saveUserRole(aur);
			} else {
				boolean b = aiUserRoleDao.updateUserRoleByUser(
						Integer.parseInt(ruid[1]), ruid[0]);
			}

			br = new DataResult(BaseResult.SUCCESS.getCode(),
					BaseResult.SUCCESS.getMsg(), "");

			// if(b){
			// logger.info("角色设置成功");
			// br = new DataResult(BaseResult.SUCCESS.getCode(),
			// BaseResult.SUCCESS.getMsg(), "");
			// }else{
			// logger.info("角色设置失败");
			// br = new DataResult(BaseResult.FAILED.getCode(),
			// BaseResult.FAILED.getMsg(), "");
			// }

		} catch (Exception e) {
			logger.error("异常", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

	@RequestMapping(value = "/manager")
	public String manager(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {

		try {
			List<AiFunc> list = aiFuncDao.getFuncByRole(31);

			String str = new DtreeUtil().getJSTreeString2(list);
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
			List<AiFunc> list = aiFuncDao.allFunc();
			String str = new DtreeUtil().getJSTreeString2Index(list);
			// String str = new DtreeUtil().getJSTreeString2(list);
			logger.info("str:" + str);
			modelMap.addAttribute("modulTree", str);
		} catch (Exception e) {
			logger.error("e", e);
		}
		return "user/funclist";
	}

	/**
	 * 更新用户信息
	 * 
	 * @param guid
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> update(
			@RequestParam(value = "guid", required = true) String guid,
			@RequestParam(value = "groupprovince", required = true) String groupprovince,
			HttpServletRequest request, ModelMap modelMap) throws IOException {

		logger.info("传入参数:guid:" + guid + "|groupprovince:" + groupprovince);
		BaseResult br = null;
		try {

			AiUser user = aiUserDao.getAiUserById(guid);
			if (user != null) {
				user.setGroupprovince(groupprovince);
			}
			aiUserDao.updateAiUser(user);
			modelMap.addAttribute("user", user);
			
			br = new DataResult(BaseResult.SUCCESS.getCode(),
					BaseResult.SUCCESS.getMsg(), "");

		} catch (Exception e) {
			logger.error("异常", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

}
