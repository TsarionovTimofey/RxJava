В Kotlin коллекции (такие как `List`, `Set`, `Map`, `Sequence`) имеют богатый набор встроенных
функций-расширений (extension functions), которые упрощают их обработку и манипуляции. Вот основные
категории и примеры этих функций:

---

### **1. Фильтрация**

Функции для выбора элементов по условию:

- **`filter`**: Фильтрует элементы, оставляя те, которые удовлетворяют условию.
  ```kotlin
  val numbers = listOf(1, 2, 3, 4, 5)
  val evenNumbers = numbers.filter { it % 2 == 0 } // [2, 4]
  ```

- **`filterNot`**: Оставляет элементы, которые **не** удовлетворяют условию.
  ```kotlin
  val oddNumbers = numbers.filterNot { it % 2 == 0 } // [1, 3, 5]
  ```

- **`takeWhile`**: Возвращает элементы до тех пор, пока условие истинно.
  ```kotlin
  val list = listOf(1, 2, 3, 4, 5)
  val result = list.takeWhile { it < 3 } // [1, 2]
  ```

- **`dropWhile`**: Удаляет элементы до тех пор, пока условие истинно.
  ```kotlin
  val result = list.dropWhile { it < 3 } // [3, 4, 5]
  ```

---

### **2. Преобразование**

Функции для преобразования элементов:

- **`map`**: Применяет функцию к каждому элементу и возвращает новый список.
  ```kotlin
  val squares = numbers.map { it * it } // [1, 4, 9, 16, 25]
  ```

- **`flatMap`**: Сначала преобразует каждый элемент в коллекцию, затем объединяет все в одну.
  ```kotlin
  val lists = listOf(listOf(1, 2), listOf(3, 4))
  val flat = lists.flatMap { it } // [1, 2, 3, 4]
  ```

- **`mapIndexed`**: Аналог `map`, но передает индекс элемента.
  ```kotlin
  val withIndices = numbers.mapIndexed { index, value -> "$index: $value" }
  // ["0: 1", "1: 2", ...]
  ```

- **`associateWith`**: Создает `Map`, где ключи — элементы коллекции, а значения — результат
  функции.
  ```kotlin
  val map = numbers.associateWith { it * 2 } // {1=2, 2=4, 3=6, ...}
  ```

- **`groupBy`**: Группирует элементы по ключу.
  ```kotlin
  val grouped = numbers.groupBy { it % 2 } // {0=[2,4], 1=[1,3,5]}
  ```

---

### **3. Агрегация и свертка**

Функции для вычисления результата из коллекции:

- **`reduce`**: Сворачивает коллекцию в одно значение, применяя функцию к парам элементов.
  ```kotlin
  val sum = numbers.reduce { acc, i -> acc + i } // 15
  ```

- **`fold`**: Аналогично `reduce`, но позволяет начать с начального значения.
  ```kotlin
  val product = numbers.fold(1) { acc, i -> acc * i } // 120
  ```

- **`sumOf`**: Суммирует результаты функции для каждого элемента.
  ```kotlin
  val sum = numbers.sumOf { it.toInt() } // 15
  ```

---

### **4. Проверки**

Функции для проверки условий:

- **`any`**: Проверяет, есть ли хотя бы один элемент, удовлетворяющий условию.
  ```kotlin
  val hasEven = numbers.any { it % 2 == 0 } // true
  ```

- **`all`**: Проверяет, все ли элементы удовлетворяют условию.
  ```kotlin
  val allEven = numbers.all { it % 2 == 0 } // false
  ```

- **`none`**: Проверяет, нет ли элементов, удовлетворяющих условию.
  ```kotlin
  val noEven = numbers.none { it % 2 == 0 } // false
  ```

---

### **5. Индексы и элементы**

Функции для работы с индексами:

- **`withIndex`**: Возвращает пары (индекс, элемент).
  ```kotlin
  val indexed = numbers.withIndex().associate { it.index to it.value }
  // {0=1, 1=2, 2=3, ...}
  ```

