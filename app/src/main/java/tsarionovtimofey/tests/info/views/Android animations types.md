## **Сравнительная таблица видов анимаций**

| **Тип анимации**              | **Описание**                                                         | **Преимущества**                               | **Недостатки**                        |
|-------------------------------|----------------------------------------------------------------------|------------------------------------------------|---------------------------------------|
| **Property Animation**        | Анимация реальных свойств объекта.                                   | Гибкость, возможность работы с любыми числами. | Более сложная настройка.              |
| **View Animation**            | Изменение визуальных свойств `View` без изменения реальных значений. | Простота использования.                        | Не меняет реальные свойства объекта.  |
| **Drawable Animation**        | Анимация последовательности изображений.                             | Простота реализации для простых анимаций.      | Ограниченность (только изображения).  |
| **Transitions**               | Автоматическое создание анимаций при изменении состояния экрана.     | Автоматизация процесса.                        | Меньше контроля над деталями.         |
| **MotionLayout**              | Анимация на основе констрейнтов.                                     | Сложные анимации с несколькими состояниями.    | Требует дополнительной настройки XML. |
| **Shared Element Transition** | Плавный переход общих элементов между экранами.                      | Естественный эффект перехода.                  | Ограниченность в использовании.       |
| **Frame-by-Frame Animation**  | Анимация последовательности изображений.                             | Простота реализации для простых анимаций.      | Ограниченность (только изображения).  |

---

1. **ValueAnimator** и **ObjectAnimator**: Для анимаций с изменением реальных свойств.
2. **View Animation**: Для простых анимаций, где реальное изменение свойств не требуется.
3. **Drawable Animation**: Для анимации последовательности изображений.
4. **Transitions**: Для автоматического создания анимаций при изменении состояния экрана.
5. **MotionLayout**: Для сложных анимаций с несколькими состояниями.
6. **Shared Element Transition**: Для плавного перехода общих элементов между экранами.
8. **Frame-by-Frame Animation**: Для создания анимаций на основе последовательности изображений.

Выбор типа анимации зависит от ваших потребностей и сложности задачи. В большинстве случаев
рекомендуется использовать **Property Animation** (включая `ObjectAnimator`), так как она является
более современной и функциональной.

## **1. Property Animation (Анимация свойств)**

### **Что это?**

Система анимации, которая работает напрямую с реальными свойствами объектов. Она позволяет изменять
любые числовые свойства объекта (не только `View`).

### **Основные компоненты:**

- **ValueAnimator**: Вычисляет значения во времени, но не применяет их автоматически.
- **ObjectAnimator**: Наследуется от `ValueAnimator` и автоматически применяет изменения к
  указанному свойству через `getter`/`setter`.

### **Примеры использования:**

- Анимация перемещения (`translationX`, `translationY`).
- Масштабирование (`scaleX`, `scaleY`).
- Поворот (`rotation`).
- Изменение прозрачности (`alpha`).
- Анимация цвета (`backgroundColor`).

### **Код примера:**

```kotlin
// ObjectAnimator для перемещения View
ObjectAnimator.ofFloat(view, "translationX", 0f, 200f).apply {
    duration = 1000
    start()
}
```

```kotlin
// Вычисление прогресса загрузки
val animator = ValueAnimator.ofInt(0, 100)
animator.addUpdateListener { animation ->
    progressBar.progress = animation.animatedValue as Int
}
animator.duration = 2000
animator.start()
```

---

## **2. View Animation (Традиционная анимация)**

### **Что это?**

Старая система анимации, основанная на изменениях **визуального представления** элементов (`View`).
Она не меняет реальные свойства объекта, а только его внешний вид.

### **Основные классы:**

- `TranslateAnimation`: Перемещение.
- `ScaleAnimation`: Масштабирование.
- `RotateAnimation`: Поворот.
- `AlphaAnimation`: Изменение прозрачности.

### **Пример использования:**

