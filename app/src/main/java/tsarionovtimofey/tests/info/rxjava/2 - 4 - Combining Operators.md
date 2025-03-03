### Сравнительная таблица:

| Оператор          | Порядок | Параллельность | Начало работы        | Примерное поведение                                                                             |
|-------------------|---------|----------------|----------------------|-------------------------------------------------------------------------------------------------|
| **concat**        | Да      | Нет            | Последовательно      | Ждет завершения первого Observable перед началом второго                                        |
| **merge**         | Нет     | Да             | Одновременно         | Объединяет эмиты из нескольких источников, не гарантируя порядок                                |
| **zip**           | Да      | Да             | Синхронизированно    | Создает пары значений из разных источников по мере их поступления                               |
| **combineLatest** | Нет     | Да             | При первых значениях | Эмитит комбинацию последних значений из всех источников при каждом новом значении любого из них |
| **join**          | Нет     | Да             | По условиям          | Объединяет данные на основе временных окон                                                      |
| **switchOnNext**  | Нет     | Да             | Динамически          | Отменяет предыдущий Observable при переключении на новый                                        |
| **startWith**     | Да      | Нет            | До основного         | Добавляет начальные значения перед основным Observable                                          |
| **amb**           | Нет     | Да             | Первый               | Выбирает первый источник, который начинает эмитить, и игнорирует остальные                      |

### Подробнее о характеристиках:

1. **Порядок**
    - concat: Гарантирует строгий порядок выполнения
    - merge: Не гарантирует порядок
    - zip: Гарантирует порядок создания пар
    - combineLatest: Не гарантирует порядок комбинаций
    - join: Работает с временными окнами
    - switchOnNext: Меняет источник динамически
    - startWith: Гарантирует порядок (до основного потока)
    - amb: Выбирает один источник

2. **Параллельность**
    - concat: Последовательный
    - merge: Параллельный
    - zip: Параллельный, но синхронизированный
    - combineLatest: Параллельный
    - join: Параллельный
    - switchOnNext: Динамический
    - startWith: Последовательный
    - amb: Параллельный выбор

3. **Начало работы**
    - concat: Последовательно после завершения предыдущего
    - merge: Одновременно все источники
    - zip: Синхронизированно при наличии значений из всех источников
    - combineLatest: После получения первых значений из всех источников
    - join: По условиям временных окон
    - switchOnNext: Динамически при переключении
    - startWith: До начала основного потока
    - amb: При первом источнике, который начинает эмитить

### Когда использовать:

- **concat**: Когда важен строгий порядок выполнения
- **merge**: Для параллельной обработки без учета порядка
- **zip**: Для создания пар/комбинаций значений из разных источников
- **combineLatest**: Для реагирования на последние значения из нескольких источников
- **join**: Для сложных временных зависимостей
- **switchOnNext**: Для динамического переключения между источниками
- **startWith**: Для добавления начальных значений
- **amb**: Когда нужно выбрать только один источникrvable при переключении


1. concat()

```kotlin
val observable1 = Observable.just("A", "B")
val observable2 = Observable.just("C", "D")

Observable.concat(observable1, observable2)
    .subscribe { println(it) } // Выведет: A B C D
```

2. merge()

```kotlin
val observable1 =
    Observable.intervalRange(0, 3, 0, 500, TimeUnit.MILLISECONDS).map { "Source 1: $it" }
val observable2 =
    Observable.intervalRange(0, 3, 0, 300, TimeUnit.MILLISECONDS).map { "Source 2: $it" }

Observable.merge(observable1, observable2)
    .subscribe { println(it) }
// Вывод будет перемешанным, так как оба источника эмитят одновременно
```

3. zip()

```kotlin
val observable1 = Observable.just("A", "B", "C")
val observable2 = Observable.just(1, 2, 3)

Observable.zip(
    observable1,
    observable2,
    BiFunction<String, Int, String> { t1, t2 -> "$t1$t2" }
).subscribe { println(it) } // Выведет: A1 B2 C3
```

4. combineLatest()

```kotlin
val observable1 = Observable.intervalRange(0, 3, 0, 500, TimeUnit.MILLISECONDS).map { "A$it" }
val observable2 = Observable.intervalRange(0, 3, 0, 300, TimeUnit.MILLISECONDS).map { "B$it" }

Observable.combineLatest(
    observable1,
    observable2,
    BiFunction<String, String, String> { t1, t2 -> "$t1:$t2" }
).subscribe { println(it) }
// Будет выводить комбинации последних значений из обоих источников
```

5. join()

```kotlin
val left = Observable.interval(100, TimeUnit.MILLISECONDS)
val right = Observable.interval(150, TimeUnit.MILLISECONDS)

left.join(
    right,
    { l -> Observable.timer(200, TimeUnit.MILLISECONDS) },
    { r -> Observable.timer(100, TimeUnit.MILLISECONDS) },
    { l, r -> "$l:$r" }
).take(5)
    .subscribe { println(it) }
```

6. switchOnNext()

```kotlin
val outer = Observable.just(
    Observable.interval(100, TimeUnit.MILLISECONDS),
    Observable.interval(150, TimeUnit.MILLISECONDS)
)

outer.switchOnNext()
    .take(5)
    .subscribe { println(it) }
// При переключении между внутренними Observable, предыдущий отменяется
```

7. startWith()

```kotlin
val observable = Observable.just("B", "C")
observable.startWith(Observable.just("A"))
    .subscribe { println(it) } // Выведет: A B C
```

8. amb()

```kotlin
val observable1 = Observable.intervalRange(0, 3, 0, 500, TimeUnit.MILLISECONDS).map { "A$it" }
val observable2 = Observable.intervalRange(0, 3, 0, 300, TimeUnit.MILLISECONDS).map { "B$it" }

Observable.amb(listOf(observable1, observable2))
    .subscribe { println(it) }
// Выберет первый источник, который начал эмитить значения
```