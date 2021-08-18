<%@ page import="vn.mog.ewallet.consumer.web.common.SharedConstants" %>
<%@ page import="vn.mog.ewallet.consumer.web.controller.epo.BatchCardsController" %>
<%@ page
        import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.CardType" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.DASHBOARD_TYPE_API" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.dashboard.DashboardController.DASHBOARD_TYPE_EXPORT" %>
<%@ page
        import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.epo.beans.EpinPurchaseOrder.StoreType" %>
<%@ page
        import="static vn.mog.ewallet.consumer.web.controller.AbstractController.DASHBOARD_TYPE_API_N02" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions"
           prefix="ewallet" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>
    <%--batch cards--%>
    <link rel="stylesheet" href="<c:url value="/assets/development/css/epin.css"/>">

    <title><spring:message code="menu.batch.cards"/></title>
    <!-- head libs  -->
    <c:import url="../include_page/head.jsp"/>
    <!-- /head libs  -->

    <c:set var="typeAPI" value="<%=DASHBOARD_TYPE_API%>"/>
    <c:set var="typeExport" value="<%=DASHBOARD_TYPE_EXPORT%>"/>
    <c:set var="typeAPIN02" value="<%=DASHBOARD_TYPE_API_N02%>"/>
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<c:set var="menu_param" value=""/>
<c:set var="page_title" value=""/>
<c:set var="tab_title" value="service.exportcard.title.content"/>
<c:if test="${dashboardType eq typeExport}">
    <c:set var="menu_param" value="batchCardsMenu"/>
    <c:set var="page_title" value="batch.cards.export.po.label"/>
    <c:set var="tab_title" value="service.exportcard.file.title.content"/>
    <c:set var="epoStoreType" value="<%=StoreType.OFFLINE_STORE%>"/>
</c:if>

<c:if test="${dashboardType eq typeAPIN02}">
    <c:set var="menu_param" value="batchCardsMenuAPIN02"/>
    <c:set var="page_title" value="batch.cards.api.po.label"/>
    <c:set var="tab_title" value="service.exportcard.api.title.content"/>
    <c:set var="epoStoreType" value="<%=StoreType.ONLINE_STORE_N02%>"/>
</c:if>

<c:if test="${dashboardType eq typeAPI}">
    <c:set var="menu_param" value="batchCardsMenuAPI"/>
    <c:set var="page_title" value="batch.cards.api.po.label"/>
    <c:set var="tab_title" value="service.exportcard.api.title.content"/>
    <c:set var="epoStoreType" value="<%=StoreType.ONLINE_STORE%>"/>
</c:if>
<jsp:include page="../include_page/menu_bar.jsp">
    <jsp:param name="nav" value="${menu_param}"/>
</jsp:include>
<!-- /menu bar -->

