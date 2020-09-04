Module Module1

    Sub Main()
        Dim p As Person = New Person("Tony", "Allen", 32)
        Console.WriteLine(p)
    End Sub

    Structure Person
        Public firstName As String
        Public lastName As String
        Public age As Integer

        Public Sub New(ByVal _firstName As String, ByVal _lastName As String, ByVal _age As Integer)
            firstName = _firstName
            lastName = _lastName
            age = _age
        End Sub

        Public Overloads Overrides Function ToString() As String
            Return firstName + " " + lastName + ", age " + age.ToString
        End Function
    End Structure
End Module
