package com.ai.yc.common.api.syssensitive.param;

import java.io.Serializable;

import com.ai.opt.base.vo.PageInfo;

public class SensitivePageQueryRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
     *  敏感词
     */
	private String sensitiveWords;
	/**
	 * 站点
	 */
	private String site;
	/**
	 * 状态
	 */
	private String state;
	/**
	 *创建人
	 */
	private String creatPeople;
	/**
     * 分页时必填
     */
    PageInfo<SensitivePageVo> pageInfo;
	public String getSensitiveWords() {
		return sensitiveWords;
	}
	public void setSensitiveWords(String sensitiveWords) {
		this.sensitiveWords = sensitiveWords;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreatPeople() {
		return creatPeople;
	}
	public void setCreatPeople(String creatPeople) {
		this.creatPeople = creatPeople;
	}
	public PageInfo<SensitivePageVo> getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo<SensitivePageVo> pageInfo) {
		this.pageInfo = pageInfo;
	}
}
