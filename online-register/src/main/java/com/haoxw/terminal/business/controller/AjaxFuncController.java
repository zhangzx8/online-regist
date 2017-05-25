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
import com.haoxw.terminal.business.model.AiFunc;
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
@RequestMapping(value = "ajax/func")
public class AjaxFuncController extends JsonController {

	private Logger logger = LoggerFactory.getLogger(AjaxFuncController.class);

	@Resource
	private AiFuncDao aiFuncDao;

	/**
	 * 添加功能
	 * 
	 * @param url
	 * @param title
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> add(
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "rank", required = false) int rank,
			@RequestParam(value = "parentid", required = false) Integer parentid,
			HttpServletRequest request) throws IOException {

		logger.info("传入参数：url" + url + "|title:" + title);
		BaseResult br = null;
		try {

			AiFunc af = new AiFunc();
			af.setState(1);
			af.setTitle(title);
			af.setUptime(System.currentTimeMillis());
			af.setUrl(url);
			af.setParentid(parentid);
			af.setRank(rank);

			boolean b = aiFuncDao.saveFunc(af);

			if (b) {
				logger.info("添加功能成功.....");
				br = new DataResult(BaseResult.SUCCESS.getCode(),
						BaseResult.SUCCESS.getMsg(), "");
			} else {
				logger.info("添加功能出错.....");
				br = new DataResult(BaseResult.FAILED.getCode(),
						BaseResult.FAILED.getMsg(), "");
			}

		} catch (Exception e) {
			logger.error("添加功能异常....", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

	/**
	 * 修改功能
	 * 
	 * @param state
	 * @param id
	 * @param url
	 * @param title
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> update(
			@RequestParam(value = "state", required = true) Integer state,
			@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "url", required = true) String url,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "rank", required = false) int rank,
			@RequestParam(value = "parentid", required = true) Integer parentid,
			HttpServletRequest request) throws IOException {

		logger.info("传入参数：url" + url + "|title:" + title + "|state:" + state
				+ "|id:" + id + "|parentid:" + parentid);
		BaseResult br = null;
		try {

			if(Constant.QIANDUAN_SHOW_CONTROLLER != id){
			AiFunc af = new AiFunc();
			af.setState(state);
			af.setId(id);
			af.setTitle(title);
			af.setUptime(System.currentTimeMillis());
			af.setUrl(url);
			af.setParentid(parentid);
			af.setRank(rank);

			boolean b = aiFuncDao.updateFunc(af);
			if (b) {
				logger.info("修改功能成功.....");
				br = new DataResult(BaseResult.SUCCESS.getCode(),
						BaseResult.SUCCESS.getMsg(), "");
			} else {
				logger.info("修改功能出错.....");
				br = new DataResult(BaseResult.FAILED.getCode(),
						BaseResult.FAILED.getMsg(), "");
			}
			
			}else{
				logger.info("该功能不允许修改");
				br = new DataResult(BaseResult.SUCCESS.getCode(),
						BaseResult.SUCCESS.getMsg(), "");	
			}

		} catch (Exception e) {
			logger.error("修改功能出错....", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

	/**
	 * 获取功能列表
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/get", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> get(HttpServletRequest request)
			throws IOException {

		logger.info("获取功能的列表...............");
		BaseResult br = null;
		try {

			List<AiFunc> list = aiFuncDao.allFunc();

			if (list != null && list.size() > 0) {
				logger.info("获取功能列表成功.....");
				br = new DataResult(BaseResult.SUCCESS.getCode(),
						BaseResult.SUCCESS.getMsg(), list);
			} else {
				logger.info("获取功能列表为空.....");
				br = new DataResult(BaseResult.FAILED.getCode(),
						BaseResult.FAILED.getMsg(), "");
			}

		} catch (Exception e) {
			logger.error("修改功能出现异常....", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

	/**
	 * 删除功能
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/del", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> del(
			@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request) throws IOException {

		logger.info("参数：id:" + id);
		BaseResult br = null;
		try {

			aiFuncDao.delFunc(id);

			logger.info("删除功能成功，功能id:" + id);
			br = new DataResult(BaseResult.SUCCESS.getCode(),
					BaseResult.SUCCESS.getMsg(), "");

		} catch (Exception e) {
			logger.error("删除功能出现异常....", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

	/*
	 * @RequestMapping(value = "/manager") public String
	 * manager(HttpServletRequest request,HttpServletResponse response, ModelMap
	 * modelMap) throws Exception {
	 * 
	 * List<AiFunc> list = aiFuncDao.allFunc();
	 * modelMap.addAttribute("listFunc", list);
	 * 
	 * return "manager2"; }
	 */

	/*
	 * @RequestMapping(value = "/getdtree", method = { RequestMethod.GET,
	 * RequestMethod.POST })
	 * 
	 * @ResponseBody public ResponseEntity<String> getdtree(HttpServletRequest
	 * request) throws IOException {
	 * 
	 * 
	 * BaseResult br = null; try {
	 * 
	 * List<AiFunc> list = aiFuncDao.allFunc(); String str = new
	 * DtreeUtil().getJSTreeString(list); logger.info("str:"+str); br = new
	 * DataResult(BaseResult.SUCCESS.getCode(), BaseResult.SUCCESS.getMsg(),
	 * str);
	 * 
	 * } catch (Exception e) { logger.error("删除功能出现异常....", e); br = new
	 * DataResult(BaseResult.FAILED.getCode(), BaseResult.FAILED.getMsg(), "");
	 * }
	 * 
	 * return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
	 * getResponseHeaders(), HttpStatus.OK);
	 * 
	 * }
	 */

}
