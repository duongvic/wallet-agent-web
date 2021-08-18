<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="https://admin.bizdev.zo-ta.com/service/jsp/jstl/functions" prefix="ewallet" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="panel">
  <div class="panel">
    <div class="p-20 text-center ">
      <input type="file" id="id-dropify-img" name="imgProfile" data-plugin="dropify" data-default-file="" disabled></div>
    <table class="table no-border fs13">
      <tbody>
      <tr>
        <td><spring:message code="wallet.balance.totalBalance"/></td>
        <td class="text-right vnd">${ewallet:formatNumber(userLogin.balance)}</td>
      </tr>
      <tr>
        <td><spring:message code="label.current.balance"/> :</td>
        <td class="text-right vnd">${ewallet:formatNumber(userLogin.balance)}</td>
      </tr>
      <tr>
        <td>Số dư đóng băng:</td>
        <td class="text-right vnd">10.000</td>
      </tr>
      </tbody>
    </table>
  </div>
</div>