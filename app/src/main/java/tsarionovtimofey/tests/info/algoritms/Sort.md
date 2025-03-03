популярные виды сортировок с примерами на Kotlin, включая их принципы, временную сложность и код:

---

### **1. Пузырьковая сортировка (Bubble Sort)**

- **Принцип**: Попарное сравнение соседних элементов и их обмен, если они расположены в неправильном
  порядке. Процесс повторяется до полной сортировки.
- **Сложность**:
    - **Худший/Средний случай**: \(O(n^2)\)
    - **Лучший случай**: \(O(n)\) (если массив уже отсортирован).
- **Пример на Kotlin**:
  ```kotlin
  fun bubbleSort(arr: IntArray) {
      val n = arr.size
      for (i in 0 until n) {
          for (j in 0 until n - i - 1) {
              if (arr[j] > arr[j + 1]) {
                  // Обмен элементов
                  val temp = arr[j]
                  arr[j] = arr[j + 1]
                  arr[j + 1] = temp
              }
          }
      }
  }
  ```

---

### **2. Сортировка выбором (Selection Sort)**

- **Принцип**: На каждом шаге находит минимальный элемент и помещает его в начало несортированной
  части массива.
- **Сложность**:
    - **Все случаи**: \(O(n^2)\)
- **Пример на Kotlin**:
  ```kotlin
  fun selectionSort(arr: IntArray) {
      val n = arr.size
      for (i in 0 until n) {
          var minIndex = i
          for (j in i + 1 until n) {
              if (arr[j] < arr[minIndex]) minIndex = j
          }
          // Обмен минимального элемента с текущим
          val temp = arr[i]
          arr[i] = arr[minIndex]
          arr[minIndex] = temp
      }
  }
  ```

---

### **3. Сортировка вставками (Insertion Sort)**

- **Принцип**: Постепенно вставляет элементы в отсортированную часть массива.
- **Сложность**:
    - **Лучший случай**: \(O(n)\) (уже отсортированный массив).
    - **Средний/Худший случай**: \(O(n^2)\)
- **Пример на Kotlin**:
  ```kotlin
  fun insertionSort(arr: IntArray) {
      for (i in 1 until arr.size) {
          val key = arr[i]
          var j = i - 1
          // Сдвигаем элементы, чтобы найти место для вставки
          while (j >= 0 && arr[j] > key) {
              arr[j + 1] = arr[j]
              j -= 1
          }
          arr[j + 1] = key
      }
  }
  ```

---

### **4. Быстрая сортировка (Quick Sort)**

- **Принцип**: Рекурсивно разделяет массив на подмассивы с помощью опорного элемента (pivot), затем
  сортирует их.
- **Сложность**:
    - **Средний/Лучший случай**: \(O(n \log n)\)
    - **Худший случай**: \(O(n^2)\) (например, если массив уже отсортирован).
- **Пример на Kotlin**:
  ```kotlin
  fun quickSort(arr: IntArray, low: Int, high: Int) {
      if (low < high) {
          val pivotIndex = partition(arr, low, high)
          quickSort(arr, low, pivotIndex - 1)
          quickSort(arr, pivotIndex + 1, high)
      }
  }

  private fun partition(arr: IntArray, low: Int, high: Int): Int {
      val pivot = arr[high]
      var i = low - 1
      for (j in low until high) {
          if (arr[j] <= pivot) {
              i++
              // Обмен элементов
              val temp = arr[i]
              arr[i] = arr[j]
              arr[j] = temp
          }
      }
      val temp = arr[i + 1]
      arr[i + 1] = arr[high]
      arr[high] = temp
      return i + 1
  }
  ```

---

### **5. Сортировка слиянием (Merge Sort)**

- **Принцип**: Разделяет массив на подмассивы, сортирует их рекурсивно, затем объединяет.
- **Сложность**:
    - **Все случаи**: \(O(n \log n)\)
