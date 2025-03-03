### Сравнительная таблица:

| Тип Subject           | Хранит историю?               | Передает новым подписчикам      | Поведение при подключении нового подписчика                              | Потокобезопасность по умолчанию | Примерное поведение                                                       |
|-----------------------|-------------------------------|---------------------------------|--------------------------------------------------------------------------|---------------------------------|---------------------------------------------------------------------------|
| **PublishSubject**    | Нет                           | Только новые значения           | Эмитирует только те значения, которые поступают после подписки           | Нет                             | Подходит для сценариев, где предыдущие данные не важны                    |
| **BehaviorSubject**   | Последнее значение            | Последнее значение + новые      | Передает последнее эмитированное значение и продолжает эмитировать новые | Нет                             | Используется для передачи текущего состояния                              |
| **ReplaySubject**     | Всю историю                   | Все значения                    | Передает все ранее эмитированные значения и продолжает эмитировать новые | Нет                             | Идеально для кэширования полной истории событий                           |
| **AsyncSubject**      | Только последнее значение     | Только последнее при завершении | Передает только последнее значение при вызове `onComplete()`             | Нет                             | Полезен для получения результата асинхронной операции после её завершения |
| **UnicastSubject**    | Нет                           | Только одному подписчику        | Работает как `PublishSubject`, но допускает только одного подписчика     | Нет                             | Используется для строгой привязки к единственному подписчику              |
| **SerializedSubject** | Зависит от обёрнутого Subject | Зависит от обёрнутого Subject   | Обеспечивает потокобезопасность для любого типа `Subject`                | Да                              | Используется для защиты от многопоточных проблем                          |

---

### Дополнительные характеристики:

#### 1. **Потокобезопасность:**

- По умолчанию все `Subject` (кроме `SerializedSubject`) **не являются потокобезопасными**.
- Для работы в многопоточной среде рекомендуется использовать `SerializedSubject` или
  синхронизировать доступ к `Subject`.

#### 2. **Использование с операторами:**

- Все `Subject` можно комбинировать с операторами RxJava (например, `map`, `filter`, `flatMap` и
  т.д.), что позволяет создавать сложные цепочки преобразований.

#### 3. **Ограничения буфера:**

- `ReplaySubject` может быть настроен для хранения ограниченного количества значений или значений за
  определенный период времени:
    - `createWithSize(n)` — сохраняет последние `n` значений.
    - `createWithTime(timespan, unit)` — сохраняет значения за указанный временной интервал.

---

### Когда использовать каждый `Subject`:

| Сценарий                                                | Рекомендуемый `Subject` |
|---------------------------------------------------------|-------------------------|
| Новые подписчики не нуждаются в истории                 | **PublishSubject**      |
| Новые подписчики должны получить последнее состояние    | **BehaviorSubject**     |
| Новые подписчики должны получить полную историю событий | **ReplaySubject**       |
| Нужен только конечный результат асинхронной операции    | **AsyncSubject**        |
| Требуется строгая привязка к единственному подписчику   | **UnicastSubject**      |
| Работа в многопоточной среде                            | **SerializedSubject**   |

---

### Примеры использования:

#### **PublishSubject:**

```kotlin
val subject = PublishSubject.create<String>()
subject.onNext("A") // Новое значение до подписки игнорируется
subject.subscribe { println(it) }
subject.onNext("B") // Выведет: B
```

#### **BehaviorSubject:**

```kotlin
val subject = BehaviorSubject.createDefault("Initial")
subject.onNext("A")
subject.subscribe { println(it) } // Выведет: A (последнее значение)
```

#### **ReplaySubject:**

```kotlin
val subject = ReplaySubject.create<String>()
subject.onNext("A")
subject.onNext("B")
subject.subscribe { println(it) } // Выведет: A B
```

#### **AsyncSubject:**

```kotlin
val subject = AsyncSubject.create<String>()
subject.onNext("A")
subject.onNext("B")
subject.onComplete()
subject.subscribe { println(it) } // Выведет: B (только последнее значение)
```

#### **UnicastSubject:**

```kotlin
val subject = UnicastSubject.create<String>()
subject.onNext("A")
val subscriber = subject.subscribe { println(it) } // Выведет: A
try {
    subject.subscribe { println(it) } // Выбросит IllegalStateException
} catch (e: Exception) {
    println(e.message) // "There is already a subscriber"
}
```

#### **SerializedSubject:**

```kotlin
val publishSubject = PublishSubject.create<String>()
val serializedSubject = SerializedSubject(publishSubject)
serializedSubject.onNext("A")
serializedSubject.onNext("B")
serializedSubject.subscribe { println(it) } // Выведет: B
```

---