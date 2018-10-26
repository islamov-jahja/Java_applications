class GetNetAddress {
  public static void main(String[] args) {
    //put your task here
    if (IsCorrectCountOfArgs(args)) {
      if (args[0].split("\\.").length == 4 && args[1].split("\\.").length == 4) {
        long[] ip_address = GetArrOfIntegerFromStringIpAddress(args[0].split("\\."));
        long[] mask = GetArrOfIntegerFromStringMask(args[1].split("\\."));
        if (ip_address[0] != -404 && mask[0] != -404) {
          if (!IsCorrectIpAddress(ip_address, args[0]))
            System.out.println("Wrong ip address");
          if (IsCorrectMask(mask, args[1]) && IsCorrectIpAddress(ip_address, args[0]))
            System.out.println(GetNetAdress(ip_address, mask));
          else if (IsCorrectIpAddress(ip_address, args[0]))
            System.out.println("Wrong mask");
        }
      } else {
        if (args[0].split("\\.").length != 4)
          System.out.println("Wrong ip address");
        if(args[1].split("\\.").length != 4)
          System.out.println("Wrong mask");
      }
    } else
      System.out.println("GetNetAddress <IP address> <Subnet mask>");
  }

  private static boolean IsCorrectIpAddress(long[] ipAddress, String ipAdress) {
    int countofPoint = 0;
    for(int i = 0; i < ipAdress.length(); i++)
      if(ipAdress.charAt(i) == '.')
        countofPoint++;

    if(countofPoint != 3)
      return false;
    for (int i = 0; i < ipAddress.length; i++)
      if (ipAddress[i] < 0 || ipAddress[i] > 255)
        return false;

    return true;
  }

  private static long[] GetArrOfIntegerFromStringMask(String[] arrOfString) {
    long[] intArray = new long[4];

    try {
      for (int i = 0; i < arrOfString.length; i++) {
        if (arrOfString[i].equals("-0")) {
          System.out.println("Wrong mask");
          intArray[0] = -404;
          return intArray;
        }
        intArray[i] += Integer.parseInt(arrOfString[i]);
      }
    } catch (NumberFormatException e) {
      System.out.println("Wrong mask");
      intArray[0] = -404;
    }
    return intArray;
  }

  private static long[] GetArrOfIntegerFromStringIpAddress(String[] arrOfString) {
    long[] intArray = new long[4];

    try {
      for (int i = 0; i < arrOfString.length; i++) {
        if (arrOfString[i].equals("-0")) {
          System.out.println("Wrong ip address");
          intArray[0] = -404;
          return intArray;
        }
        intArray[i] += Integer.parseInt(arrOfString[i]);
      }
    } catch (NumberFormatException e) {
      System.out.println("Wrong ip address");
      intArray[0] = -404;
    }
    return intArray;
  }

  private static boolean IsCorrectMask(long[] mask, String maskS) {
    int countofPoint = 0;
    for(int i = 0; i < maskS.length(); i++)
      if(maskS.charAt(i) == '.')
        countofPoint++;

    if(countofPoint != 3)
      return false;

    for (int i = 0; i < mask.length; i++)
      if (mask[i] < 0 || mask[i] > 255)
        return false;

    for (int i = 0; i < mask.length; i++) {
      String number = String.format("%8s", Long.toBinaryString(mask[i])).replace(' ', '0');
      for (int j = 1; j < number.length(); j++)
        if (number.charAt(j - 1) < number.charAt(j))
          return false;
    }

    return true;
  }

  private static String GetNetAdress(long[] ip_adress, long[] mask) {
    long[] address = new long[4];
    String addressS = new String();

    for (int i = 0; i < 4; i++) {
      if (i != 3) {
        address[i] = ip_adress[i] & mask[i];
        addressS += String.valueOf(address[i]) + '.';
      } else {
        address[i] = ip_adress[i] & mask[i];
        addressS += String.valueOf(address[i]);
      }
    }

    return addressS;
  }

  private static boolean IsCorrectCountOfArgs(String[] args) {
    return (args.length == 2);
  }
}