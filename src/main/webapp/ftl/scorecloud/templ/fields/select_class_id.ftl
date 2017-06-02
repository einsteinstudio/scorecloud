<#macro select_class_id selectedVal="">
	<select class="form-control select2me" id="id_label_single" name="${classIdWrapper.name}" >
		<#list classIdWrapper.optGroupVos as gv>
			<optgroup label="${gv.groupName}">
				<#list gv.optionValueDtos as v>
					<option value="${v.value}" <#if  selectedVal==v.value >selected="true"</#if> >${v.descrp}</option>
				</#list>
			</optgroup>
		</#list>
	</select>
</#macro>
