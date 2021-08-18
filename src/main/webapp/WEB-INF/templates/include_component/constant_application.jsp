<%@ page import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.FundOrderChannelType" %>
<%@ page import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.FundOrderFlowStageType" %>
<%@ page import="vn.mog.ewallet.consumer.web.thirdparty.system.integration.apigateway.contract.system.type.WalletTransferOrderFlowStage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="BANK_TRANSFER" value="<%=FundOrderChannelType.BANK_TRANSFER.code%>" scope="application"/>
<c:set var="CASH_ON_HAND" value="<%=FundOrderChannelType.CASH_ON_HAND.code%>" scope="application"/>
<c:set var="LINK_BANK" value="<%=FundOrderChannelType.LINK_BANK.code%>" scope="application"/>

<!-- Fund Order -->
<c:set var="fundOrderInit" value="<%=FundOrderFlowStageType.FUND_ORDER_INIT.code%>" scope="application"/>
<c:set var="saleExcutiveReject" value="<%=FundOrderFlowStageType.SALE_EXCUTIVE_REJECTED.code%>" scope="application"/>
<c:set var="saleExcutiveVerify" value="<%=FundOrderFlowStageType.SALE_EXCUTIVE_VERIFY.code%>" scope="application"/>


<c:set var="financeSupportReject" value="<%=FundOrderFlowStageType.FINANCE_SUPPORT_REJECTED.code%>" scope="application"/>
<c:set var="financeSupportVerify" value="<%=FundOrderFlowStageType.FINANCE_SUPPORT_VERIFY.code%>" scope="application"/>

<c:set var="financeManagerRejected" value="<%=FundOrderFlowStageType.FINANCE_MANAGER_REJECTED.code%>" scope="application"/>
<c:set var="financeMamagerApprove" value="<%=FundOrderFlowStageType.FINANCE_MAMAGER_APPROVE.code%>" scope="application"/>

<c:set var="fundOrderFinished" value="<%=FundOrderFlowStageType.FUND_ORDER_FINISHED.code%>" scope="application"/>


<!-- wallet transfer -->
<c:set var="walletTransferInit" value="<%=WalletTransferOrderFlowStage.OPERATION_INIT%>" scope="application"/>

<c:set var="financeRejected" value="<%=WalletTransferOrderFlowStage.FINANCE_REJECTED%>" scope="application"/>
<c:set var="financeReadyToVerified" value="<%=WalletTransferOrderFlowStage.FINANCE_READY_TO_VERIFIED%>" scope="application"/>

<c:set var="financeLeaderRejected" value="<%=WalletTransferOrderFlowStage.FINANCE_LEADER_REJECTED%>" scope="application"/>
<c:set var="financeLeaderReadyToReviewed" value="<%=WalletTransferOrderFlowStage.FINANCE_LEADER_READY_TO_REVIEWED%>" scope="application"/>

<c:set var="financeManagerRejected" value="<%=WalletTransferOrderFlowStage.FINANCE_MANAGER_REJECTED%>" scope="application"/>
<c:set var="financeManagerReadyToApproved" value="<%=WalletTransferOrderFlowStage.FINANCE_MANAGER_READY_TO_APPROVED%>" scope="application"/>

<c:set var="walletTransferFinished" value="<%=WalletTransferOrderFlowStage.FINISHED%>" scope="application"/>







