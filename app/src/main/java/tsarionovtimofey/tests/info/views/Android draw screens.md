## **Процесс обновления экрана**

1. Choreographer: Синхронизирует работу всех компонентов с Vsync.
2. Main Thread: Measure → Layout → Draw → Создает Display List.
3. Render Thread: Получает Display List → Преобразует в OpenGL ES команды.
4. GPU: Выполняет рендеринг → Сохраняет результат в Back Buffer.
5. SurfaceFlinger: Компонует слои → Отправляет на дисплей.