- **`indexOfFirst`/`indexOfLast`**: Находит первый/последний индекс элемента, удовлетворяющего
  условию.
  ```kotlin
  val index = numbers.indexOfFirst { it > 3 } // 3 (индекс элемента 4)
  ```

---

### **6. Срезы и диапазоны**

Функции для извлечения подколлекций:

- **`take`**: Возвращает первые N элементов.
  ```kotlin
  val firstTwo = numbers.take(2) // [1, 2]
  ```

- **`drop`**: Удаляет первые N элементов.
  ```kotlin
  val rest = numbers.drop(2) // [3, 4, 5]
  ```

- **`slice`**: Возвращает элементы по заданным индексам.
  ```kotlin
  val sliced = numbers.slice(0..2) // [1, 2, 3]
  ```

---

### **7. Поиск элементов**

Функции для поиска:

- **`first`/`last`**: Возвращает первый/последний элемент (или null, если коллекция пуста).
  ```kotlin
  val firstEven = numbers.first { it % 2 == 0 } // 2
  ```

- **`find`**: Возвращает первый элемент, удовлетворяющий условию (или null).
  ```kotlin
  val found = numbers.find { it > 3 } // 4
  ```

- **`elementAtOrNull`**: Возвращает элемент по индексу (или null, если индекс выходит за пределы).
  ```kotlin
  val element = numbers.elementAtOrNull(5) // null
  ```

---

### **8. Преобразование типов**

Функции для изменения типа коллекции:

- **`toTypedArray`**: Преобразует в массив указанного типа.
  ```kotlin
  val array = numbers.toTypedArray() // IntArray
  ```

- **`toList`/`toSet`/`toMap`**: Преобразует в другой тип коллекции.
  ```kotlin
  val set = numbers.toSet() // Set<Int>
  ```

---

### **9. Другие полезные функции**

- **`zip`**: Объединяет два списка в список пар.
  ```kotlin
  val list1 = listOf(1, 2, 3)
  val list2 = listOf("a", "b", "c")
  val zipped = list1.zip(list2) // [(1, "a"), (2, "b"), (3, "c")]
  ```

- **`chunked`**: Разбивает коллекцию на части заданного размера.
  ```kotlin
  val chunks = numbers.chunked(2) // [[1,2], [3,4], [5]]
  ```

- **`windowed`**: Возвращает последовательность окон из элементов.
  ```kotlin
  val windows = numbers.windowed(2) // [[1,2], [2,3], [3,4], [4,5]]
  ```

- **`joinToString`**: Склеивает элементы в строку с разделителем.
  ```kotlin
  val joined = numbers.joinToString(", ") // "1, 2, 3, 4, 5"
  ```

---

### **10. Итерация и побочные эффекты**

Функции для выполнения действий:

- **`forEach`**: Выполняет действие для каждого элемента.
  ```kotlin
  numbers.forEach { println(it) }
  ```

- **`also`/`apply`**: Используются для побочных эффектов (например, логирования).
  ```kotlin
  numbers.also { println("Processing $it") }.map { it * 2 }
  ```

---

### **11. Последовательности (Sequences)**

Для работы с `Sequence` (ленивые вычисления):

- **`asSequence`**: Преобразует коллекцию в последовательность.
- **`sequence`**: Создает последовательность.
- **`onEach`**: Выполняет действие на каждом элементе (например, логирование).
- **`distinct`**: Удаляет дубликаты.

---

### **Примечания**

- **Иммутабельность**: Большинство функций возвращают новые коллекции (кроме MutableList).
- **Синтаксис**: Для списков (`List`) есть дополнительные функции (например, `getOrNull`).
- **Map-функции**: Для `Map` есть `keys`, `values`, `entries`, `filterKeys`, `filterValues`.

---

Эти функции позволяют писать код, который выглядит **более декларативно** и избавляет от циклов
`for` и `if`. Например, вместо:

```kotlin
val evenSquares = mutableListOf<Int>()
for (num in numbers) {
    if (num % 2 == 0) {
        evenSquares.add(num * num)
    }
}
```

можно написать:

```kotlin
val evenSquares = numbers.filter { it % 2 == 0 }.map { it * it }
```
