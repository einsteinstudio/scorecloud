<!-- 年级选择 -->
<#macro select_grade_id selectedVal="">
	<select class="form-control bs-select" name="gradeId">
		<#list gradeIdOptionDto.optionValueDtos as vo>
			<option value="${vo.value}"   <#if  selectedVal==vo.value >selected="true"</#if> >${vo.descrp}</option>
		</#list>
	</select>	
</#macro>