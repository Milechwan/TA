
<%@ page import="ta.Evaluation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'evaluation.label', default: 'Evaluation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-evaluation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-evaluation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list evaluation">
			
				<g:if test="${evaluationInstance?.origin}">
				<li class="fieldcontain">
					<span id="origin-label" class="property-label"><g:message code="evaluation.origin.label" default="Origin" /></span>
					
						<span class="property-value" aria-labelledby="origin-label"><g:fieldValue bean="${evaluationInstance}" field="origin"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluationInstance?.value}">
				<li class="fieldcontain">
					<span id="value-label" class="property-label"><g:message code="evaluation.value.label" default="Value" /></span>
					
						<span class="property-value" aria-labelledby="value-label"><g:fieldValue bean="${evaluationInstance}" field="value"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${evaluationInstance?.applicationDate}">
				<li class="fieldcontain">
					<span id="applicationDate-label" class="property-label"><g:message code="evaluation.applicationDate.label" default="Application Date" /></span>
					
						<span class="property-value" aria-labelledby="applicationDate-label"><g:formatDate date="${evaluationInstance?.applicationDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:evaluationInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${evaluationInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
