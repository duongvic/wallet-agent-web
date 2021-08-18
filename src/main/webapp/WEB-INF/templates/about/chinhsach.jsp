<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="no-js css-menubar" lang="en">

<head>
  <title><spring:message code="label.policy"/> - <spring:message code="common.company"/></title>
  <!-- head libs  -->
  <c:import url="../include_page/head.jsp"/>
  <!-- /head libs  -->
</head>

<body class="animsition">
<!-- notification -->
<jsp:include page="../include_page/notification.jsp"/>
<!-- /notification --

<!-- menu bar -->
<jsp:include page="../include_page/menu_bar.jsp"/>
<!-- /menu bar -->

<div class="page">
  <div class="page-header">
    <ol class="breadcrumb">
      <li class="breadcrumb-item"><a href="/dashboard/index"><spring:message code="dashboard.home"/></a></li>
      <li class="breadcrumb-item active"><spring:message code="label.term"/></li>
    </ol>
    <h1 class="page-title"><spring:message code="label.policy"/></h1>
  </div>
  <div class="page-content container-fluid">
    <div class="row">
      <div class="col-xxl-9 col-lg-9 col-md-8 col-sm-12">
        <div class="panel panel-bordered">
          <div class="panel-body">
            <p>Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis.Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer
              euismod lacus luctus magna.Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta.</p>
            <p>Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis.Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer
              euismod lacus luctus magna.Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta.</p>
            <p>Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis.Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer
              euismod lacus luctus magna.Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta.</p>
            <p>Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis.Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer
              euismod lacus luctus magna.Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta.</p>
            <p>Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis.Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer
              euismod lacus luctus magna.Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta.</p>

          </div>
        </div>
      </div>
      <div class="col-xxl-3 col-lg-3 col-md-4 col-sm-12">
        <div class="panel">
          <div class="carousel slide" id="exampleCarouselDefault" data-ride="carousel">
            <div class="carousel-inner" role="listbox">
              <div class="carousel-item active"><img class="w-full" src="/assets/images/banner.png" alt="..."/></div>
              <div class="carousel-item"><img class="w-full" src="/assets/images/banner.png" alt="..."/></div>
              <div class="carousel-item"><img class="w-full" src="/assets/images/banner.png" alt="..."/></div>
            </div>
            <div class="pull-right">
              <a class="carousel-control-prev carousel-nav-bottom" href="#exampleCarouselDefault" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon wb-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
              </a>
              <a class="carousel-control-next carousel-nav-bottom" href="#exampleCarouselDefault" role="button" data-slide="next">
                <span class="carousel-control-next-icon wb-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
              </a>
            </div>
            <div class="clr"></div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-6 col-md-12">
            <div class="panel panel-bordered">
              <div class="panel-heading">
                <h3 class="panel-title">Câu hỏi thường gặp</h3></div>
              <div class="panel-body py-15 chtg">
                <p class="mb-5"><a href="#">Thời gian xử lý khiếu nại là bao lâu?</a></p>
                <p class="mb-5"><a href="#">Thời gian xử lý khiếu nại là bao lâu?</a></p>
                <p class="mb-5"><a href="#">Thời gian xử lý khiếu nại là bao lâu?</a></p>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-md-12">
            <div class="panel panel-bordered">
              <div class="panel-heading">
                <h3 class="panel-title">Trợ giúp</h3>
                <ul class="panel-actions panel-actions-keep">
                  <li><a href="#">Xem toàn bộ</a></li>
                </ul>
              </div>
              <ul class="list-group list-group-full py-10">
                <li class="list-group-item py-5">
                  <div class="media">
                    <div class="pr-10">
                      <a class="" href="javascript:void(0)"><img class="img-fluid br3" src="/assets/images/placeholder100.png" width="26" alt="..."></a>
                    </div>
                    <div class="media-body">
                      <a href="#">
                        <h5 class="mt-5 mb-5"><spring:message code="account.bank.account"/> <spring:message code="common.company"/></h5></a>
                    </div>
                  </div>
                </li>
                <li class="list-group-item py-5">
                  <div class="media">
                    <div class="pr-10">
                      <a class="" href="javascript:void(0)"><img class="img-fluid br3" src="/assets/images/placeholder100.png" width="26" alt="..."></a>
                    </div>
                    <div class="media-body">
                      <a href="#">
                        <h5 class="mt-5 mb-5">Dịch vụ ví điện tử</h5></a>
                    </div>
                  </div>
                </li>
                <li class="list-group-item py-5">
                  <div class="media">
                    <div class="pr-10">
                      <a class="" href="javascript:void(0)"><img class="img-fluid br3" src="/assets/images/placeholder100.png" width="26" alt="..."></a>
                    </div>
                    <div class="media-body">
                      <a href="#">
                        <h5 class="mt-5 mb-5">Dịch vụ cổng thanh toán</h5></a>
                    </div>
                  </div>
                </li>
                <li class="list-group-item py-5">
                  <div class="media">
                    <div class="pr-10">
                      <a class="" href="javascript:void(0)"><img class="img-fluid br3" src="/assets/images/placeholder100.png" width="26" alt="..."></a>
                    </div>
                    <div class="media-body">
                      <a href="#">
                        <h5 class="mt-5 mb-5">Hướng dẫn mua hàng</h5></a>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- footer -->
<c:import url="../include_page/footer.jsp"/>
<!-- /footer -->
</body>

</html>