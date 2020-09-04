Module Module1

    Sub Main()
        Dim i16 As Int16 = 1
        Dim i32 As Int32 = 1
        Dim db As Decimal = 1

        i16 = i32
        i16 = db

        i32 = i16
        i32 = db

        db = i16
        db = i32
    End Sub

End Module
