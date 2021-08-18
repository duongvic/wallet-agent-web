<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, minimal-ui">
<meta name="description" content="bootstrap admin template">
<meta name="author" content="">

<link rel="shortcut icon" href="${urls.getForLookupPath('/static/images/favicon_zota.png')}">

<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/bootstrap.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/bootstrap-extend.min.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/site.min.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/animsition.css')}">
<link rel="stylesheet" href="<c:url value="/assets/css/fonts.css" />">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/dataTables.bootstrap4.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/dataTables.fixedheader.bootstrap4.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/dataTables.fixedcolumns.bootstrap4.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/dataTables.rowgroup.bootstrap4.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/dataTables.scroller.bootstrap4.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/dataTables.select.bootstrap4.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/dataTables.responsive.bootstrap4.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/dataTables.buttons.bootstrap4.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/datatable.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/custom.css')}">
<%--upload image--%>
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/jquery.fileupload.css')}" media="none" onload="if(media!='all')media='all'">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/dropify.css')}" media="none" onload="if(media!='all')media='all'">
<%--end upload image--%>
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/bootstrap-datepicker.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/select2.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/bootstrap-tokenfield.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/bootstrap-tagsinput.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/bootstrap-select.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/multi-select.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/advanced.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/webui-popover.css')}">
<link rel="stylesheet" href="${urls.getForLookupPath('/static/css/toolbar.css')}">

<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:300,400,500,300italic'>


<link rel="stylesheet" href="<c:url value="/assets/development/css/signup.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/development/css/input_group.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/development/css/reponsive.css"/>">
<%--<link rel="stylesheet" href="<c:url value="/assets/css/custom.css"/>">--%>
<%--<link rel="stylesheet" href="<c:url value="/assets/development/css/my_custom.css"/>">--%>

<link rel="stylesheet" href="<c:url value="/assets/development/css/multiselect.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/development/css/button.css"/>">

<link rel="stylesheet" href="<c:url value="/assets/development/css/table_child.css"/>">
<%--thẻ điên thoại--%>
<link rel="stylesheet" href="<c:url value="/assets/development/css/card.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/development/css/item_card.css"/>">

<link rel="stylesheet" href="<c:url value="/assets/css/messagebox_3.2.1.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/development/css/printer.css"/>">

<c:if test="${'true' eq param.import_epin}">
    <link rel="stylesheet" href="<c:url value="/assets/development/css/epin.css"/>">
</c:if>

<c:if test="${'true' eq param.import_nav_tabs}">
    <link rel="stylesheet" href="<c:url value="/assets/development/css/nav-tabs.css"/>">
</c:if>



<script type="application/javascript" src="${urls.getForLookupPath('/static/js/breakpoints.js')}"></script>

<script>
  Breakpoints();
</script>