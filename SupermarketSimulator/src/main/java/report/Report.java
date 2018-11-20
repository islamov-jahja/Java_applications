package report;

import product.Product;
import product_residue.ProductResidue;

import java.util.HashMap;
import java.util.Map;

public class Report {
  private Map<String, ProductResidue> products = new HashMap<String, ProductResidue>();

  public void WriteInTheReport(float count, Product product) {
    ProductResidue residue = new ProductResidue(product, count);
    products.put(product.GetName(), residue);
  }

  public void PrintReport(){
    for(Map.Entry<String, ProductResidue> pair: products.entrySet())
      System.out.println(pair.getKey() + " - " + pair.getValue().GetCountOfProduct());
  }
}
