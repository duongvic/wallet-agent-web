
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-xxl-4 col-lg-4 col-md-4 col-sm-12" id="otp-email">
  <div class="panel panel-bordered">
    <div class="panel-heading">
      <h3 class="panel-title">OTP Email</h3>
    </div>
    <div class="panel-body">
      <p>Với mỗi giao dịch, hệ thống sẽ gửi mã xác thực vào email đăng kí</p>
      <img src="/assets/images/placeholder100.png" class="br3 mb-20">
      <div class="row">
        <div class="col-md-12 pl-0">
          <div class="col-md-12">
            <div class="form-group row mb-5">
              <label class=" col-sm-4 col-form-label pb-0">Email : </label>
              <div class=" col-sm-6  text-right">
                <p class="form-control-plaintext pb-0">nguyenvana@gmail.com</p>
              </div>
            </div>
            <div class="form-group row mb-5">
              <label class="col-sm-4 col-form-label pb-0">Hạn mức:
                <i class="fa  fa-question-circle" data-placement="top" data-toggle="tooltip"
                   data-original-title="Hạn mức OTP là số tiền tối thiểu cho một giao dịch mà bạn muốn sử dụng dịch vụ bảo mật OTP"></i>
              </label>
              <div class=" col-sm-6 text-right">
                <p class="form-control-plaintext pb-0">100,000 </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="panel-footer vertical-align-middle col-sm-12">
      <a href="/system/payment-security/email/register" class="btn btn-primary" id="otp-email-btn" disabled="disabled">Đăng kí</a>
    </div>
  </div>
</div>