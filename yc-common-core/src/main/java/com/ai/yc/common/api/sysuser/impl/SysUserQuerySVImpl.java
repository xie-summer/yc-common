package com.ai.yc.common.api.sysuser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.common.api.sysuser.interfaces.ISysUserQuerySV;
import com.ai.yc.common.api.sysuser.param.SysUserListQueryRequest;
import com.ai.yc.common.api.sysuser.param.SysUserListQueryResponse;
import com.ai.yc.common.api.sysuser.param.SysUserQueryRequest;
import com.ai.yc.common.api.sysuser.param.SysUserQueryResponse;
import com.ai.yc.common.api.sysuser.param.SysUserThemeRequest;
import com.ai.yc.common.api.sysuser.param.SysUserThemeResponse;
import com.ai.yc.common.service.business.sysuser.ISysUserBusiSV;
import com.ai.yc.common.util.SystemValidateUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class SysUserQuerySVImpl implements ISysUserQuerySV {
	@Autowired
	private ISysUserBusiSV iSysUserBusiSV;

	/**
	 * 查詢用戶信息
	 */
	public SysUserQueryResponse queryUserInfo(SysUserQueryRequest request)
			throws BusinessException, SystemException {
		// 参数校验
		SystemValidateUtil.validateQueryUserInfo(request);
		return iSysUserBusiSV.queryUser(request);
	}

	/**
	 * 查詢用戶主題
	 */

	@Override
	public SysUserThemeResponse queryUserTheme(SysUserThemeRequest request) {
		// 参数校验
		SystemValidateUtil.validateQueryUserTheme(request);
		return iSysUserBusiSV.queryUserTheme(request);
	}

	@Override
	public SysUserListQueryResponse queryUserByOfficeId(SysUserListQueryRequest request)
			throws BusinessException,SystemException {
		// 参数校验
		SystemValidateUtil.validateQueryUserByOfficeId(request);
		return iSysUserBusiSV.queryUserByOfficeId(request);
	}
}
