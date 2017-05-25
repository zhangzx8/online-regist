package com.haoxw.terminal.business.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.haoxw.terminal.business.dao.AiFuncDao;
import com.haoxw.terminal.business.dao.AiRoleDao;
import com.haoxw.terminal.business.dao.AiRoleFuncDao;
import com.haoxw.terminal.business.model.AiFunc;
import com.haoxw.terminal.business.model.AiRole;
import com.haoxw.terminal.business.model.AiRoleFunc;
import com.haoxw.terminal.business.util.DateUtil;
import com.haoxw.terminal.business.util.DtreeUtil;

/**
 * 角色控制类
 * 
 * @author zhang
 *
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends JsonController {

	private Logger logger = LoggerFactory.getLogger(FuncController.class);

	@Resource
	private AiRoleDao aiRoleDao;
	@Resource
	private AiRoleFuncDao aiRoleFuncDao;
	@Resource
	private AiFuncDao aiFuncDao;

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		return "role/add";
	}

	@RequestMapping(value = "/rolelist", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String get(HttpServletRequest request, ModelMap modelMap)
			throws IOException {

		try {

			List<AiRole> list = aiRoleDao.allAiRole();
			for(AiRole ar : list){
				Long uptime = ar.getUptime();
				ar.setUpdateTime(DateUtil.long2date(uptime, "yyyy-MM-dd HH:mm:ss"));
			}

			modelMap.addAttribute("list", list);
		} catch (Exception e) {
			logger.error("get 出现异常:" + e);
		}

		return "role/rolelist";

	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String delete(
			@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request, ModelMap modelMap) throws IOException {

		try {

			
			if(id != 1){
				aiRoleDao.delFunc(id);
			}
			
			List<AiRole> list = aiRoleDao.allAiRole();

			modelMap.addAttribute("list", list);

		} catch (Exception e) {
			logger.error("get 出现异常:" + e);
		}

		return "role/rolelist";

	}

	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String update(
			@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request, ModelMap modelMap) throws IOException {

		try {

			AiRole ar = aiRoleDao.getAiRoleById(id);
			modelMap.addAttribute("ar", ar);

		} catch (Exception e) {
			logger.error("get 出现异常:" + e);
		}

		return "role/update";

	}

	@RequestMapping(value = "/funcList", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String addFuncList(
			@RequestParam(value = "rid", required = true) Integer rid,
			HttpServletRequest request, ModelMap modelMap) throws IOException {

		logger.info("传入参数:rid:" + rid);

		try {
			List<AiFunc> list = aiFuncDao.allFunc();// 获取所有功能

			List<AiFunc> child = aiFuncDao.getFuncByRole(rid);// 获取该角色下所有功能

			String modulTree = new DtreeUtil().createCheckboxTreeRoleFuncInfo(
					list, child);
			logger.info("modulTree:" + modulTree);
			modelMap.addAttribute("roleId", rid);
			modelMap.addAttribute("modulTree", modulTree);

		} catch (Exception e) {
			logger.error("异常", e);
		}

		return "func/setFunc";

	}

	@RequestMapping(value = "/addFunc", method = { RequestMethod.GET,
			RequestMethod.POST })
	public String addFunc(
			@RequestParam(value = "rid", required = false) Integer rid,
			@RequestParam(value = "fids", required = false) String fids,
			HttpServletRequest request, ModelMap modelMap) throws IOException {
		logger.info("传入参数:rid:" + rid + "|fids" + fids);
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

			List<AiRole> list = aiRoleDao.allAiRole();

			modelMap.addAttribute("list", list);

		} catch (Exception e) {
			logger.error("角色添加功能出错....", e);
		}

		return "role/rolelist";

	}

}
