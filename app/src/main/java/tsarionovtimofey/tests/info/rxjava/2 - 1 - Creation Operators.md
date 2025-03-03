### Сравнительная таблица:

| Оператор          | Тип преобразования                | Порядок | Параллельность        | Возвращаемый тип            | Примерное поведение                                                        |
|-------------------|-----------------------------------|---------|-----------------------|-----------------------------|----------------------------------------------------------------------------|
| **map**           | Одиночное преобразование          | Да      | Нет                   | Same as source              | Преобразует каждый элемент с помощью функции                               |
| **flatMap**       | Множественное преобразование      | Нет     | Да                    | Observable<Observable<T>>   | Преобразует каждый элемент в Observable и объединяет результаты            |
| **concatMap**     | Множественное преобразование      | Да      | Нет                   | Observable<Observable<T>>   | Похож на flatMap, но гарантирует порядок выполнения                        |
| **switchMap**     | Множественное преобразование      | Нет     | Да                    | Observable<Observable<T>>   | Отменяет предыдущее преобразование при новом эмите                         |
| **scan**          | Аккумулирующее преобразование     | Да      | Нет                   | Same as source              | Накапливает результаты с использованием функции                            |
| **buffer**        | Группировка по количеству         | Да      | Нет                   | Observable<List<T>>         | Собирает элементы в буферы заданного размера                               |
| **window**        | Группировка по времени/количеству | Да      | Нет                   | Observable<Observable<T>>   | Разбивает поток на окна                                                    |
| **groupBy**       | Группировка по ключу              | Нет     | Да                    | GroupedObservable<K,V>      | Группирует элементы по ключу                                               |
| **cast**          | Преобразование типа               | Да      | Нет                   | Observable<NewType>         | Преобразует тип элементов                                                  |
| **materialize**   | Преобразование уведомлений        | Да      | Нет                   | Observable<Notification<T>> | Преобразует все уведомления (next, error, complete) в объекты Notification |
| **dematerialize** | Обратное materialize              | Да      | Нет                   | Observable<T>               | Преобразует Notification обратно в поток                                   |
| **compose**       | Композиция операторов             | Да      | Зависит от реализации | Any                         | Позволяет создавать цепочки преобразований                                 |

### Подробнее о характеристиках:

1. **Тип преобразования**
    - map: Простое преобразование одного элемента
    - flatMap/concatMap/switchMap: Преобразование в Observable
    - scan: Аккумулирующее преобразование
    - buffer/window: Группировка элементов
    - groupBy: Категоризация элементов
    - cast: Изменение типа
    - materialize/dematerialize: Преобразование уведомлений

2. **Порядок**
    - map, concatMap, scan, buffer, window, cast, materialize, dematerialize: Гарантируют порядок
    - flatMap, switchMap, groupBy: Не гарантируют порядок

3. **Параллельность**
    - flatMap, switchMap, groupBy: Позволяют параллельную обработку
    - Остальные операторы: Последовательная обработка

4. **Возвращаемый тип**
    - map, scan, cast, materialize, dematerialize: Возвращают тот же тип потока
    - flatMap, concatMap, switchMap, window: Возвращают Observable<Observable<T>>
    - buffer: Возвращает Observable<List<T>>
    - groupBy: Возвращает GroupedObservable<K,V>

5. **Когда использовать:**

| Сценарий                              | Рекомендуемый оператор    |
|---------------------------------------|---------------------------|
| Простое преобразование элементов      | map                       |
| Асинхронное преобразование            | flatMap                   |
| Асинхронное преобразование с порядком | concatMap                 |
| Динамическое переключение             | switchMap                 |
| Накопление результатов                | scan                      |
| Группировка по количеству             | buffer                    |
| Группировка по времени/количеству     | window                    |
| Категоризация элементов               | groupBy                   |
| Изменение типа                        | cast                      |
| Работа с уведомлениями                | materialize/dematerialize |
| Создание цепочек преобразований       | compose                   |

Преобразующие операторы используются для изменения или преобразования данных в потоке:

### 1. map()

Преобразует каждый элемент с помощью функции

```kotlin
Observable.just("apple", "banana", "orange")
    .map { it.length }
    .subscribe { println(it) } // Выведет длины строк
```

### 2. flatMap()

Преобразует каждый элемент в Observable и объединяет результаты

```kotlin
Observable.just(1, 2, 3)
    .flatMap {
        Observable.just(it * 2, it * 3)
    }
    .subscribe { println(it) } // Выведет перемешанные значения
```

### 3. concatMap()

Похож на flatMap, но гарантирует порядок выполнения

```kotlin
Observable.just(1, 2, 3)
    .concatMap {
        Observable.just(it * 2, it * 3)
    }
    .subscribe { println(it) } // Выведет строго по порядку
```

### 4. switchMap()

Отменяет предыдущее преобразование при новом эмите

```kotlin
val subject = PublishSubject.create<Int>()
subject.switchMap {
    Observable.interval(100, TimeUnit.MILLISECONDS)
        .take(3)
        .map { "$it: $it" }
}
    .subscribe { println(it) }

subject.onNext(1) // Начнет новый поток
subject.onNext(2) // Отменит предыдущий и начнет новый
```

### 5. scan()

Накапливает результаты с использованием функции

```kotlin
Observable.just(1, 2, 3, 4)
    .scan(0) { acc, value -> acc + value }
    .subscribe { println(it) } // Выведет: 0, 1, 3, 6, 10
```

### 6. buffer()

Собирает элементы в буферы

```kotlin
Observable.interval(100, TimeUnit.MILLISECONDS)
    .buffer(3) // Создаст список из 3 элементов
    .take(2)
    .subscribe { println(it) }
// Выведет: [0, 1, 2] затем [3, 4, 5]
```

### 7. window()

Разбивает поток на окна

```kotlin
Observable.interval(100, TimeUnit.MILLISECONDS)
    .window(3) // Создаст Observable<Observable<Long>>
    .flatMap { it.toList() } // Преобразует каждое окно в список
    .take(2)
    .subscribe { println(it) }
// Выведет: [0, 1, 2] затем [3, 4, 5]
```

### 8. groupBy()

Группирует элементы по ключу

```kotlin
Observable.just("apple", "banana", "cherry", "avocado")
    .groupBy { it[0] } // Группирует по первой букве
    .flatMapSingle { group ->
        group.toList().map { "${group.key}: $it" }
    }
    .subscribe { println(it) }
// Выведет группы слов начинающихся на одну букву
```

### 9. cast()

Преобразует тип элементов

```kotlin
Observable.just("1", "2", "3")
    .cast(String::class.java) // Явное указание типа
    .map { it.toInt() }
    .subscribe { println(it) }
```

### 10. materialize()

Преобразует все уведомления (next, error, complete) в объекты Notification

```kotlin
Observable.error<String>(Exception("Error"))
    .materialize()
    .subscribe {
        when (it) {
            is Notification.OnNext -> println("OnNext: ${it.value}")
            is Notification.OnError -> println("OnError: ${it.error.message}")
            is Notification.OnCompleted -> println("Completed")
        }
    }
```

### 11. dematerialize()

Обратная операция к materialize()

```kotlin
Observable.just(Notification.createOnNext("Test"))
    .dematerialize<String>()
    .subscribe { println(it) }
```

### 12. compose()

Позволяет создавать цепочки преобразований

```kotlin
fun <T> applySchedulers(): FlowableTransformer<T, T> {
    return Flowable.< T > compose { upstream ->
        upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

Flowable.just("Hello")
    .compose(applySchedulers())
    .subscribe { println(it) }
```