```kotlin
// TranslateAnimation для перемещения View
val animation = TranslateAnimation(0f, 200f, 0f, 0f)
animation.duration = 1000
view.startAnimation(animation)
```

---

## **3. Drawable Animation (Анимация рисуемых объектов)**

### **Что это?**

Анимация последовательности изображений (как в мультфильмах), где каждое изображение представляет
собой кадр анимации.

### **Особенности:**

- Основана на переключении между изображениями.
- Используется для создания простых анимированных эффектов (например, загрузочные индикаторы).

### **Код примера:**

```xml
<!-- XML-определение анимации Drawable -->
<animation-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/frame1" android:duration="100" />
    <item android:drawable="@drawable/frame2" android:duration="100" />
</animation-list>
```

```kotlin
// Применение анимации Drawable
val drawable = context.getDrawable(R.drawable.animation_list) as AnimationDrawable
view.background = drawable
drawable.start()
```

---

## **4. Transitions (Анимации переходов)**

### **Что это?**

Система анимации, которая автоматически создает анимации при изменении состояния экрана или макета.

### **Основные типы переходов:**

- `Fade`: Плавное появление/исчезновение.
- `Slide`: Слайдинговые эффекты.
- `ChangeBounds`: Плавное изменение размеров или положения.
- `SharedElementTransition`: Плавный переход общих элементов между экранами.

### **Пример использования:**

```kotlin
// Создание Fade анимации
val fade = Fade(Fade.IN)
fade.duration = 1000

// Применение анимации к контейнеру
TransitionManager.beginDelayedTransition(container, fade)

// Изменение видимости View
view.visibility = View.VISIBLE
```

---

## **5. Layout Animation (Анимация макета)**

### **Что это?**

Анимация, применяемая ко всем дочерним элементам контейнера (`ViewGroup`) при их появлении или
исчезновении.

### **Особенности:**

- Каждый дочерний элемент анимируется независимо.
- Часто используется для списков или сеток.

### **Код примера:**

```xml
<!-- XML-определение анимации Layout -->
<layoutAnimation xmlns:android="http://schemas.android.com/apk/res/android"
    android:animation="@anim/slide_in_from_right" android:delay="0.3"
    android:animationOrder="normal" />
```

```kotlin
// Применение анимации к контейнеру
val layoutAnimationController =
    LayoutAnimationController(AnimationUtils.loadAnimation(context, R.anim.slide_in_from_right))
layoutAnimationController.delay = 0.3f
container.layoutAnimation = layoutAnimationController
container.scheduleLayoutAnimation()
```

---

## **6. Physics-Based Animation (Физическая анимация)**

### **Что это?**

Анимация, основанная на физических моделях (например, упругость, гравитация). Предоставляет более
естественные и реалистичные эффекты.

### **Основные классы:**

- `SpringAnimation`: Создает упругие эффекты.
- `FlingAnimation`: Создает эффект инерции.

### **Пример использования:**

```kotlin
// SpringAnimation для создания упругого эффекта
val springAnimation = SpringAnimation(view, DynamicAnimation.TRANSLATION_X)
springAnimation.spring.stiffness = SpringForce.STIFFNESS_LOW
springAnimation.spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
springAnimation.animateToFinalPosition(200f)
```

---

## **7. MotionLayout Animation**

### **Что это?**

Библиотека ConstraintLayout предоставляет `MotionLayout`, который позволяет создавать сложные
анимации на основе констрейнтов.

### **Особенности:**

- Определяется через XML-файл анимации.
- Поддерживает переходы между состояниями макета.
- Идеально подходит для сложных анимаций (например, раскрытие меню или преобразование формы).

### **Код примера:**

```xml
<!-- XML-определение MotionScene -->
<MotionScene xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:motion="http://schemas.android.com/apk/res-auto">
    <Transition motion:constraintSetStart="@id/start" motion:constraintSetEnd="@id/end"
        motion:duration="1000" />
</MotionScene>
```

```kotlin
// Применение MotionLayout
motionLayout.transitionToState(R.id.end)
```

---

