<%@ page import="ta.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="student.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${studentInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'login', 'error')} required">
	<label for="login">
		<g:message code="student.login.label" default="Login" />
		<span class="required-indicator">*</span>
	</label>

</div>