- **Пример на Kotlin**:
  ```kotlin
  fun mergeSort(arr: IntArray): IntArray {
      if (arr.size <= 1) return arr
      val mid = arr.size / 2
      val left = mergeSort(arr.copyOfRange(0, mid))
      val right = mergeSort(arr.copyOfRange(mid, arr.size))
      return merge(left, right)
  }

  private fun merge(left: IntArray, right: IntArray): IntArray {
      val result = IntArray(left.size + right.size)
      var i = 0, j = 0, k = 0
      while (i < left.size && j < right.size) {
          if (left[i] <= right[j]) {
              result[k++] = left[i++]
          } else {
              result[k++] = right[j++]
          }
      }
      while (i < left.size) result[k++] = left[i++]
      while (j < right.size) result[k++] = right[j++]
      return result
  }
  ```

---

### **6. Сортировка кучей (Heap Sort)**

- **Принцип**: Построение кучи (max-heap), затем последовательное извлечение максимальных элементов.
- **Сложность**:
    - **Все случаи**: \(O(n \log n)\)
- **Пример на Kotlin**:
  ```kotlin
  fun heapSort(arr: IntArray) {
      val n = arr.size
      // Построение max-heap
      for (i in n / 2 downTo 0) {
          heapify(arr, n, i)
      }
      // Извлекаем элементы по одному
      for (i in n - 1 downTo 0) {
          val temp = arr[0]
          arr[0] = arr[i]
          arr[i] = temp
          heapify(arr, i, 0)
      }
  }

  private fun heapify(arr: IntArray, n: Int, i: Int) {
      var largest = i
      val left = 2 * i + 1
      val right = 2 * i + 2
      if (left < n && arr[left] > arr[largest]) largest = left
      if (right < n && arr[right] > arr[largest]) largest = right
      if (largest != i) {
          val swap = arr[i]
          arr[i] = arr[largest]
          arr[largest] = swap
          heapify(arr, n, largest)
      }
  }
  ```

---

### **Сравнение алгоритмов**

| **Сортировка**  | **Лучший случай** | **Средний случай** | **Худший случай** | **Память**    | **Устойчивость** |
|-----------------|-------------------|--------------------|-------------------|---------------|------------------|
| **Пузырьковая** | \(O(n)\)          | \(O(n^2)\)         | \(O(n^2)\)        | \(O(1)\)      | Устойчива        |
| **Выбором**     | \(O(n^2)\)        | \(O(n^2)\)         | \(O(n^2)\)        | \(O(1)\)      | Неустойчива      |
| **Вставками**   | \(O(n)\)          | \(O(n^2)\)         | \(O(n^2)\)        | \(O(1)\)      | Устойчива        |
| **Быстрая**     | \(O(n \log n)\)   | \(O(n \log n)\)    | \(O(n^2)\)        | \(O(\log n)\) | Неустойчива      |
| **Слиянием**    | \(O(n \log n)\)   | \(O(n \log n)\)    | \(O(n \log n)\)   | \(O(n)\)      | Устойчива        |
| **Кучей**       | \(O(n \log n)\)   | \(O(n \log n)\)    | \(O(n \log n)\)   | \(O(1)\)      | Неустойчива      |

---

### **Когда использовать что?**

- **Для маленьких данных**: Пузырьковая/Вставками (просты в реализации).
- **Для больших данных**: Быстрая/Слиянием/Кучей (эффективные).
- **Если требуется устойчивость**: Слиянием или Вставками.
- **Если важно минимизировать память**: Кучей (требует \(O(1)\) дополнительной памяти).

---

### **Пример использования**

```kotlin
fun main() {
    val arr = intArrayOf(5, 2, 9, 1, 5, 6)
    println("Исходный массив: ${arr.contentToString()}")

    // Пример сортировки слиянием:
    val sortedArr = mergeSort(arr)
    println("Отсортированный массив: ${sortedArr.contentToString()}")
}
```

Эти алгоритмы покрывают основные подходы к сортировке, и их выбор зависит от конкретных требований к
скорости и ресурсам.