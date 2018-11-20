import java.util.HashMap;
import java.util.Map;

public class Report {
  private Map<String, ProductResidue> products = new HashMap<String, ProductResidue>();

  public void WriteInTheReport(float count, Product product) {
    ProductResidue residue = new ProductResidue(product, count);
    products.put(product.GetName(), residue);
  }
}
