package com.haoxw.terminal.business.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.haoxw.db.oracle.base.PageModel;
import com.haoxw.terminal.business.dao.PriceMonitorDao;
import com.haoxw.terminal.business.model.PageGrid;
import com.haoxw.terminal.business.service.PriceMonitorService;
import com.haoxw.terminal.business.util.BaseResult;
import com.haoxw.terminal.business.util.BeanJsonUtil;
import com.haoxw.terminal.business.util.DataResult;
import com.haoxw.terminal.business.util.DateUtil;
import com.haoxw.terminal.business.util.ExcelUtil;
import com.haoxw.terminal.business.util.ProvinceName;

@Controller
public class ExcelController extends JsonController {

	private static final Logger logger = LoggerFactory
			.getLogger(ExcelUtil.class);
	@Resource
	private PriceMonitorDao priceMonitorDao;
	@Resource
	private PriceMonitorService priceMonitorService;

	/**
	 * 进入前台报表页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/priceMonitor", method = {RequestMethod.GET, RequestMethod.POST})
	public String priceMonitor(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		logger.info("in priceMonitor page..........");
		return "upload/priceMonitor";
	}
	/**
	 * 进入excel上传页面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upFile", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) throws Exception {

		logger.info("upload start..........");
		return "upload/upFile";
	}
	/**
	 * excel上传
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadexcel", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<String> uploadPhoto(HttpServletRequest request,
			@RequestParam(value = "token", required = false) String token)
			throws Exception {
		logger.info("in uploadexcel ");
		long s = System.currentTimeMillis();
		BaseResult br = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		/** 页面控件的文件流 **/
		MultipartFile multipartFile = multipartRequest.getFile("uploadexcel");
		if (multipartFile != null && !multipartFile.isEmpty()) {
			String path = request.getRealPath("/resources/upload")
					+ File.separator + "tmp";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			String filename = System.currentTimeMillis() + ".xls";
			SaveFileFromInputStream(multipartFile.getInputStream(), path,
					filename);
			List<List<String>> list = ExcelUtil.readListExcelFile(path
					+ File.separator + filename, 0);
			priceMonitorService.saveFromExcel(list);
			br = BaseResult.SUCCESS;
		} else {
			br = new BaseResult(1, "请选择excel文件");
		}
		long e = System.currentTimeMillis();
		logger.info("uploadexcel ok " + br.toString() + " use time=" + (e - s)
				/ 1000.0 + " seconds");
		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);
	}

	/**
	 * 保存文件
	 * 
	 * @param stream
	 * @param path
	 * @throws IOException
	 */
	public void SaveFileFromInputStream(InputStream stream, String path,
			String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + File.separator
				+ filename);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}



	@RequestMapping(value = "/getPriceMonitor", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> getPriceMonitor(HttpServletRequest request,
			@RequestParam(value = "province", required = true) String province,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "pageNo", required = false) Integer pageNo,
			@RequestParam(value = "pageSize", required = false) Integer pageSize)
			throws Exception {
		
		logger.info("province:"+province+"|month:"+month+"|pageNo:"+pageNo+"|pageSize:"+pageSize);
		
		if(StringUtils.isEmpty(month)){
			month = DateUtil.getBeforeMonth(new Date());
		}
		
		Map<String, String> map = ProvinceName.provinceCode;
		String provName = map.get(province);
		
		if (pageNo == null || pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize == null || pageSize <= 0) {
			pageSize = 10;
		}

		BaseResult br = null;
		try{
            
			PageGrid pg = priceMonitorService.queryByDays(provName, month, pageNo,
					pageSize);
			br = new DataResult(BaseResult.SUCCESS.getCode(),BaseResult.SUCCESS.getMsg(), pg);
		}catch(Exception e){
			logger.error("异常", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}


		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/downPriceMonitor", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<String> downPriceMonitor(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "province", required = true) String province,
			@RequestParam(value = "month", required = true) String month)
			throws Exception {
		
		logger.info("province:"+province+"|month:"+month);
		if(StringUtils.isEmpty(month)){
			month = DateUtil.getBeforeMonth(new Date());
		}
		
		Map<String, String> map = ProvinceName.provinceCode;
		String provName = map.get(province);


		BaseResult br = null;
		try{
			priceMonitorService.downPriceMonitor(provName, month, response);
			br = new DataResult(BaseResult.SUCCESS.getCode(),BaseResult.SUCCESS.getMsg(), null);
		}catch(Exception e){
			logger.error("异常", e);
			br = new DataResult(BaseResult.FAILED.getCode(),
					BaseResult.FAILED.getMsg(), "");
		}


		return new ResponseEntity<String>(BeanJsonUtil.beanToJson(br, null),
				getResponseHeaders(), HttpStatus.OK);
	}
	
	

}
