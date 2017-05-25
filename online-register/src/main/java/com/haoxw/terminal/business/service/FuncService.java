package com.haoxw.terminal.business.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haoxw.terminal.business.dao.AiFuncDao;
import com.haoxw.terminal.business.model.AiFunc;

/**
 * 功能接口
 * 
 * @author zhang
 *
 */

@Service("funcService")
public class FuncService {

	@Resource
	private AiFuncDao aiFuncDao;

	public void delFunc(int id) {

		// AiFunc af = aiFuncDao.getFuncById(id);// 获取该功能信息

		// if (af != null) {

		aiFuncDao.delFunc(id);//删除该节点

		List<AiFunc> list = aiFuncDao.getFuncByParentid(id);// 获取该功能的子节点列表

		if (list != null && list.size() > 0) {
			// 有子节点
			for (AiFunc afchild : list) {
				this.delFunc(afchild.getId());
			}

		}

		// }

	}

}
