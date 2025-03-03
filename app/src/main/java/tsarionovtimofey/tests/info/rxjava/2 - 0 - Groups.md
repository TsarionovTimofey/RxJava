### Когда использовать каждую группу:

| Сценарий                       | Группа операторов             |
|--------------------------------|-------------------------------|
| Создание потока данных         | Создающие                     |
| Преобразование данных          | Преобразующие                 |
| Фильтрация данных              | Фильтрующие                   |
| Объединение нескольких потоков | Комбинирующие/Объединяющие    |
| Проверка условий               | Условные и булевы             |
| Работа с отдельными элементами | Элементовые                   |
| Обработка ошибок               | Ошибочные                     |
| Утилитные функции              | Утилитные                     |
| Математические вычисления      | Математические и агрегирующие |
| Управление подпиской           | Подписка и отмена             |

### 1. Создающие операторы (Creation Operators)

**Назначение:** Используются для создания Observable/Flowable из различных источников данных.

- just()
- from()
- create()
- interval()
- timer()
- range()
- defer()
- empty()
- never()
- error()
- using()
- generate()

**Пример использования:**

```kotlin
Observable.just("Hello", "World")
    .subscribe { println(it) } // Выведет: Hello World
```

### 2. Преобразующие операторы (Transforming Operators)

**Назначение:** Используются для изменения или преобразования данных в потоке.

- map()
- flatMap()
- concatMap()
- switchMap()
- scan()
- buffer()
- window()
- groupBy()
- cast()
- materialize()
- dematerialize()
- compose()

**Пример использования:**

```kotlin
Observable.just("apple", "banana", "orange")
    .map { it.length }
    .subscribe { println(it) } // Выведет длины строк
```

### 3. Фильтрующие операторы (Filtering Operators)

**Назначение:** Используются для выборки или ограничения элементов в потоке.

- filter()
- take()
- skip()
- distinct()
- debounce()
- throttleFirst()
- sample()
- elementAt()
- ignoreElements()

**Пример использования:**

```kotlin
Observable.just(1, 2, 3, 4, 5)
    .filter { it % 2 == 0 }
    .subscribe { println(it) } // Выведет: 2 4
```

### 4. Комбинирующие операторы (Combining Operators)

**Назначение:** Используются для объединения нескольких Observable/Flowable.

- concat()
- merge()
- zip()
- combineLatest()
- join()
- switchOnNext()
- startWith()
- amb()

**Пример использования:**

```kotlin
val observable1 = Observable.just("A", "B")
val observable2 = Observable.just("1", "2")

Observable.zip(observable1, observable2) { a, b -> "$a$b" }
    .subscribe { println(it) } // Выведет: A1 B2
```

### 5. Объединяющие операторы (Joining Operators)

**Назначение:** Похожи на комбинирующие, но имеют более специфичное поведение.

- and()
- when()

**Пример использования:**

```kotlin
val observable1 = Observable.just("A")
val observable2 = Observable.just("B")

observable1.and(observable2).then(Observable.just("Combined"))
    .subscribe { println(it) } // Выведет: Combined
```

### 6. Условные и булевы операторы (Conditional and Boolean Operators)

**Назначение:** Используются для проверки условий в потоке.

- all()
- contains()
- defaultIfEmpty()
- sequenceEqual()
- isEmpty()
- count()

**Пример использования:**

```kotlin
Observable.just(1, 2, 3, 4)
    .all { it < 5 }
    .subscribe { println(it) } // Выведет: true
```

### 7. Элементовые операторы (Element Operators)

**Назначение:** Используются для работы с отдельными элементами потока.

- first()
- last()
- single()
- find()
- elementAt()

**Пример использования:**

```kotlin
Observable.just(1, 2, 3)
    .firstOrError()
    .subscribe { println(it) } // Выведет: 1
```

### 8. Ошибочные операторы (Error Handling Operators)

**Назначение:** Используются для обработки ошибок в потоке.

- onErrorReturn()
- onErrorResumeNext()
- retry()
- retryWhen()
- onExceptionResumeNext()

**Пример использования:**

```kotlin
Observable.error<String>(Exception("Test Error"))
    .onErrorReturn { "Default Value" }
    .subscribe { println(it) } // Выведет: Default Value
```

### 9. Утилитные операторы (Utility Operators)

**Назначение:** Предоставляют различные полезные функции.

- doOnEach()
- doOnNext()
- doOnError()
- doOnComplete()
- subscribeOn()
- observeOn()
- timeout()

**Пример использования:**

```kotlin
Observable.just("Hello")
    .doOnNext { println("Processing $it") }
    .subscribe { println(it) }
// Выведет:
// Processing Hello
// Hello
```

### 10. Математические и агрегирующие операторы (Mathematical and Aggregate Operators)

**Назначение:** Используются для математических вычислений и агрегации данных.

- min()
- max()
- average()
- sum()
- reduce()

**Пример использования:**

```kotlin
Observable.just(1, 2, 3, 4)
    .reduce { acc, value -> acc + value }
    .subscribe { println(it) } // Выведет: 10
```

### 11. Подписка и отмена (Subscription and Disposal Operators)

**Назначение:** Управляют подпиской и отменой подписки.

- dispose()
- isDisposed()
- Disposable

**Пример использования:**

```kotlin
val disposable = Observable.interval(1, TimeUnit.SECONDS)
    .subscribe { println(it) }

Thread.sleep(3000)
disposable.dispose() // Отменяет подписку
```