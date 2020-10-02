using System;
using System.Collections.Generic;
using System.Text;

namespace rep6
{
  class Person
  {
    private string name;
    private string surname;
    private DateTime dateOfBirth;

    public string Name { get { return name; } }
    public string Surname { get { return surname; } }
    public DateTime DateOfBirth { get { return dateOfBirth; } }
    public int YearOfBirth {
      get {
        return dateOfBirth.Year;
      }
      set {
        //dateOfBirth.Year = value;
      }
    }
    public Person(string _name, string _surname, DateTime _dateOfBirth)
    {
      name = _name;
      surname = _surname;
      dateOfBirth = _dateOfBirth;
    }

    public Person()
    {
      name = "Иван";
      surname = "Смитт";
      dateOfBirth = new DateTime(2001, 11, 8, 0, 0, 0);
    }

    public override string ToString()
    {
      return name + " " + surname + " was born on " + dateOfBirth.ToShortDateString();
    }
    public virtual string ToShortString()
    {
      return name + " " + surname;
    }
  }
}

