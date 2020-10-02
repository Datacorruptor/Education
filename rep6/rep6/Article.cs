using System;
using System.Collections.Generic;
using System.Text;

namespace rep6
{
  class Article
  {
    public Person Author { get; set; }
    public string Title { get; set; }
    public double Rate { get; set; }

    public Article(Person _author, string _title, double _rate)
    {
      Author = _author;
      Title = _title;
      Rate = _rate;
    }

    public Article()
    {
      Author = new Person();
    }
  }
}
