Module Module1

    Sub Main()
        Dim p As Person = New Person("Tony", "Allen", 32, Person.Genders.Male)
        Console.WriteLine(p)
    End Sub

    Structure Person
        Public firstName As String
        Public lastName As String
        Public age As Integer
        Public gender As Genders

        Public Sub New(ByVal _firstName As String, ByVal _lastName As String, ByVal _age As Integer, ByVal _gender As Genders)
            firstName = _firstName
            lastName = _lastName
            age = _age
            gender = _gender
        End Sub

        Public Overloads Overrides Function ToString() As String
            Return firstName + " " + lastName + " (" + gender.ToString() + "), age " + age.ToString
        End Function

        Enum Genders
            Male
            Female
        End Enum

    End Structure
End Module
