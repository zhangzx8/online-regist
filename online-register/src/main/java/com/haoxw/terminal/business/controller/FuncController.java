package com.haoxw.terminal.business.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.haoxw.terminal.business.dao.AiFuncDao;
import com.haoxw.terminal.business.dao.AiRoleFuncDao;
import com.haoxw.terminal.business.model.AiFunc;
import com.haoxw.terminal.business.model.AiRoleFunc;
import com.haoxw.terminal.business.service.FuncService;
import com.haoxw.terminal.business.util.BaseResult;
import com.haoxw.terminal.business.util.BeanJsonUtil;
import com.haoxw.terminal.business.util.Constant;
import com.haoxw.terminal.business.util.DataResult;
import com.haoxw.terminal.business.util.DtreeUtil;

/**
 * 功能控制类
 * 
 * @author zhang
 *
 */
@Controller
@RequestMapping(value = "/func")
public class FuncController extends JsonController {

	private Logger logger = LoggerFactory.getLogger(FuncController.class);

	@Resource
	private AiFuncDao aiFuncDao;
	@Resource
	private AiRoleFuncDao aiRoleFuncDao;
	@Resource
	private FuncService funcService;

	@RequestMapping(value = "/add")
	public String add(@RequestParam(value = "id", required = true) int id,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		modelMap.addAttribute("parentid", id);
		return "func/add";
	}

	@RequestMapping(value = "/update")
	public String update(@RequestParam(value = "id", required = true) int id,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		try{
		AiFunc af = aiFuncDao.getFuncById(id);
		modelMap.addAttribute("af", af);
		}catch(Exception e){
			logger.error("e", e);
		}
		return "func/update";
	}

	@RequestMapping(value = "/funclist")
	public String dtree(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws Exception {
		try{
		List<AiFunc> list = aiFuncDao.allFunc();
		String str = new DtreeUtil().getJSTreeString2(list);
		logger.info("str:" + str);
		modelMap.addAttribute("modulTree", str);
		}catch(Exception e){
			logger.error("e", e);
		}
		return "func/funclist";
	}

	@RequestMapping(value = "/setFunc")
	public String setFunc(@RequestParam(value = "id", required = true) int id,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		try{
		List<AiFunc> list = aiFuncDao.getFuncByRole(id);

		String str = new DtreeUtil().getJSTreeString2(list);
		logger.info("str:" + str);
		modelMap.addAttribute("modulTree", str);
		}catch(Exception e){
			logger.error("e", e);
		}
		
		return "func/setFunc";
	}

	@RequestMapping(value = "/del", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String del(@RequestParam(value = "id", required = true) int id,
			HttpServletRequest request) throws IOException {

		logger.info("参数：id:" + id);
		try {

			//前端首页功能模块不允许删除
			if(Constant.QIANDUAN_SHOW_CONTROLLER != id){
				funcService.delFunc(id);
			}
			// aiFuncDao.delFunc(id);
		

		} catch (Exception e) {
			logger.error("删除功能出现异常....", e);
		}

		return "redirect:/func/funclist";

	}

}