<div class="page">
    <div class="page-header">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message
                    code="dashboard.home"/></a></li>
            <li class="breadcrumb-item active"><spring:message
                    code="label.manage"/></li>
            <li class="breadcrumb-item active"><spring:message
                    code="${page_title}"/></li>
            <li class="breadcrumb-item"><spring:message
                    code="label.send.request"/></li>
        </ol>
        <h1 class="page-title"><spring:message code="${page_title}"/></h1>
    </div>
    <div class="page-content container-fluid">
        <%--<form action="/batch-cards" method="post">--%>
        <div class="panel mb-0 panel-bordered">
            <form id="tblInputTel">
                <div class="panel-body">
                    <h4 class="mb-20"><spring:message code="label.choosen.card"/></h4>

                    <div class="form-group row telco gr-logo">
                        <c:set var="itemPerRow" value="5"/>
                        <c:forEach var="enabledService" items="${enabledServices}">
                        </c:forEach>
                        <c:if test="${enabledServices != null}">
                            <c:forEach var="trueService" items="${enabledServices}"
                                       varStatus="index">
                                <c:if test="${0 == index.index || (0 == index.index % itemPerRow)}">
                                    <div class="col-xs-0 col-sm-1 image-empty">
                                        <label class="lb-image">
                                            <div class="sprite-card-line_none_100_40"></div>
                                            <br/>
                                        </label>
                                    </div>
                                </c:if>

                                <div class="col-xs-6 col-sm-4 col-md-2">
                                    <label for="rd${index.count}" class="lb-image">
                                        <div class="sprite-card-${fn:toLowerCase(trueService.serviceCodeName)}_100_40"></div>
                                        <br/>
                                        <c:choose>
                                            <c:when test="${trueService.serviceCodeName eq 'DATA_MOBIFONE'}">
                                                <input id="rd${index.count}" name="telco"
                                                       value="${CardType.getCardType(trueService.serviceCodeName)}"
                                                       type="radio" class="rd-img mobifone-type"/>
                                            </c:when>
                                            <c:when test="${trueService.serviceCodeName eq 'DATA_VINAPHONE'}">
                                                <input id="rd${index.count}" name="telco"
                                                       value="${CardType.getCardType(trueService.serviceCodeName)}"
                                                       type="radio" class="rd-img vinaphone-type"/>
                                            </c:when>
                                            <c:when test="${trueService.serviceCodeName eq 'DATA_VIETTEL'}">
                                                <input id="rd${index.count}" name="telco"
                                                       value="${CardType.getCardType(trueService.serviceCodeName)}"
                                                       type="radio" class="rd-img viettel-type"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input id="rd${index.count}" name="telco"
                                                       value="${CardType.getCardType(trueService.serviceCodeName)}"
                                                       type="radio" class="rd-img normal-type"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </label>
                                </div>

                                <c:if test="${0 < index.index && (0 == (index.index + 1) % itemPerRow)}">
                                    <div class="col-xs-0 col-sm-1 image-empty">
                                        <label class="lb-image">
                                            <div class="sprite-card-line_none_100_40"></div>
                                            <br/>
                                        </label>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:if>

                    </div>
                    <div class="clearfix"></div>

                    <%--start table--%>
                    <div class="form-group row normal-type-tb tb-facevalue">
                        <spring:message code="label.inventory" var="inventory_label"/>

                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <!-- 10000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">10.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am10"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4  pl-none pr-none secondary_color tc_am10"><spring:message
                                    code="card.label.discount"/></label>


                            <!-- 20000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">20.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am20"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am20"><spring:message
                                    code="card.label.discount"/></label>

                            <!-- 30000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">30.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am30"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am30"><spring:message
                                    code="card.label.discount"/></label>

                            <%--50000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">50.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am50"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am50"><spring:message
                                    code="card.label.discount"/></label>

                            <%--1000000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">1.000.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am1000"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am1000"><spring:message
                                    code="card.label.discount"/></label>
                        </div>

                        <%--100000--%>
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">100.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am100"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am100"><spring:message
                                    code="card.label.discount"/></label>

                            <%--200000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">200.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am200"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label class="col-4 pl-none pr-none  secondary_color tc_am200">
                                <spring:message code="card.label.discount"/></label>

                            <%--300000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">300.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am300"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label class="col-4 pl-none pr-none  secondary_color tc_am300">
                                <spring:message code="card.label.discount"/></label>

                            <%--500000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">500.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am500"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am500"><spring:message
                                    code="card.label.discount"/></label>

                            <%--2000000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">2.000.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am2000"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am2000"><spring:message
                                    code="card.label.discount"/></label>
                        </div>

                        <div class="col-xs-12 col-sm-12">
                            <a href="#" class="btn btn-primary btn-sm mb-xs mt-xs add-tel-po"><i
                                    class="fa fa-plus"></i>&nbsp;<spring:message
                                    code="service.exportcard.create.card.add"/></a>
                        </div>
                    </div>
                    <%--end table--%>

                    <%--start table--%>
                    <div class="form-group row mobifone-type-tb tb-facevalue hidden">
                        <spring:message code="label.inventory" var="inventory_label"/>

                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <!-- 10.000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">10.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am10"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4  pl-none pr-none secondary_color tc_am10"><spring:message
                                    code="card.label.discount"/></label>


                            <!-- 20.000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">20.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am20"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am20"><spring:message
                                    code="card.label.discount"/></label>

                            <!-- 50.000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">50.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am50"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am50"><spring:message
                                    code="card.label.discount"/></label>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <%--200.000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">200.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am200"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am200"><spring:message
                                    code="card.label.discount"/></label>

                            <%--500.000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">500.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am500"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am500"><spring:message
                                    code="card.label.discount"/></label>

                        </div>
                        <%-- Add button --%>
                        <div class="col-xs-12 col-sm-12">
                            <a href="#" class="btn btn-primary btn-sm mb-xs mt-xs add-tel-po"><i
                                    class="fa fa-plus"></i>&nbsp;<spring:message
                                    code="service.exportcard.create.card.add"/></a>
                        </div>
                    </div>
                    <%--end table--%>

                    <%--start table--%>
                    <div class="form-group row vinaphone-type-tb tb-facevalue hidden">
                        <spring:message code="label.inventory" var="inventory_label"/>

                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <!-- 20.000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">20.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am20"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am20"><spring:message
                                    code="card.label.discount"/></label>

                            <!-- 50.000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">50.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am50"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am50"><spring:message
                                    code="card.label.discount"/></label>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <%--100.000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">100.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am100"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am100"><spring:message
                                    code="card.label.discount"/></label>

                        </div>
                        <%-- Add button --%>
                        <div class="col-xs-12 col-sm-12">
                            <a href="#" class="btn btn-primary btn-sm mb-xs mt-xs add-tel-po"><i
                                    class="fa fa-plus"></i>&nbsp;<spring:message
                                    code="service.exportcard.create.card.add"/></a>
                        </div>
                    </div>
                    <%--end table--%>

                    <%--start table--%>
                    <div class="form-group row viettel-type-tb tb-facevalue hidden">
                        <spring:message code="label.inventory" var="inventory_label"/>

                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <!-- 40.000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">40.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am40"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am40"><spring:message
                                    code="card.label.discount"/></label>

                            <!-- 90.000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">90.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am90"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am90"><spring:message
                                    code="card.label.discount"/></label>

                            <%--200.000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">200.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am200"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am100"><spring:message
                                    code="card.label.discount"/></label>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <%--70.000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">70.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am70"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am70"><spring:message
                                    code="card.label.discount"/></label>

                            <%--125.000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">125.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am125"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am125"><spring:message
                                    code="card.label.discount"/></label>

                        </div>
                        <%-- Add button --%>
                        <div class="col-xs-12 col-sm-12">
                            <a href="#" class="btn btn-primary btn-sm mb-xs mt-xs add-tel-po"><i
                                    class="fa fa-plus"></i>&nbsp;<spring:message
                                    code="service.exportcard.create.card.add"/></a>
                        </div>
                    </div>
                    <%--end table--%>

                    <%--start table--%>
                    <div class="form-group row scoin-type-tb tb-facevalue hidden">
                        <spring:message code="label.inventory" var="inventory_label"/>

                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <!-- 10000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">10.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am10"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4  pl-none pr-none secondary_color tc_am10 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <!-- 50000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">50.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am50"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am50 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <!-- 200000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">200.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am200"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am200 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <%--1000000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">1.000.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am1000"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am1000 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <%--5000000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">5.000.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am5000"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am5000 hidden"><spring:message
                                    code="card.label.discount"/></label>
                        </div>

                        <%--20000--%>
                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">20.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am20"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am20 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <%--100000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">100.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am100"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label class="col-4 pl-none pr-none  secondary_color tc_am100 hidden">
                                <spring:message code="card.label.discount"/></label>

                            <%--500000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">500.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am500"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label class="col-4 pl-none pr-none  secondary_color tc_am300 hidden">
                                <spring:message code="card.label.discount"/></label>

                            <%--2000000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">2.000.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am2000"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am2000 hidden"><spring:message
                                    code="card.label.discount"/></label>

                        </div>

                        <div class="col-xs-12 col-sm-12">
                            <a href="#" class="btn btn-primary btn-sm mb-xs mt-xs add-tel-po"><i
                                    class="fa fa-plus"></i>&nbsp;<spring:message
                                    code="service.exportcard.create.card.add"/></a>
                        </div>
                    </div>
                    <%--end table--%>

                    <%--start table--%>
                    <div class="form-group row soha-type-tb tb-facevalue hidden">
                        <spring:message code="label.inventory" var="inventory_label"/>

                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <!-- 10000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">10.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am10"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4  pl-none pr-none secondary_color tc_am10 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <!-- 50000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">50.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am50"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am50 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <!-- 200000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">200.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am200"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am200 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <%--500000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">500.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am500"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am500 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <%--2000000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">2.000.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am2000"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am2000 hidden"><spring:message
                                    code="card.label.discount"/></label>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <%--20000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">20.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am20"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am20 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <%--100000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">100.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am100"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label class="col-4 pl-none pr-none  secondary_color tc_am100 hidden">
                                <spring:message code="card.label.discount"/></label>

                            <%--300000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">300.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am300"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label class="col-4 pl-none pr-none  secondary_color tc_am300 hidden">
                                <spring:message code="card.label.discount"/></label>
                            <%--1000000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">1.000.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am1000"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am1000 hidden"><spring:message
                                    code="card.label.discount"/></label>
                            <%--5000000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">5.000.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am5000"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am5000 hidden"><spring:message
                                    code="card.label.discount"/></label>

                        </div>

                        <div class="col-xs-12 col-sm-12">
                            <a href="#" class="btn btn-primary btn-sm mb-xs mt-xs add-tel-po"><i
                                    class="fa fa-plus"></i>&nbsp;<spring:message
                                    code="service.exportcard.create.card.add"/></a>
                        </div>
                    </div>
                    <%--end table--%>

                    <%--start table--%>
                    <div class="form-group row funcard-type-tb tb-facevalue hidden">
                        <spring:message code="label.inventory" var="inventory_label"/>

                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <!-- 10000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">10.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am10"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4  pl-none pr-none secondary_color tc_am10 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <!-- 50000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">50.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am50"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am50 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <!-- 200000 -->
                            <label class="col-4"><spring:message code="label.price"/>:<span
                                    class="fr">200.000 đ</span>
                            </label>
                            <div class="col-4"><input type="text" name="tc_am200"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none secondary_color tc_am200 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <%--1000000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">1.000.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am1000"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am1000 hidden"><spring:message
                                    code="card.label.discount"/></label>
                        </div>

                        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 form-group row textTelco mt-20">
                            <%--20000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">20.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am20"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am20 hidden"><spring:message
                                    code="card.label.discount"/></label>

                            <%--100000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">100.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am100"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label class="col-4 pl-none pr-none  secondary_color tc_am100 hidden">
                                <spring:message code="card.label.discount"/></label>

                            <%--500000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">500.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am500"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label class="col-4 pl-none pr-none  secondary_color tc_am500 hidden">
                                <spring:message code="card.label.discount"/></label>
                            <%--2000000--%>
                            <label class="col-4 "><spring:message code="label.price"/>:<span
                                    class="fr">2.000.000 đ</span> </label>
                            <div class="col-4"><input type="text" name="tc_am2000"
                                                      placeholder="${inventory_label}"
                                                      autocomplete="off"
                                                      class="form-control po-line inputtelco"></div>
                            <label
                                    class="col-4 pl-none pr-none  secondary_color tc_am2000 hidden"><spring:message
                                    code="card.label.discount"/></label>

                        </div>

                        <div class="col-xs-12 col-sm-12">
                            <a href="#" class="btn btn-primary btn-sm mb-xs mt-xs add-tel-po"><i
                                    class="fa fa-plus"></i>&nbsp;<spring:message
                                    code="service.exportcard.create.card.add"/></a>
                        </div>
                    </div>
                    <%--end table--%>
                </div>
                <%--<input type="hidden" name="${_csrf.parameterName}"--%>
                <%--value="${_csrf.token}"/>--%>
                <div class="line-horizontal"></div>
            </form>

            <c:url var="epinPoConUri" value="<%=BatchCardsController.BATCH_CARDS_CONTROLLER%>"/>
            <form name="telco" action="request-po" method="post">
                <div class="panel-body tb-tool">
                    <input type="hidden" name="totalTelco" id="totalTelco" value="${totalTelco}"/>
                    <input type="hidden" name="poCode" value=""/>
                    <input type="hidden" name="totalQuantity" value=""/>
                    <input type="hidden" name="totalMoney" value=""/>

                    <section class="panel panel-default po-detail-line" style="box-shadow: none">
                        <%-- b-pocode,Mã-PO code --%>
                        <div class="pl-xs" style="font-size: 13px;">
                            <%--<b class="b-pocode" hidden></b>--%>
                            <b class="b-pocode hidden"></b>
                            <spring:message code="service.exportcard.create.table.header.total"/>&nbsp;<b
                                class="b-money primary_color">0</b> <b class="primary_color">VND</b>&nbsp;|&nbsp;
                            <spring:message code="service.exportcard.create.table.header.quantity"/>&nbsp;<b
                                class="b-quantity primary_color">0</b>
                        </div>

                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-bordered table-striped mb-none"
                                       id="tableMPOdetail">
                                    <thead>
                                    <tr>
                                        <th><spring:message
                                                code="service.exportcard.create.table.column.no"/></th>
                                        <th style="width:20%"><spring:message
                                                code="service.exportcard.create.table.column.cardtype"/></th>
                                        <th class="text-right" style="width: 20%"><spring:message
                                                code="service.exportcard.create.table.column.facevalue"/></th>
                                        <th class="text-right" style="width: 20%"><spring:message
                                                code="service.exportcard.create.table.column.quantity"/></th>
                                        <%--<th class="text-center"><spring:message--%>
                                        <%--code="service.exportcard.create.table.column.status"/></th>--%>
                                        <th class="text-center"><spring:message
                                                code="service.exportcard.create.table.column.total.amount"/></th>
                                        <th class="text-center"><spring:message
                                                code="service.exportcard.create.table.column.action"/></th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <spring:message code="service.exportcard.create.card.available"
                                                    var="langAvailable"/>
                                    <c:forEach var="item" items="${purchaseOrderDetails }"
                                               varStatus="varStatus">
                                        <tr class="${item.cardType }_${item.faceValue }">
                                            <input type="hidden" name="telco_${varStatus.index+1}"
                                                   value="${item.cardType }"/>
                                            <input type="hidden" name="value_${varStatus.index+1}"
                                                   value="${item.faceValue }"/>
                                            <input type="hidden"
                                                   name="quantity_${varStatus.index+1}"
                                                   value="${item.quantity }"/>
                                            <input type="hidden" name="amount_${varStatus.index+1}"
                                                   value="${item.amount }"/>

                                            <input type="text" class="hidden"
                                                   name="status_${varStatus.index+1}"
                                                   value="${item.status }"/>

                                            <td class="stt">${varStatus.index+1}</td>
                                            <td>${item.cardType }</td>
                                            <td class="text-right">${ewallet:formatNumber(item.faceValue)}</td>
                                            <td class="text-right">${ewallet:formatNumber(item.quantity)}</td>
                                            <td class="text-right">${ewallet:formatNumber(item.faceValue * item.quantity)}</td>
                                                <%--<td class="text-center ${item.status eq langAvailable ? 'primary_color' : 'secondary_color' }">${item.status }</td>--%>
                                            <td class="action_icon text-center">
                                                <a href="javascript:;" style="color: red;"><i
                                                        class="fa fa-times"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </section>
                    <div class="row content-body-wrap">
                        <div class="col-md-12 col-lg-12 col-xl-12 text-right">
                            <div class="panel-body edit_profile">
                                <spring:message code="service.exportcard.otp.waiting"
                                                var="waiting"/>
                                <button type="submit" class="mt-md btn btn-success fr po-next-step"
                                        id="btnNextStep"
                                        data-loading-text="<i class='fa fa-spinner fa-spin '></i> ${waiting}">
                                    <spring:message code="service.exportcard.create.btn.next"/>&nbsp;<i
                                        class="fa fa-arrow-right"></i></button>
                            </div>
                        </div>
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>


        </div>

        <%--</form>--%>
    </div>
