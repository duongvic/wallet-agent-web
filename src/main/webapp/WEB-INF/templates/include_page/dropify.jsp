<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="label.image.upload.default.title" var="upload_image"/>
<spring:message code="label.image.upload.replace.title" var="replace_image"/>
<spring:message code="label.image.upload.remove.title" var="remove_image"/>
<spring:message code="label.image.upload.error.title" var="error_image"/>
<spring:message code="label.image.upload.error.file.size.pre.title" var="file_size_error_pre"/>
<spring:message code="label.image.upload.error.file.size.post.title" var="file_size_error_post"/>
<spring:message code="label.image.upload.error.file.type.pre.title" var="file_type_error_pre"/>
<spring:message code="label.image.upload.error.file.type.post.title" var="file_type_error_post"/>

<script>
  $('.dropify').dropify({
    messages: {
      'a': ""
      + "",
      'default': '${upload_image}',
      'replace': '${replace_image}',
      'remove':  '${remove_image}',
      'error':   '${error_image}'
    },
    error: {
      'fileSize': '${file_size_error_pre} ({{ value }} ${file_size_error_post}).',
      'imageFormat': '${file_type_error_pre} ({{ value }} ${file_type_error_post}).'
    }

  });
</script>