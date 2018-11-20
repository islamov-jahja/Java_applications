package payment_method;

public class PaymentMethod {
  private Methods m_methodOfPay;

  public PaymentMethod(Methods method){
    m_methodOfPay = method;
  }

  public Methods getMethod(){
    return m_methodOfPay;
  }
}
