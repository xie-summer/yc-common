package com.ai.yc.common.service.atom.syssensitive;


import com.ai.opt.base.vo.PageInfo;
import com.ai.yc.common.api.syssensitive.param.SensitivePageQueryRequest;
import com.ai.yc.common.api.syssensitive.param.SensitivePageVo;

/**
 * @author shancc
 * @date 2017年5月16日 
 * @version V1.0.1
 */
public interface ISysSensitiveAtomSV {

	PageInfo<SensitivePageVo> querySensitivePage(SensitivePageQueryRequest param);
	
}