</div>
<spring:message code="select.choose.all" var="choose_all"/>
<spring:message code="select.choose.forder.process" var="choose_process"/>
<spring:message code="select.status" var="choose_status"/>

<spring:message code="data.table.header.paging.showing" var="paging_showing"/>
<spring:message code="data.table.header.paging.to" var="paging_to"/>
<spring:message code="data.table.header.paging.of" var="paging_of"/>
<spring:message code="data.table.header.paging.entries" var="paging_entries"/>
<spring:message code="data.table.header.paging.previous" var="paging_previous"/>
<spring:message code="data.table.header.paging.next" var="paging_next"/>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
<c:import url="../include_page/js_daterangepicker.jsp"/>

</body>
<script type="text/javascript">
  <%--$(document).ready(function () {--%>
  <%--var t = $('#tableMPOdetail').DataTable({--%>
  <%--responsive: true,--%>
  <%--"searching": false,--%>
  <%--"bLengthChange": false, // không show row--%>
  <%--"order": [[1, 'desc']],--%>
  <%--"paginate": false,--%>
  <%--"sInfo": false--%>
  <%--&lt;%&ndash;"language": {&ndash;%&gt;--%>
  <%--&lt;%&ndash;"sInfo": "${paging_showing} _START_ ${paging_to} _END_ ${paging_of} _TOTAL_ ${paging_entries}",&ndash;%&gt;--%>
  <%--&lt;%&ndash;"paginate": {&ndash;%&gt;--%>
  <%--&lt;%&ndash;"previous": "${paging_previous}",&ndash;%&gt;--%>
  <%--&lt;%&ndash;"next": "${paging_next}"&ndash;%&gt;--%>
  <%--&lt;%&ndash;}&ndash;%&gt;--%>
  <%--&lt;%&ndash;}&ndash;%&gt;--%>
  <%--});--%>
  <%--new $.fn.dataTable.FixedHeader(t);--%>

  <%--});--%>

  /*add-tel-po*/
  function formatNumberSeparator(x, locale) {
    var locale = "vi";
    var separator = ",";
    if (locale == "vi") {
      separator = ".";
    }
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, separator);
  }

  function getValueFromName(name) {
    if (name == 'tc_am10') return '10000';
    else if (name == 'tc_am20') return '20000';
    else if (name == 'tc_am30') return '30000';
    else if (name == 'tc_am50') return '50000';
    else if (name == 'tc_am100') return '100000';
    else if (name == 'tc_am200') return '200000';
    else if (name == 'tc_am300') return '300000';
    else if (name == 'tc_am500') return '500000';
    else if (name == 'tc_am1000') return '1000000';
    else if (name == 'tc_am2000') return '2000000';
    else if (name == 'tc_am5000') return '5000000';

    else if (name == 'tc_am1') return '1000';
    else if (name == 'tc_am2') return '2000';
    else if (name == 'tc_am3') return '3000';
    else if (name == 'tc_am5') return '5000';
    else if (name == 'tc_am14') return '14000';
    else if (name == 'tc_am28') return '28000';
    else if (name == 'tc_am40') return '40000';
    else if (name == 'tc_am42') return '42000';
    else if (name == 'tc_am56') return '56000';
    else if (name == 'tc_am84') return '84000';
    else if (name == 'tc_am70') return '70000';
    else if (name == 'tc_am90') return '90000';
    else if (name == 'tc_am120') return '120000';
    else if (name == 'tc_am125') return '125000';
    else if (name == 'tc_am150') return '150000';

  }

  function getNameFromValue(value) {
    if (value == '10000') return 'tc_am10';
    else if (value == '20000') return 'tc_am20';
    else if (value == '30000') return 'tc_am30';
    else if (value == '50000') return 'tc_am50';
    else if (value == '100000') return 'tc_am100';
    else if (value == '200000') return 'tc_am200';
    else if (value == '300000') return 'tc_am300';
    else if (value == '500000') return 'tc_am500';
    else if (value == '1000000') return 'tc_am1000';
    else if (value == '2000000') return 'tc_am2000';
    else if (value == '5000000') return 'tc_am5000';

    else if (value == '1000') return 'tc_am1';
    else if (value == '2000') return 'tc_am2';
    else if (value == '3000') return 'tc_am3';
    else if (value == '5000') return 'tc_am5';
    else if (value == '14000') return 'tc_am14';
    else if (value == '28000') return 'tc_am28';
    else if (value == '40000') return 'tc_am40';
    else if (value == '42000') return 'tc_am42';
    else if (value == '56000') return 'tc_am56';
    else if (value == '84000') return 'tc_am84';
    else if (value == '70000') return 'tc_am70';
    else if (value == '90000') return 'tc_am90';
    else if (value == '120000') return 'tc_am120';
    else if (value == '125000') return 'tc_am125';
    else if (value == '150000') return 'tc_am150';
  }

  $(document).ready(function () {

    var rowCount = $('#tableMPOdetail tbody tr').length;
    if (rowCount < 1) {
      $('#btnNextStep').prop('disabled', true);
    } else {
      $('#btnNextStep').prop('disabled', false);
    }

    $('.add-tel-po').click(function () {
      if ($('#tblInputTel').valid()) {
        if ($('.telco input[name=telco]:checked').length > 0) {
          var telco = $('.telco input[name=telco]:checked').val();
          // $('tr.' + telco).remove();
          // renameTable();
          var i = $('.po-detail-line table tbody tr:last td:first').text();
          if (i == null || i == '') i = 0;
          var poLine = "";
          var ac = '<td class="action_icon text-center">'
              + '<a href="javascript:;" style="\n'
              + '    color: red;\n'
              + '"><i class="fa fa-times"></i></a>' + '</td>';
          var totalQuantity = 0;
          $('.inputtelco').each(function (index, value) {
            var quantity = $(this).val().trim();
            var value = getValueFromName($(this).attr('name'));
            var amount = parseInt(quantity) * parseInt(value);
            if (quantity != '0' && quantity != '') {
              $('tr.' + telco + '_' + value).remove();
              totalQuantity += parseInt(quantity);
              i++;
              var status = $("." + $(this).attr('name')).html();
              poLine += '<tr class="' + telco + '_' + value + '">';
              poLine += '<td class="stt">' + i + '</td>' +
                  '<td>' + telco + '<input type="hidden" name="telco_' + i + '" value="' + telco
                  + '" /></td>' +
                  '<td class="text-right">' + formatNumberSeparator(value, "${locale}")
                  + '<input type="hidden" name="value_' + i + '" value="' + value + '" /></td>'
                  + '<td class="text-right">' + (quantity)
                  + '<input type="hidden" name="quantity_' + i + '" value="' + quantity
                  + '" /></td>'
                  + '<td class="text-right">' + formatNumberSeparator(amount, "${locale}")
                  + '<input type="hidden" name="amount_' + i + '" value="' + amount + '" /></td>'
                  // + '<td class="text-center ' + $("." + $(this).attr('name')).attr('class') + '">'
                  // + status + '<input type="text" name="status_' + i + '" value="' + status
                  // + '" /></td>'
                  + ac;
              poLine += '</tr>';
              $('#totalTelco').val(i);

              if (poLine < 1) {
                $('#btnNextStep').attr('disabled', true);
              } else {
                $('#btnNextStep').attr('disabled', false);
              }
            }
            renameTable();

          });
          var curQuantity = parseInt($('input[name=totalQuantity]').val());
          var nameUser = '${userLogin.username}';
          var maxQuantity = 0;

          /*comment nhập tối đa số lượng thẻ*/
          <%--if (nameUser == 'Zo-ta_TMN') {--%>
          <%--maxQuantity = 30000;--%>
          <%--} else {--%>
          <%--maxQuantity = <%=SharedConstants.QUANTITY_MPO_MAX_TMN%>;--%>
          <%--}--%>

          <%--if (totalQuantity + curQuantity > maxQuantity) {--%>
          <%--$.MessageBox({message: 'Cho phép xuất tối đa ' + maxQuantity + ' thẻ mỗi lần!'});--%>
          <%--return false;--%>
          <%--} else {--%>
          <%--$('.po-detail-line table tbody').append(poLine);--%>
          <%--}--%>
          /*end comment*/

          $('.po-detail-line table tbody').append(poLine);

          var istt = renameTable();
          $('form[name=telco] input[name=totalTelco]').val(istt);

        } else {
          $.MessageBox({message: '<spring:message code="popup.message.warning.card.choose"/>'});
        }
        return false;
      }
    });
    $(document).on('click', 'td.action_icon a', function () {
      $(this).closest('tr').remove();
      $('form[name=telco] input[name=totalTelco]').val(renameTable());
    });
    //truong hop back
    renameTable();

    function renameTable() {
      var istt = 0;
      var totalQuatity = 0;
      var totalMoney = 0;
      $('.po-detail-line table tbody tr').each(function (index, value) {
        istt = index + 1;
        $(value).find('td.stt').html(istt);
        $(value).find('input[name*=value_]').attr('name', 'value_' + istt);
        $(value).find('input[name*=telco_]').attr('name', 'telco_' + istt);
        $(value).find('input[name*=quantity_]').attr('name', 'quantity_' + istt);
        $(value).find('input[name*=status_]').attr('name', 'status_' + istt);
        var quantity = parseInt($(value).find('input[name*=quantity_]').val());
        var money = parseInt($(value).find('input[name*=value_]').val()) * quantity;
        totalQuatity += parseInt($(value).find('input[name*=quantity_]').val());
        totalMoney += money;
      });
      $("b.b-quantity").html(formatNumberSeparator(totalQuatity, "${locale}"));
      $('input[name=totalQuantity]').val(totalQuatity);
      $("b.b-money").html(formatNumberSeparator(totalMoney, "${locale}"));
      var d = new Date();
      var month = d.getMonth() + 1;
      var textMoney = totalMoney / 1000;
      var pocode = "" + d.getFullYear() + month + d.getDate()
          + d.getHours() + d.getMinutes() + '-' + totalQuatity + '-' + textMoney;
      $("b.b-pocode").html(pocode);
      $("input[name=poCode]").val(pocode);
      $("input[name=totalMoney]").val(totalMoney);

      if (istt < 1) {
        $('#btnNextStep').attr('disabled', true);
      } else {
        $('#btnNextStep').attr('disabled', false);
      }
      return istt;
    }

    $('input[name=telco]').click(function () {
      $("#mloading").modal('toggle');
      $('form#tblInputTel input[name*=tc_am]').prop('disabled', false);
//      $('form#tblInputTel label[class*=tc_am]').removeClass('primary_color');
//      $('form#tblInputTel label[class*=tc_am]').addClass('secondary_color');
      //$('form#tblInputTel label[class*=tc_am]').html('(Not available)');
      <%--$('form#tblInputTel label[class*=tc_am]').html('<spring:message code="card.label.discount"/>');--%>

      $("div.textTelco input").val('');
      var telco = $(this).val();
      var thisInput = $(this);

      $.ajax({
        type: "GET",
        contentType: "application/json;charset=utf-8",
        url: "/ajax-controller/get-card-commission",
        data: {
          serviceTypeId: 'epo_card',
          cardName: telco,
          phoneNumber: null
        },
        dataType: 'json',
        timeout: 60000,
        success: function (data) {
          console.log(data);
          var commissionFee = data.commissionFee;
          if (commissionFee != null) {
            $.each(commissionFee, function (key, value) {
              var nameClass = getNameFromValue(key);
              var lable = $('form#tblInputTel label[class*=' + nameClass + ']');
              lable.html('<spring:message code="card.label.discount"/>' + value.commision);
            });
          }
        },
        error: function (e) {
          $('form#tblInputTel label[class*=tc_am]').html(
              '<spring:message code="card.label.discount"/>')
        }
      });

      $.get('getAvailabelCard', {cardtype: telco}, function (data) {

        var cardAvailable = data;

        var valArray = [10000, 20000, 30000, 50000, 100000, 200000, 300000, 500000, 1000000,
          2000000, 5000000, 1000, 2000, 3000, 5000, 14000, 28000, 40000, 42000, 56000, 84000,
          70000, 90000, 120000, 125000, 150000];
//        hidden all faceValue input text tabel.
        var tbFaceValues = $('.tb-facevalue');
        tbFaceValues.addClass("hidden");
//        show faceValue input text tabel folow option.
        var typeFaceValueArr = ['normal-type', 'mobifone-type', 'vinaphone-type', 'viettel-type',
          'scoin-type', 'soha-type', 'funcard-type'];
        for (var k = 0; k < typeFaceValueArr.length; k++) {
          if (thisInput.hasClass(typeFaceValueArr[k])) {
            $('div.' + typeFaceValueArr[k] + '-tb').removeClass('hidden');
          }
        }
//          disabled all faceValue input text.
        for (var j = 0; j < valArray.length; ++j) {
          var input = $('form#tblInputTel input[name=' + getNameFromValue(valArray[j]) + ']');
          input.attr('placeholder', "${inventory_label}");
//          input.prop('disabled', true);
//          input.addClass('disabled-color');
//          input.removeClass('enabled-color');
        }

        if (cardAvailable != null) {
          $.each(cardAvailable, function (inx, val) {
            var name = getNameFromValue(inx);
            if (val > 0) {
              var input = $('form#tblInputTel input[name=' + name + ']');
//              input.prop('disabled', false);
              input.attr('placeholder', "${inventory_label} " + val);

//              input.removeClass('disabled-color');
//              input.addClass('enabled-color');
              <%--$('form#tblInputTel label.' + name).html(--%>
              <%--'<spring:message code="popup.message.warning.bank.available"/>');--%>
            }
            else {
//              $('form#tblInputTel input[name=' + name + ']').prop('disabled', true);
            }
          });
        }
        // $("#mloading").modal('toggle');
      });

      // $('tr.' + telco).each(function (index, value) {
      //   var faceValue = "";
      //   var quantity = "";
      //   $(value).find('input[type=hidden]').each(function (index1, value1) {
      //     if ($(value1).attr("name").includes("value_"))
      //       faceValue = $(value1).val();
      //     if ($(value1).attr("name").includes("quantity_"))
      //       quantity = $(value1).val();
      //   });
      //   var nameInput = getNameFromValue(faceValue);
      //   $('input[name=' + nameInput + ']').val(quantity);
      // });
    });

    <%--$('form[name="telco"]').submit(function () {--%>
    <%--var rowCount = $('#tableMPOdetail tr').length;--%>
    <%--if (rowCount < 2) {--%>
    <%--$.MessageBox(--%>
    <%--{message: '<spring:message code="popup.message.warning.card.choose.quantity"/>'});--%>
    <%--return false;--%>
    <%--}--%>
    <%--$('#btnNextStep').button('loading');--%>
    <%--});--%>

    $.validator.addMethod("inputdigits", $.validator.methods.digits,
        "Hãy nhập số nguyên (Không nhập các ký tự đặc biệt (\<\>\?\.\:\"\(\),cách trống).)");
    $.validator.addClassRules("inputtelco", {inputdigits: true});

    $("div.report_search_form span.fr").each(function () {
      val = $(this).html();
      $(this).html(formatNumberSeparator(val, "${locale}"));
    });
  });
</script>
</html>