package com.goldskyer.gmxx.common.helpers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.common.dtos.DataTablesRespDto;

public class DataTableHelper
{

	public static DataTablesRespDto execute(DataTableReqDto reqDto, BaseDao baseDao)
	{
		DataTablesRespDto respDto = new DataTablesRespDto();
		String countSql = reqDto.getSql()
				+ (StringUtils.isNotBlank(reqDto.getSearchKey())
						? " and concat(" + reqDto.getSearchField() + ") like '%" + reqDto.getSearchKey() + "%'" : "");
		countSql += " " + reqDto.getOrderBy();
		long count = baseDao.count(countSql, reqDto.getParaMap());
		respDto.setRecordsTotal(count);
		respDto.setRecordsFiltered(count);
		List data = baseDao.query(countSql, reqDto.getParaMap(), reqDto.getStart(), reqDto.getLength());
		respDto.setData(data);
		return respDto;
	}

	public static DataTablesRespDto execute(DataTableReqDto reqDto, BaseDao baseDao, VoConverter converter)
	{
		DataTablesRespDto respDto = new DataTablesRespDto();
		String countSql = reqDto.getSql() + (StringUtils.isNotBlank(reqDto.getSearchKey())
				? " and concat(" + reqDto.getSearchField() + ") like '%" + reqDto.getSearchKey() + "%'" : "");
		countSql += " " + reqDto.getOrderBy();
		long count = baseDao.count(countSql, reqDto.getParaMap());
		respDto.setRecordsTotal(count);
		respDto.setRecordsFiltered(count);
		List data = baseDao.query(countSql, reqDto.getParaMap());
		List realData=new ArrayList<>();
		for(Object o:data)
		{
			realData.add(converter.convert((Object[]) o));
		}
		respDto.setData(data);
		return respDto;
	}
}
