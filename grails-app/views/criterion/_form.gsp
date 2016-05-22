<%@ page import="ta.Criterion" %>



<div class="fieldcontain ${hasErrors(bean: criterionInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="criterion.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${criterionInstance?.name}"/>

</div>