## **8. Shared Element Transition (Общие элементы)**

### **Что это?**

Анимация, которая обеспечивает плавный переход общих элементов между двумя экранами (например,
изображение из списка в детальный экран).

### **Особенности:**

- Требует использования `ActivityOptions` или `FragmentTransaction`.
- Поддерживает масштабирование, перемещение и другие эффекты.

### **Код примера:**

```kotlin
// Создание анимации Shared Element
val options = ActivityOptions.makeSceneTransitionAnimation(
    this,
    Pair(imageView, "imageTransition")
)
startActivity(intent, options.toBundle())
```

---

## **9. AnimatorSet (Группировка анимаций)**

### **Что это?**

Класс `AnimatorSet` позволяет объединять несколько анимаций в одну последовательность или группу.

### **Особенности:**

- Можно запускать анимации одновременно, последовательно или с задержкой.
- Гибкая настройка порядка выполнения анимаций.

### **Пример использования:**

```kotlin
// Создание AnimatorSet
val animator1 = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
val animator2 = ObjectAnimator.ofFloat(view, "translationY", 0f, -200f)

val animatorSet = AnimatorSet()
animatorSet.playSequentially(animator1, animator2) // Последовательное выполнение
animatorSet.duration = 1000
animatorSet.start()
```

---

## **11. Tween Animation (Интерполяция)**

### **Что это?**

Это подтип **View Animation**, который позволяет создавать анимации перемещения, масштабирования,
поворота и изменения прозрачности.

### **Пример использования:**

```xml
<!-- XML-определение Tween Animation -->
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate android:fromXDelta="0" android:toXDelta="200" android:duration="1000" />
</set>
```

```kotlin
// Применение Tween Animation
val animation = AnimationUtils.loadAnimation(context, R.anim.translate_animation)
view.startAnimation(animation)
```

---

## **12. Frame-by-Frame Animation (Кадровая анимация)**

### **Что это?**

Анимация, основанная на последовательном показе изображений (как в мультипликации).

### **Особенности:**

- Используется для создания простых анимированных эффектов.
- Определена через XML-файл `<animation-list>`.

### **Пример использования:**

```xml
<!-- XML-определение Frame-by-Frame Animation -->
<animation-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/frame1" android:duration="100" />
    <item android:drawable="@drawable/frame2" android:duration="100" />
</animation-list>
```

```kotlin
// Применение Frame-by-Frame Animation
val drawable = context.getDrawable(R.drawable.animation_list) as AnimationDrawable
view.background = drawable
drawable.start()
```

---

## **13. Custom Animations (Кастомные анимации)**

### **Что это?**

Разработчик может создавать собственные анимации, используя:

- **Custom Interpolators**: Для изменения скорости анимации.
- **Custom TypeEvaluators**: Для работы со сложными типами данных.

### **Пример использования:**

```kotlin
// Кастомный Interpolator
class BounceInterpolator : interpolator {
    override fun getInterpolation(input: Float): Float {
        return input * input // Простой бOUNCE эффект
    }
}

// Применение к ObjectAnimator
ObjectAnimator.ofFloat(view, "translationY", 0f, -200f).apply {
    interpolator = BounceInterpolator()
    duration = 1000
    start()
}
```

---

## **14. RecyclerView Item Animations**

### **Что это?**

Анимации, которые можно применить к элементам `RecyclerView` при добавлении, удалении, перемещении
или изменении.

### **Особенности:**

- По умолчанию используется `DefaultItemAnimator`.
- Можно создать кастомный `ItemAnimator` для реализации своих эффектов.

### **Пример использования:**

```kotlin
// Создание кастомного ItemAnimator
recyclerView.itemAnimator = MyCustomItemAnimator()
```

---

## **15. Scenegraph Animation (Анимация OpenGL)**

### **Что это?**

Анимации, созданные с использованием OpenGL для высокопроизводительной графики.

### **Особенности:**

- Используется для 3D-графики или сложных графических эффектов.
- Требует глубоких знаний OpenGL.

---