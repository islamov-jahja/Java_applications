package report;

import product.Product;
import product_residue.ProductResidue;

import java.util.HashMap;
import java.util.Map;

public class Report {
  private final Map<String, ProductResidue> products = new HashMap<String, ProductResidue>();

  public void WriteInTheReport(float count, Product product) {
    float countTmp = 0;
    if (products.containsKey(product.GetName()))
      countTmp = products.get(product.GetName()).GetCountOfProduct();

    ProductResidue residue = new ProductResidue(product, count + countTmp);
    products.put(product.GetName(), residue);
  }

  public void PrintReport(){
    for(Map.Entry<String, ProductResidue> pair: products.entrySet())
      System.out.println(pair.getKey() + " - " + pair.getValue().GetCountOfProduct());
  }
}
