package vn.mog.framework.contract.base;

public class BaseResponseType<T> extends MobiliserResponseType {

 private static final long serialVersionUID = 1L;
 private T data;

 public T getData() {
  return data;
 }

 public void setData(T data) {
  this.data = data;
 }

}
