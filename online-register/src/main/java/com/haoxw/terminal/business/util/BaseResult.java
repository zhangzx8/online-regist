package com.haoxw.terminal.business.util;

/**
 * 基础返回bean code: 0 正常，非0为异常 msg: 异常信息 前端提示用（***）描述，如果无需前端显示用（无）表示
 * 
 * @author haoxw
 * @since 2011/5/9
 */
@SuppressWarnings("UnusedDeclaration")
public class BaseResult {

	/**
	 * 成功（无）
	 */
	public static final BaseResult SUCCESS = new BaseResult(0, "成功");
	/**
	 * 失败（无）
	 */
	public static final BaseResult FAILED = new BaseResult(1, "失败");

	/**
	 * 无效参数（无）
	 */
	public static final BaseResult INVALID_PARAMETER = new BaseResult(2, "无效参数");
	/**
	 * 无此方法（无）
	 */
	public static final BaseResult NO_SUCH_METHOD = new BaseResult(3, "无此方法");
	/**
	 * 签名失败（无）
	 */
	public static final BaseResult INVALID_SIGN = new BaseResult(4, "验证签名失败");
	/**
	 * 尚未登录（请重新登录后再试）
	 */
	public static final BaseResult NO_LOGIN = new BaseResult(5, "请重新登录后再试");
	/**
	 * 无效用户名密码（帐号或密码错误，请重新输入）
	 */
	public static final BaseResult INVALID_USERNAME_PWD = new BaseResult(6,
			"帐号或密码错误，请重新输入");
	/**
	 * 用户不匹配（无）
	 */
	public static final BaseResult UID_MISMATCHED = new BaseResult(7, "用户不匹配");
	/**
	 * 请求头不符合要求（请求来源非法）
	 */
	public static final BaseResult REFERER_MISMATCHED = new BaseResult(8,"请求来源非法");
	/**
	 * 无效时间（无）
	 */
	public static final BaseResult INVALID_DATETIME = new BaseResult(9, "无效时间");
	/**
	 * 非法请求（非法请求）
	 */
	public static final BaseResult ILLEGAL_REQUEST = new BaseResult(10, "非法请求");
	/**
	 * 服务器错误（网络或服务异常，请稍后再试）
	 */
	public static final BaseResult SERVICE_ERROR = new BaseResult(12, "网络或服务异常，请稍后再试");
	/**
	 * 文件为空
	 */
	public static final BaseResult FILE_NULL = new BaseResult(13, "文件为空");
	
	/**
	 * 文件不存在
	 */
	public static final BaseResult FILE_BLANK = new BaseResult(14, "文件不存在");

	private int code;
	private String msg;

	public BaseResult() {
	}

	public BaseResult(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "BaseResult [code=" + code + ", msg=" + msg + "]";
	}

}
