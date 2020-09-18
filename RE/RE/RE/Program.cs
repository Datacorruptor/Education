using System;
using System.Text.RegularExpressions;


namespace RE
{
  class Program
  {
    static void Main(string[] args)
    {
      string[] text ={"Макс",
        "C++",
        " +79263772622",
        " 89263772622",
        " (926)3772622" };

      foreach (string item in text)
      {
        string pattern = @"[+]?[1-9]?\s?[(]?[0-9]{3}[)]?[\s-]?[0-9]{3}[\s-]?[0-9]{2}[\s-]?[0-9]{2}";
        if (Regex.IsMatch(item, pattern))
        {
          //Console.WriteLine($"{item} это телефон"); 
          Console.WriteLine(item + " это телефон");
        }

        else
        {
          Console.WriteLine(
          string.Format("{0} это НЕ телефон", item)
          );
        }

      }
    }
  }
}
