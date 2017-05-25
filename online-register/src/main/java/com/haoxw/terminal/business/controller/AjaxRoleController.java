package com.haoxw.terminal.business.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import com.haoxw.terminal.business.dao.AiRoleDao;
import com.haoxw.terminal.business.dao.AiRoleFuncDao;
import com.haoxw.terminal.business.model.AiFunc;
import com.haoxw.terminal.business.model.AiRole;
import com.haoxw.terminal.business.model.AiRoleFunc;
import com.haoxw.terminal.business.util.BaseResult;
import com.haoxw.terminal.business.util.BeanJsonUtil;
import com.haoxw.terminal.business.util.DataResult;

/**
 * 角色控制类
 * 
 * @author zhang
 *
 */
@Controller
@RequestMapping(value = "/ajax/role")
public class AjaxRoleController extends JsonController {

	private Logger logger = LoggerFactory.getLogger(FuncController.class);

	@Resource
	private AiRoleDao aiRoleDao;
	@Resource
	private AiRoleFuncDao aiRoleFuncDao;

	/**
	 * 添加角色
	 * 
	 * @param title
	 * @param descp
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> add(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "descp", required = false) String descp,
			HttpServletRequest request) throws IOException {

		logger.info("传入参数：title" + title + "|descp:" + descp);
		BaseResult br = null;
		try {

			AiRole ar = new AiRole();
			ar.setDescp(descp);
			ar.setState(1);
			ar.setTitle(title);
			boolean b = aiRoleDao.saveRole(ar);
			if (b) {
				logger.info("添加角色成功.....");
				br = new DataResult(BaseResult.SUCCESS.getCode(),
						BaseResult.SUCCESS.getMsg(), "");
			} else {
				logger.info("添加角色出错.....");
				br = new DataResult(BaseResult.FAILED.getCode(),
						BaseResult.FAILED.getMsg(), "");
			}

		} catch (Exception e) {
			logger.error("添加角色出错....", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

	/**
	 * 修改角色
	 * 
	 * @param id
	 * @param title
	 * @param descp
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
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "descp", required = false) String descp,
			HttpServletRequest request) throws IOException {

		logger.info("传入参数:state:" + state + "|id:" + id + "|title:" + title
				+ "|descp:" + descp);
		BaseResult br = null;
		try {

			AiRole ar = new AiRole();
			ar.setDescp(descp);

			ar.setState(state);
			ar.setTitle(title);
			ar.setUptime(System.currentTimeMillis());
			ar.setId(id);
			boolean b = aiRoleDao.updateD(ar);
			if (b) {
				logger.info("修改角色成功.....");
				br = new DataResult(BaseResult.SUCCESS.getCode(),
						BaseResult.SUCCESS.getMsg(), "");
			} else {
				logger.info("修改角色出错.....");
				br = new DataResult(BaseResult.FAILED.getCode(),
						BaseResult.FAILED.getMsg(), "");
			}

		} catch (Exception e) {
			logger.error("修改角色出错....", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/del", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> del(
			@RequestParam(value = "id", required = true) int id,
			HttpServletRequest request) throws IOException {

		logger.info("传入参数:id:" + id);
		BaseResult br = null;
		try {

			boolean b = aiRoleDao.delFunc(id);
			if (b) {
				logger.info("删除角色成功.....");
				br = new DataResult(BaseResult.SUCCESS.getCode(),
						BaseResult.SUCCESS.getMsg(), "");
			} else {
				logger.info("删除角色出错.....");
				br = new DataResult(BaseResult.FAILED.getCode(),
						BaseResult.FAILED.getMsg(), "");
			}

		} catch (Exception e) {
			logger.error("删除角色出错....", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

	/**
	 * 对角色添加功能
	 * 
	 * @param rid
	 *            角色id
	 * @param fid
	 *            功能id
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/addFunc", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> addFunc(
			@RequestParam(value = "rid", required = false) Integer rid,
			@RequestParam(value = "fids", required = false) String fids,
			HttpServletRequest request) throws IOException {

		logger.info("传入参数:rid:" + rid + "|fids" + fids);
		BaseResult br = null;
		try {

			String[] funcid = fids.split(",");// 获取该角色的功能列表

			// 删除该角色对应的原有功能
			boolean dr = aiRoleFuncDao.delRoleFuncByRole(rid);

			// 循环设置该角色现有功能
			for (String fid : funcid) {
				AiRoleFunc arf = new AiRoleFunc();
				arf.setFid(Integer.parseInt(fid));
				arf.setRid(rid);
				boolean b = aiRoleFuncDao.saveRoleFunc(arf);
			}

		} catch (Exception e) {
			logger.error("角色添加功能出错....", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

	/**
	 * 删除角色功能
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/delFunc", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> delFunc(
			@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request) throws IOException {

		logger.info("传入参数:id:" + id);
		BaseResult br = null;
		try {

			boolean b = aiRoleFuncDao.delRoleFunc(id);
			if (b) {
				logger.info("删除角色功能成功.....");
				br = new DataResult(BaseResult.SUCCESS.getCode(),
						BaseResult.SUCCESS.getMsg(), "");
			} else {
				logger.info("删除角色功能出错.....");
				br = new DataResult(BaseResult.FAILED.getCode(),
						BaseResult.FAILED.getMsg(), "");
			}

		} catch (Exception e) {
			logger.error("删除角色功能出错....", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}

		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);

	}

}
