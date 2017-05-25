package com.haoxw.terminal.business.util;

import java.util.Iterator;
import java.util.List;

import com.haoxw.terminal.business.model.AiFunc;

public class DtreeUtil {

	// 返回定义且生成页面树的 js 字符串
	public String getJSTreeString(List<AiFunc> list){
		
		Iterator<AiFunc> treeIt = list.iterator();
		StringBuffer sbf = new StringBuffer();
		
		// 定义js树对象
		sbf.append("<script type=\"text/javascript\">");
		sbf.append("var dtree = new dTree(\"dtree\");");
		sbf.append("dtree.add(0,-1,\"功能列表\");");
		
		while(treeIt.hasNext()){
			AiFunc nd = treeIt.next();
			// 增加 js树结点
			sbf.append("dtree.add("+nd.getId()+","+nd.getParentid()+",\""+nd.getTitle()+"\");");
		}
		// 输出js树
		sbf.append("document.write(dtree);");
		sbf.append("</script>");
		return sbf.toString();
	}
	
	
	
	public String getJSTreeString2(List<AiFunc> list){
		
		Iterator<AiFunc> treeIt = list.iterator();
		StringBuffer sbf = new StringBuffer();
		
		// 定义js树对象
		/*sbf.append("var dtree = new dTree(\"dtree\");");
		sbf.append("dtree.add(0,-1,\"功能列表\");");
		*/
		while(treeIt.hasNext()){
			AiFunc nd = treeIt.next();
			// 增加 js树结点
			sbf.append("d.add("+nd.getId()+","+nd.getParentid()+",\""+nd.getTitle()+"\");");
		}
		// 输出js树
		sbf.append("document.write(d);");
		return sbf.toString();
	}
	
	
	
	
	/**
	 * 生成角色设置功能树
	 * @param allList
	 * @param childList
	 * @return
	 */
	public String createCheckboxTreeRoleFuncInfo(List<AiFunc> allList, List<AiFunc> childList) {
		StringBuffer contents = new StringBuffer();
		// 定义js树对象
		
		for (int i = 0; i < allList.size(); i++) {
			AiFunc func = allList.get(i);
			int m = 0;
			if (childList != null && childList.size() > 0)
				for (AiFunc funcTmp : childList) {
					if (func.getId() == funcTmp.getId()) {
						m++;
						break;
					}
				}
			
			if (m > 0) {// 如果子列表从存在父列表中的第i项

				contents.append("d.add(" + func.getId() + "," + func.getParentid() + ",'" + func.getTitle()
						+ "','" + func.getUrl() + "',1);");
			} else {// 反之不存在

				contents.append("d.add(" + func.getId() + "," + func.getParentid() + ",'" + func.getTitle()
						+ "','" + func.getUrl() + "',0);");

			}
		}
		contents.append("document.write(d);");
		return contents.toString();
	}
	
	
	
	public String getJSTreeString2Index(List<AiFunc> list){
		
		Iterator<AiFunc> treeIt = list.iterator();
		StringBuffer sbf = new StringBuffer();
		
		// 定义js树对象
		/*sbf.append("var dtree = new dTree(\"dtree\");");
		sbf.append("dtree.add(0,-1,\"功能列表\");");
		*/
		while(treeIt.hasNext()){
			AiFunc nd = treeIt.next();
			// 增加 js树结点
			sbf.append("d.add(" + nd.getId() + "," + nd.getParentid() + ",'" + nd.getTitle()
					+ "','" + nd.getUrl() + "',1);");
			//sbf.append("d.add("+nd.getId()+","+nd.getParentid()+",\""+nd.getTitle()+"\");");
		}
		// 输出js树
		sbf.append("document.write(d);");
		return sbf.toString();
	}
	
	
	
}
