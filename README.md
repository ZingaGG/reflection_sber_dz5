**Почему на любом объекте можем вызвать метод getClass()?**
*Ответ:*
Каждый объект в Java является неявным наследником класса Object, где и лежит данный метод

------------------------------------------------------------------------------------------------------------

**Почему на любом классе можем вызвать .class?**
*Ответ:* 
Это статическая конструкция Java, которая применима в момент компеляции. Вызывается на любом классе, по скольку каждый класс имеет метаинформацию, которая представлена объектом Class.

------------------------------------------------------------------------------------------------------------

**В чём отличие динамического прокси от статического?**
*Ответ:*
В статическом мы все создаем вручную, а в динамическом задействуем рефлекисию и класс Proxy.

**Приведите преимущества и недостатки.**
*Ответ:*

Статический прокси:
Нужно создавать вручную (-)
По личному мнению, менее гибкое использование (-)
Быстродействие (+)
Подходит, если мы знаем заранее поведение прокси и уверены, что оно не будет меняться (+/-)

Динамический прокси:
Автоматическое создание (+)
Гибкость (+)
Менее производителен, чем статический (-)
Сложнее в понимании и отладке, т.к. многое происходит под копотом (-)
