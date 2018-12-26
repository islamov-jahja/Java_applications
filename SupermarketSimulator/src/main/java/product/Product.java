package product;

import discount.Discount;

public class Product {
  private final String m_name;
  private final TypesOfProducts m_category;
  private final float m_unitPrice;
  private final Discount m_discount;
  private float m_bonuses;
  private final UnitOfProduct m_unitOfProduct;

  public Product(String name, TypesOfProducts category, float unitPrice, float percentOfDiscount, UnitOfProduct unitOfProduct) {
    m_name = name;
    m_category = category;
    m_discount = new Discount(percentOfDiscount);
    m_unitPrice = unitPrice;
    m_unitOfProduct = unitOfProduct;
  }

  public Product(String name, TypesOfProducts category, float unitPrice, float percentOfDiscount, UnitOfProduct unitOfProduct, float bonuses) {
    m_name = name;
    m_category = category;
    m_discount = new Discount(percentOfDiscount);
    m_unitPrice = unitPrice;
    m_bonuses = bonuses;
    m_unitOfProduct = unitOfProduct;
  }

  public Product(String name, TypesOfProducts category, float unitPrice, UnitOfProduct unitOfProduct) {
    m_name = name;
    m_category = category;
    m_discount = new Discount(0);
    m_unitPrice = unitPrice;
    m_unitOfProduct = unitOfProduct;
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

  public float GetBonuses() {
    return m_bonuses;
  }
}
