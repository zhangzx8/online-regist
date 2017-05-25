package com.haoxw.terminal.business.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoxw.terminal.business.dao.AiFuncDao;
import com.haoxw.terminal.business.dao.AiUserDao;
import com.haoxw.terminal.business.model.AiFunc;
import com.haoxw.terminal.business.model.AiUser;
import com.haoxw.terminal.business.service.OaService;
import com.haoxw.terminal.business.util.BaseResult;
import com.haoxw.terminal.business.util.BeanJsonUtil;
import com.haoxw.terminal.business.util.Constant;
import com.haoxw.terminal.business.util.CookiesUtil;
import com.haoxw.terminal.business.util.DataResult;

@Controller
public class IndexControll extends JsonController {

	private static final Logger logger = LoggerFactory
			.getLogger(IndexControll.class);

	@Resource
	private OaService oaService;
	@Resource
	private AiUserDao aiUserDao;
	@Resource
	private AiFuncDao aiFuncDao;

	/**
	 * 登录
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/tmp")
	public String tmp(HttpServletRequest request,
			@RequestParam(value = "guid", required = true) String guid,
			HttpServletResponse response, ModelMap modelMap,HttpSession httpSession) throws Exception {
		try {
			modelMap.addAttribute("info", "从controller层设置的变量");

			String res = "";
			
			AiUser user = aiUserDao.getAiUserById(guid);
			if(user != null && user.getUsername().equals("admin")){
				res="admin";
				
			}else{
				res = oaService.login(guid);// 单点登录
			}
			

			if (!StringUtils.isBlank(res)) {
				// 登录成功
				AiUser aiUser = aiUserDao.getAiUserByUserName(res);
				if (aiUser == null) {
					// 单点登录成功,用户不存在
					modelMap.addAttribute("result", "对不起，用户不存在");
					return "fail";
				} 

				List<AiFunc> list = new ArrayList<AiFunc>();
				List<AiFunc> listTmp = aiFuncDao.getFuncByUserByRank(aiUser.getGuid());
				
				for(AiFunc func : listTmp){
					if(!func.getUrl().equals("#")){//去除父节点
						list.add(func);
					}
				}
				
				if (list.size() == 0) {
					modelMap.addAttribute("result", "对不起，您没有权限");
					return "fail";
				}
				modelMap.addAttribute("userSession", aiUser);
				logger.info("111111"+aiUser.toString()+"2222");
				modelMap.addAttribute("province",
						aiUser.getGroupprovince());
				httpSession.setAttribute("guid", aiUser.getGuid());// 设置用户id放入session中
				logger.info("set guid@@@@@:"+httpSession.getAttribute("guid"));
				
				modelMap.addAttribute("list", list);
				modelMap.addAttribute("target", list.get(0));
			} else {
				// 登录失败
				modelMap.addAttribute("result", "对不起，登录失败");
				return "fail";
			}

		} catch (Exception e) {
			logger.error("", e);
			modelMap.addAttribute("result", "对不起，系统出现异常");
			return "fail";
		}

		return "index";
	}
	
	
	@RequestMapping(value = "/indextmp")
	public String indextmp(HttpServletRequest request,
			@RequestParam(value = "guid", required = true) String guid,
			HttpServletResponse response, ModelMap modelMap,HttpSession httpSession) throws Exception {
		    modelMap.addAttribute("guid", guid);
		return "tmp";
	}
	
	
	
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			@RequestParam(value = "guid", required = true) String guid,
			HttpServletResponse response, ModelMap modelMap,HttpSession httpSession) throws Exception {
		try {
			modelMap.addAttribute("info", "从controller层设置的变量");

			String res = "";
			
			AiUser user = aiUserDao.getAiUserById(guid);
			if(user != null && user.getUsername().equals("admin")){
				res="admin";
				
			}else{
				res = oaService.login(guid);// 单点登录
			}
			

			if (!StringUtils.isBlank(res)) {
				// 登录成功
				AiUser aiUser = aiUserDao.getAiUserByUserName(res);
				if (aiUser == null) {
					// 单点登录成功,用户不存在
					modelMap.addAttribute("result", "对不起，用户不存在");
					return "fail";
				} 

				List<AiFunc> list = new ArrayList<AiFunc>();
				List<AiFunc> listTmp = aiFuncDao.getFuncByUserByRank(aiUser.getGuid());
				
				for(AiFunc func : listTmp){
					if(func.getParentid() == Constant.QIANDUAN_SHOW_CONTROLLER){//去除父节点
						list.add(func);
					}
				}
				
				if (list.size() == 0) {
					modelMap.addAttribute("result", "对不起，您没有权限");
					return "fail";
				}
				modelMap.addAttribute("userSession", aiUser);
				logger.info("111111"+aiUser.toString()+"2222");
				modelMap.addAttribute("province",
						aiUser.getGroupprovince());
				CookiesUtil cookie = CookiesUtil.getInstance();
				cookie.addCookie("guid", aiUser.getGuid(), response);
				
				modelMap.addAttribute("list", list);
				modelMap.addAttribute("target", list.get(0));
			} else {
				// 登录失败
				modelMap.addAttribute("result", "对不起，登录失败");
				return "fail";
			}

		} catch (Exception e) {
			logger.error("", e);
			modelMap.addAttribute("result", "对不起，系统出现异常");
			return "fail";
		}

		return "index";
	}
	
	
	/**
	 * 暂不使用 查找记录
	 * 
	 * @param request
	 * @param appkey
	 * @param phone
	 * @param so_sig
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public ResponseEntity<String> login(HttpServletRequest request,
			@RequestParam(value = "appkey", required = false) String appkey,
			@RequestParam(value = "guid", required = true) String guid,
			@RequestParam(value = "so_sig", required = false) String so_sig)
			throws JsonGenerationException, JsonMappingException, IOException {
		BaseResult br = null;
		Map map = new HashMap();
		try {
			// boolean b = oaService.login(guid);
			boolean b = false;
			map.put("b", b);
			AiUser aiUser = aiUserDao.getAiUserById(guid);
			if (aiUser != null)
				map.put("user", aiUser);
			if (b) {
				// 登录成功
				request.getSession().setAttribute("userSession", aiUser);
				request.getSession().setAttribute("province",
						aiUser.getGroupprovince());// 设置用户相关省份相关信息
				request.getSession().setAttribute("guid", aiUser.getGuid());// 设置用户id放入session中
				br = new DataResult(BaseResult.SUCCESS.getCode(),
						BaseResult.SUCCESS.getMsg(), map);
			} else {
				br = new DataResult(BaseResult.FAILED.getCode(),
						BaseResult.FAILED.getMsg(), map);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);
	}

	/**
	 * 登出，清除session
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		request.getSession().invalidate();// 清除session信息

		return "index";
	}

	@RequestMapping(value = "/menu")
	public String menu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "menu";
	}

	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "main";
	}

	@RequestMapping(value = "/fail")
	public String fail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return "fail";
	}

}
