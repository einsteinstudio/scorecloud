<!-- 年级名字选择 -->
<#macro select_grade_name selectedVal="">
	<select class="form-control bs-select" name="gradeName">
		<#list gradeNameOptionDto.optionValueDtos as vo>
			<option value="${vo.value}"   <#if  selectedVal==vo.value >selected="true"</#if> >${vo.descrp}</option>
		</#list>
	</select>	
</#macro>