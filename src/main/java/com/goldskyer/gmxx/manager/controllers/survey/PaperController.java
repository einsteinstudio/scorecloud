package com.goldskyer.gmxx.manager.controllers.survey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldskyer.core.controllers.CoreBaseController;
import com.goldskyer.core.dao.BaseDao;
import com.goldskyer.core.dto.JsonData;
import com.goldskyer.gmxx.common.dtos.DataTableReqDto;
import com.goldskyer.gmxx.survey.service.PaperService;
@Controller
@RequestMapping("/manager/paper")
public class PaperController extends CoreBaseController {

	@Autowired
	private PaperService paperService;
	@Autowired
	private BaseDao baseDao;
	
	@RequestMapping(value="/list.json",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object list(DataTableReqDto dataTableReqDto,HttpServletRequest request){
		JsonData jsonData = JsonData.success();
		List list= baseDao.query("select p.title,p.theme,p.author,p.createDate,p.updateDate,p.id from Paper p order by p.createDate desc", new HashMap(), dataTableReqDto.getStart(), dataTableReqDto.getLength());
		jsonData.data.put("list", list);
		Map map =new HashMap();
		map.put("data", list);
		map.put("recordsTotal", 1);
		map.put("recordsFiltered", 1);
		map.put("draw", 1);
		return map;
    } 
	
}
