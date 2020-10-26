# calculation-payment-schedule
Библиотека для расчета(аннуитетный и дифференцированный) платежей по кредиту.  
Данные для расчета поступают из любого потока InputStream.  
Входные данные должны быть в формате xml.  
Пример правильных входных данных(все теги указанные в данном примере являются обязательными):  
```
<loanParameters>  
    <loanAmount>1000</loanAmount>  
    <interestRate>17</interestRate>  
    <loanTerm>12</loanTerm>  
    <loanDate>2020-10-23T03:31:12</loanDate>  
    <loanType>Аннуитетный</loanType>  
</loanParameters>
```  
где  
loanAmount - Сумма кредита  
interestRate - Процентная ставка в %  
loanTerm - Срок кредита в месяцах  
loanDate - Дата получения кредита  
loanType - Тип кредита (Аннуитетный и дифференцированный).  


На выходе возвращается список платежей в формате xml и записывается в поток OutputStream  
Пример правильных выходных данных:  
```
<payments>
    <payment>
        <amountPayment>91.2</amountPayment>
        <balanceDebt>922.97</balanceDebt>
        <interestAmount>14.17</interestAmount>
        <paymentDate>2020-11-23T03:31:12Z</paymentDate>
        <paymentNumber>1</paymentNumber>
        <principalAmount>77.03</principalAmount>
    </payment>
    <payment>
        <amountPayment>91.22</amountPayment>
        <balanceDebt>0.0</balanceDebt>
        <interestAmount>1.27</interestAmount>
        <paymentDate>2021-10-23T03:31:12Z</paymentDate>
        <paymentNumber>12</paymentNumber>
        <principalAmount>89.95</principalAmount>
    </payment>
</payments>
```  
где  
paymentNumber - Номер платежа  
paymentDate - Дата платежа   
amountPayment - Сумма платежа  
principalAmount - Сумма на погашение основного долга  
interestAmount - Сумма на погашение процентов  
balanceDebt - Остаток задолженности.   

Пример запуска приложения из папки проекта с указанием входного файла для чтения параметров и выходного файла для вывода результатов   
```
java -jar target/calculation-payment-schedule-1.0.jar -i "src/main/resources/input.xml" -o "src/main/resources/output.xml"
```  