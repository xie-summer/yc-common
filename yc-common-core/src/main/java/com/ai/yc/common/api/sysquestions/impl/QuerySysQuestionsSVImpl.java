package com.ai.yc.common.api.sysquestions.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.yc.common.api.sysitembank.param.ItemBankPageQueryRequest;
import com.ai.yc.common.api.sysitembank.param.ItemBankPageVo;
import com.ai.yc.common.api.sysquestions.interfaces.IQuerySysQuestionsSV;
import com.ai.yc.common.api.sysquestions.param.DeleteSysQuestions;
import com.ai.yc.common.api.sysquestions.param.QuestionsPageQueryRequest;
import com.ai.yc.common.api.sysquestions.param.QuestionsPageQueryResponse;
import com.ai.yc.common.api.sysquestions.param.QuestionsPageVo;
import com.ai.yc.common.api.sysquestions.param.QuestionsPapersResponse;
import com.ai.yc.common.api.sysquestions.param.QuestionsPapersVo;
import com.ai.yc.common.api.sysquestions.param.SaveSysQuestions;
import com.ai.yc.common.service.business.sysitembank.IQuerySysItemBankBusiSV;
import com.ai.yc.common.service.business.sysquestions.IQuerySysQuestionsBusiSV;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author shancc
 * @date 2017年5月16日 
 * @version V1.0.1
 */
@Service
@Component
public class QuerySysQuestionsSVImpl implements IQuerySysQuestionsSV {
	
	@Autowired
	private transient IQuerySysQuestionsBusiSV iQuerySysQuestionsBusiSV;
	
	@Autowired
	private transient IQuerySysItemBankBusiSV iQuerySysItemBankBusiSV;


	@Override
	public QuestionsPageQueryResponse queryQuestionsPage(QuestionsPageQueryRequest param)
			throws BusinessException, SystemException {
		
		QuestionsPageQueryResponse questionsPageQueryResponse = new QuestionsPageQueryResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		try {
            PageInfo<QuestionsPageVo> pageInfo = iQuerySysQuestionsBusiSV.queryQuestionsPage(param);
            questionsPageQueryResponse.setPageInfo(pageInfo);
            responseHeader.setIsSuccess(true);
            responseHeader.setResultCode(ExceptCodeConstants.Special.SUCCESS);
            responseHeader.setResultMessage("题目查询成功");
            questionsPageQueryResponse.setResponseHeader(responseHeader);
        }catch (BusinessException businessException){
            responseHeader.setResultCode(businessException.getErrorCode());
            responseHeader.setResultMessage(businessException.getErrorMessage());
            questionsPageQueryResponse.setResponseHeader(responseHeader);
        }catch (Exception e){
            responseHeader.setResultCode(ExceptCodeConstants.Special.SYSTEM_ERROR);
            responseHeader.setResultMessage("题目查询失败");
            questionsPageQueryResponse.setResponseHeader(responseHeader);
        }
		return questionsPageQueryResponse;
	}


	@Override
	public BaseResponse saveSysQuestions(SaveSysQuestions req) throws BusinessException, SystemException {
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		try {
			iQuerySysQuestionsBusiSV.saveSysQuestions(req);
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SUCCESS);
			responseHeader.setResultMessage("添加题目成功");
			response.setResponseHeader(responseHeader);
		}catch (Exception e) {
			throw new SystemException(ExceptCodeConstants.Special.SYSTEM_ERROR,"添加题目失败");
		}
		return response;
	}


	@Override
	public Integer deleteSysQuestions(DeleteSysQuestions param) throws BusinessException, SystemException {
		return iQuerySysQuestionsBusiSV.deleteSysQuestions(param.getQid());
	}


	@Override
	public BaseResponse updateSysQuestions(SaveSysQuestions req) throws BusinessException, SystemException {
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		try {
			iQuerySysQuestionsBusiSV.updateSysQuestions(req);
			responseHeader.setIsSuccess(true);
			responseHeader.setResultCode(ExceptCodeConstants.Special.SUCCESS);
			responseHeader.setResultMessage("修改题目成功");
			response.setResponseHeader(responseHeader);
		}catch (Exception e) {
			throw new SystemException(ExceptCodeConstants.Special.SYSTEM_ERROR,"修改题目失败");
		}
		return response;
	}


	@Override
	public Integer queryQuestionsNumber(QuestionsPageQueryRequest param) throws BusinessException, SystemException {
		return iQuerySysQuestionsBusiSV.queryQuestionsNumber(param);
	}


	@Override
	public QuestionsPapersResponse questionsPapers(ItemBankPageQueryRequest param) throws BusinessException, SystemException {
		if(param==null){
			throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL, "参数不能为空");
		}
		QuestionsPapersResponse questionsPapersResponses = new QuestionsPapersResponse();
		PageInfo<ItemBankPageVo> queryItemBankPage = iQuerySysItemBankBusiSV.queryItemBankPage(param);
		List<ItemBankPageVo> result = queryItemBankPage.getResult();
		for (ItemBankPageVo itemBankPageVo : result) {
			List<QuestionsPapersVo> questionsPapersVoList = iQuerySysQuestionsBusiSV.questionsChoicePapers(itemBankPageVo.getBid());
			questionsPapersResponses.setQiestionsParpersVoList(questionsPapersVoList);
			QuestionsPapersResponse questionsPapersResponse = iQuerySysQuestionsBusiSV.questionsPapers(itemBankPageVo.getBid());
			if(questionsPapersResponse.getOriginal() != null && questionsPapersResponse.getQid()!= null){
				questionsPapersResponses.setOriginal(questionsPapersResponse.getOriginal());
				questionsPapersResponses.setQid(questionsPapersResponse.getQid());
			}
		}
		return questionsPapersResponses;
	}
}
