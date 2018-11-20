enum TypesOfProducts {
  ForAll, ForAdult
}

public class Product {
  private String m_name;
  private TypesOfProducts m_category;
  private float m_unitPrice;
  private Discount m_discount;
  private float m_bonuses;

  public Product(String name, TypesOfProducts category, float unitPrice, float percentOfDiscount) {
    m_name = name;
    m_category = category;
    Discount discount = new Discount(percentOfDiscount);
    m_discount = discount;
    m_unitPrice = unitPrice;
  }

  public Product(String name, TypesOfProducts category, float unitPrice, float percentOfDiscount, float bonuses) {
    m_name = name;
    m_category = category;
    Discount discount = new Discount(percentOfDiscount);
    m_discount = discount;
    m_unitPrice = unitPrice;
    m_bonuses = bonuses;
  }

  public Product(String name, TypesOfProducts category, float unitPrice) {
    m_name = name;
    m_category = category;
    Discount discount = new Discount(0);
    m_discount = discount;
    m_unitPrice = unitPrice;
  }


  public String GetName() {
    return m_name;
  }

  public float GetUnitPrice() {
    return m_unitPrice;
  }

  public TypesOfProducts GetCategory() {
    return m_category;
  }

  public Discount GetDiscount() {
    return m_discount;
  }

  public float GetBonuses(){
    return m_bonuses;
  }
}
